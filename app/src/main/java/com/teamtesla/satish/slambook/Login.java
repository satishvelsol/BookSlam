package com.teamtesla.satish.slambook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {


    Button mSignin_btn;
    EditText mMobile,mPassword;
    TextView mSignupLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialise();
    }

    private void initialise() {
        mSignupLink = (TextView)findViewById(R.id.link_signup);
        mSignin_btn = (Button)findViewById(R.id.sign_in_button);

        mMobile     = (EditText) findViewById(R.id.mobile_input);
        mPassword   = (EditText) findViewById(R.id.password_input);

        mSignin_btn.setOnClickListener(this);
        mSignupLink.setOnClickListener(this);
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
    }

    private void signIn() {

        String mobile = mMobile.getText().toString().trim();
        String password = mPassword.getText().toString();


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
//        else if(password.length() >=6)
//        {
//            mPassword.setError("Password length must be 6 or more letters");
//            mPassword.requestFocus();
//        }
        else
        {
            //call api
            Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this,Home.class);
            startActivity(intent);
        }

    }

}


