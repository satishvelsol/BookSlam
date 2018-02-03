package com.teamtesla.satish.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener
{
    Button mAdd_btn,mView_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAdd_btn = (Button)findViewById(R.id.add_button);
        mView_btn = (Button)findViewById(R.id.view_button);
        mAdd_btn.setOnClickListener(this);
        mView_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.add_button)
        {
            Intent intent = new Intent(Home.this,AddDetails.class);
            startActivity(intent);
        }
        else if(id == R.id.view_button)
        {
            Intent intent = new Intent(Home.this,ViewDetails.class);
            startActivity(intent);
        }
    }
}
