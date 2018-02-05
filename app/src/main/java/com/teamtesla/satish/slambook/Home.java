package com.teamtesla.satish.slambook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener
{
    Button mAdd_btn,mView_btn;
    SharedPreferences logindetails;
    SharedPreferences.Editor logindetails_e;
    String user_mobile_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAdd_btn = (Button)findViewById(R.id.add_button);
        mView_btn = (Button)findViewById(R.id.view_button);
        mAdd_btn.setOnClickListener(this);
        mView_btn.setOnClickListener(this);

        logindetails = getSharedPreferences("slam_book_login_details",MODE_PRIVATE);
        logindetails_e = logindetails.edit();

        user_mobile_number = logindetails.getString("Username",null);

        if (user_mobile_number.isEmpty())
        {
            finish();
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            Intent i = new Intent(this,Login.class);
            logindetails_e.putBoolean("k1",false);
            logindetails_e.commit();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
