package com.teamtesla.satish.slambook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewDetails extends AppCompatActivity
{
    ListView mview_list;
    MyBaseAdapter baseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        mview_list=findViewById(R.id.view_list);

    }
}
class MyBaseAdapter extends BaseAdapter
{
    Context context;
    List<friends> friends;
    LayoutInflater inflater;

    public MyBaseAdapter(Context context, List<com.teamtesla.satish.slambook.friends> friends)
    {
        this.context = context;
        this.friends = friends;
    }

    @Override
    public int getCount()
    {
        return friends.size();
    }

    @Override
    public Object getItem(int i)
    {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        MyViewHolder mViewHolder;

        if (view == null)
        {
            view = inflater.inflate(R.layout.view_list_data, viewGroup, false);
            mViewHolder = new MyViewHolder();
            mViewHolder.mtext_name=(TextView)view.findViewById(R.id.list_name);
            mViewHolder.mtext_mobile=(TextView)view.findViewById(R.id.list_mobile);

            view.setTag(mViewHolder);
        }
        else
            {
            mViewHolder = (MyViewHolder) view.getTag();
        }

        mViewHolder.mtext_name.setText(friends.get(i).getName());
        mViewHolder.mtext_mobile.setText(friends.get(i).getFmobile());

        return view;
    }
   private class MyViewHolder
    {
        TextView mtext_name,mtext_mobile;

    }
}