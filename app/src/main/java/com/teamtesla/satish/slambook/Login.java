package com.teamtesla.satish.slambook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtesla.satish.slambook.api.ApiClient;
import com.teamtesla.satish.slambook.api.ApiService;
import com.teamtesla.satish.slambook.api.MSG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {


    Button mSignin_btn;
    EditText mMobile,mPassword;
    TextView mSignupLink, mForgot_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialise();
    }

    private void initialise() {
        mSignupLink = (TextView)findViewById(R.id.link_signup);
        mForgot_link = (TextView)findViewById(R.id.forgot_label);
        mSignin_btn = (Button)findViewById(R.id.sign_in_button);

        mMobile     = (EditText) findViewById(R.id.mobile_input);
        mPassword   = (EditText) findViewById(R.id.password_input);

        mSignin_btn.setOnClickListener(this);
        mSignupLink.setOnClickListener(this);
        mForgot_link.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.sign_in_button)
        {
            signIn();
        }
        else if(id == R.id.link_signup)
        {
            Intent intent = new Intent(Login.this,SignUp.class);
            startActivity(intent);
        }
        else if(id == R.id.forgot_label)
        {

            final Dialog dialog = new Dialog(this); // Context, this, etc.
            dialog.setContentView(R.layout.activity_forgot_password);
            dialog.setTitle("Dialog");
            dialog.show();

        }
    }

    private void signIn() {

        final String mobile = mMobile.getText().toString().trim();
        final String password = mPassword.getText().toString();


        if(mobile.isEmpty())
        {
            mMobile.setError("Mobile number should not be empty");
            mMobile.requestFocus();
        }
        else if(mobile.length()!=10)
        {
            mMobile.setError("Please enter valid Mobile number");
            mMobile.requestFocus();
        }
        else if(password.isEmpty()){
            mPassword.setError("Password should not be empty");
            mPassword.requestFocus();
        }
        else if(password.length() <=4)
        {
            mPassword.setError("Password length must be than 4");
            mPassword.requestFocus();
        }
        else
        {
            //call api
//            Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Login.this,Home.class);
//            startActivity(intent);

            ApiService service = ApiClient.getClient().create(ApiService.class);
            Call<MSG> call = service.userLogin(mobile,password);
            call.enqueue(new Callback<MSG>() {
                @Override
                public void onResponse(Call<MSG> call, Response<MSG> response) {
                    if(response.body().getSuccess() == 0)
                    {

                        Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);

                    }
                    else  if(response.body().getSuccess() == 2)
                    {
                        Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MSG> call, Throwable t) {

                    Toast.makeText(Login.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

}


