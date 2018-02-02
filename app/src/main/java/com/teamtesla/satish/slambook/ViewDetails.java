package com.teamtesla.satish.slambook;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtesla.satish.slambook.api.ApiClient;
import com.teamtesla.satish.slambook.api.ApiService;
import com.teamtesla.satish.slambook.model.mainResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDetails extends AppCompatActivity
{
    ListView mview_list;
    ImageView search;
    EditText medit;
    MyBaseAdapter baseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        mview_list = findViewById(R.id.view_list);
        search = findViewById(R.id.serach);
        medit = findViewById(R.id.medit);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clicks the button
                clickMethod();
            }
        });

    }
    private void clickMethod()
    {
        //heree code for diaplaying the data on button clicks
        String nuimber = medit.getText().toString().trim();
        ApiService service = ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<mainResponse> call = service.dataLogin(nuimber);
        call.enqueue(new Callback<mainResponse>()
        {
            @Override
            public void onResponse(Call<mainResponse> call, Response<mainResponse> response)
            {
                if (response.body().getResponse()==200)
                {

                    baseAdapter=new MyBaseAdapter(ViewDetails.this,response.body().getFriends());
                    mview_list.setAdapter(baseAdapter);
                }
                else
                {
                    Toast.makeText(ViewDetails.this, "friend not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<mainResponse> call, Throwable t)
            {
                Toast.makeText(ViewDetails.this, "from failure", Toast.LENGTH_SHORT).show();

            }
        });

    }
    class MyBaseAdapter extends BaseAdapter
    {
        Activity context;
        List<friends> friends;
        LayoutInflater inflater;

        public MyBaseAdapter(Activity context, List<com.teamtesla.satish.slambook.friends> friends) {
            this.context = context;
            this.friends = friends;
        }

        @Override
        public int getCount() {
            return friends.size();
        }

        @Override
        public Object getItem(int i) {
            return friends.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            MyViewHolder mViewHolder;
            LayoutInflater inflater=context.getLayoutInflater();

            if (view == null) {
                view = inflater.inflate(R.layout.view_list_data,null);
                mViewHolder = new MyViewHolder();
                mViewHolder.mtext_name = (TextView) view.findViewById(R.id.list_name);
                mViewHolder.mtext_mobile = (TextView) view.findViewById(R.id.list_mobile);

                view.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) view.getTag();
            }

            mViewHolder.mtext_name.setText(friends.get(i).getName());
            mViewHolder.mtext_mobile.setText(friends.get(i).getFmobile());

            return view;
        }
        private class MyViewHolder
        {
            TextView mtext_name, mtext_mobile;

        }
    }
}