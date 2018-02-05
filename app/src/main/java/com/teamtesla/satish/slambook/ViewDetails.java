package com.teamtesla.satish.slambook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtesla.satish.slambook.api.ApiClient;
import com.teamtesla.satish.slambook.api.ApiService;
import com.teamtesla.satish.slambook.model.ShowDetailsResponse;
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
    SharedPreferences logindetails;
    SharedPreferences.Editor logindetails_e;
    String user_mobile_number;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        mview_list = findViewById(R.id.view_list);
        search = findViewById(R.id.search_button);
        medit = findViewById(R.id.search_input);

        logindetails = getSharedPreferences("slam_book_login_details",MODE_PRIVATE);
        logindetails_e = logindetails.edit();
        user_mobile_number = logindetails.getString("Username",null);

        callFriends();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //clicks the button
                clickMethod();
            }
        });

    }

    private void callFriends() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<mainResponse> call = service.dataLogin(user_mobile_number);
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
                Toast.makeText(ViewDetails.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickMethod() {
        //heree code for diaplaying the data on button clicks
        String name = medit.getText().toString().trim();
        ApiService service = ApiClient.getClient().create(ApiService.class);
        retrofit2.Call<mainResponse> call = service.search(user_mobile_number, name);
        call.enqueue(new Callback<mainResponse>() {
            @Override
            public void onResponse(Call<mainResponse> call, Response<mainResponse> response) {
                if (response.body().getResponse() == 200) {
                    Toast.makeText(ViewDetails.this, "success", Toast.LENGTH_SHORT).show();

                    baseAdapter = new MyBaseAdapter(ViewDetails.this, response.body().getFriends());
                    mview_list.setAdapter(baseAdapter);
                } else {
                    Toast.makeText(ViewDetails.this, "friend not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<mainResponse> call, Throwable t) {
                Toast.makeText(ViewDetails.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();


            }
        });

        //goto SHOWDETAILS activity

    }

    private void callShowDetails(String mb) {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ShowDetailsResponse> call = service.showdetails(user_mobile_number, mb);
        call.enqueue(new Callback<ShowDetailsResponse>() {
            @Override
            public void onResponse(Call<ShowDetailsResponse> call, Response<ShowDetailsResponse> response) {

                if (response.body().getResponse() == 200) {


                    Intent i = new Intent(ViewDetails.this, ShowDetails.class);
                    String f_name = response.body().getFriendDetails().name;
                    i.putExtra("f_name", f_name);
                    String n_name = response.body().getFriendDetails().nick_name;
                    i.putExtra("n_name", n_name);
                    String dob = response.body().getFriendDetails().dob;
                    i.putExtra("dob", dob);
                    String mobile = response.body().getFriendDetails().mobile;
                    i.putExtra("mobile", mobile);
                    String address = response.body().getFriendDetails().address;
                    i.putExtra("address", address);
                    String best_friend = response.body().getFriendDetails().best_friend;
                    i.putExtra("best_friend", best_friend);
                    String fav_dish = response.body().getFriendDetails().fav_dish;
                    i.putExtra("fav_dish", fav_dish);
                    String fav_color = response.body().getFriendDetails().fav_color;
                    i.putExtra("fav_color", fav_dish);
                    String hobbies = response.body().getFriendDetails().hobbies;
                    i.putExtra("hobbies", hobbies);

                    startActivity(i);


                   // Toast.makeText(ViewDetails.this, response.body().getFriendDetails().fav_color+"", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ViewDetails.this, "Friend Details not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ShowDetailsResponse> call, Throwable t) {

                Toast.makeText(ViewDetails.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();
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
                mViewHolder.mviewList_linearLayout = (LinearLayout) view.findViewById(R.id.viewList_linearLayout);


                view.setTag(mViewHolder);
            } else {
                mViewHolder = (MyViewHolder) view.getTag();
            }

            mViewHolder.mtext_name.setText(friends.get(i).getName());
            mViewHolder.mtext_mobile.setText(friends.get(i).getFmobile());

            mViewHolder.mviewList_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView m = (TextView)view.findViewById(R.id.list_mobile);
                    String mb = m.getText().toString().trim();
                   // Toast.makeText(ViewDetails.this, ""+mb, Toast.LENGTH_SHORT).show();
                    callShowDetails(mb);
                }
            });
            return view;
        }
        private class MyViewHolder
        {
            TextView mtext_name, mtext_mobile;
            LinearLayout mviewList_linearLayout;

        }
    }
}