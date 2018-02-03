package com.teamtesla.satish.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDetails extends AppCompatActivity {


    TextView mName,mNickName,mDob,mMobile,mAddress,mBest_friend,mFav_dish,mFav_color,mHobbies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        initialisation();
        mName = (TextView)findViewById(R.id.name_label);
    }

    private void initialisation() {
        mName = (TextView)findViewById(R.id.name_label);
        mNickName = (TextView)findViewById(R.id.nick_name_label);
        mDob = (TextView)findViewById(R.id.dob_label);
        mMobile = (TextView)findViewById(R.id.mobile_label);
        mAddress = (TextView)findViewById(R.id.address_label);
        mBest_friend = (TextView)findViewById(R.id.bfriend_label);
        mFav_dish    = (TextView)findViewById(R.id.dish_label);
        mFav_color = (TextView)findViewById(R.id.color_label);
        mHobbies = (TextView)findViewById(R.id.hobbies_label);
        setLayoutValues();
    }

    public void setLayoutValues()
    {
        Intent in= getIntent();
        Bundle b = in.getExtras();
        if(b!=null)
        {
            mName.setText((String)b.get("f_name"));
            mNickName.setText((String)b.get("n_name"));
            mDob.setText((String)b.get("dob"));
            mMobile.setText((String)b.get("mobile"));
            mAddress.setText((String)b.get("address"));
            mBest_friend.setText((String)b.get("best_friend"));
            mFav_dish.setText((String)b.get("fav_dish"));
            mFav_color.setText((String)b.get("fav_color"));
            mHobbies.setText((String)b.get("hobbies"));
        }
        else
        {
            Toast.makeText(this, "False else group", Toast.LENGTH_SHORT).show();
        }
    }
}
