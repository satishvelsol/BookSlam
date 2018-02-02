package com.teamtesla.satish.slambook;

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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button mSignup_btn;
    EditText mName,mEmail,mMobile,mPassword;
    TextView mSignInLink;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialise();
    }
    private void initialise() {

        mSignup_btn = (Button)findViewById(R.id.sign_up_button);
        mSignInLink=  (TextView) findViewById(R.id.link_login);

        mName       = (EditText) findViewById(R.id.name_input);
        mEmail      = (EditText) findViewById(R.id.email_input);
        mMobile     = (EditText) findViewById(R.id.mobile_input);
        mPassword   = (EditText) findViewById(R.id.password_input);


        mSignup_btn.setOnClickListener(this);
        mSignInLink.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        int id = view.getId();

       if(id == R.id.sign_up_button)
        {

            signUp();

        }
        else if(id == R.id.link_login)
        {
            Intent intent = new Intent(SignUp.this,Login.class);
            startActivity(intent);
        }


    }

    private void signUp() {
        String name = mName.getText().toString();
        String email = mEmail.getText().toString().trim();
        String mobile = mMobile.getText().toString().trim();
        String password = mPassword.getText().toString();

        String namePattern= "/^[A-Za-z ]+$/";
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(name.isEmpty())
        {
            mName.setError("Name should not be empty");
            mName.requestFocus();
        }
        else if(!name.matches(namePattern))
        {
            mName.setError("Please enter valid name");
            mName.requestFocus();
        }
        else if(email.isEmpty())
        {
            mEmail.setError("Email should not be empty");
            mEmail.requestFocus();
        }
        else if(!email.matches(emailPattern))
        {
            mEmail.setError("Please enter valid email");
            mEmail.requestFocus();
        }
        else if(mobile.isEmpty())
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
        else if(password.length()<=4)
        {
            mPassword.setError("Password length must be more than 4");
            mPassword.requestFocus();
        }
        else
        {
            //call api
//            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(SignUp.this,AddDetails.class);
//            startActivity(intent);
//
            ApiService service = ApiClient.getClient().create(ApiService.class);
            Call<MSG> call = service.userSignup(name,email,mobile,password);
            call.enqueue(new Callback<MSG>() {
                @Override
                public void onResponse(Call<MSG> call, Response<MSG> response) {
                    if(response.body().getSuccess() == 0)
                    {
                        Toast.makeText(SignUp.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        mName.setText("");
                        mEmail.setText("");
                        mMobile.setText("");
                        mPassword.setText("");
                    }
                    else  if(response.body().getSuccess() == 2)
                    {
                        Toast.makeText(SignUp.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SignUp.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MSG> call, Throwable t) {

                    Toast.makeText(SignUp.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();

                }
            });

         }


    }
}
