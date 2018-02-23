package com.teamtesla.satish.slambook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamtesla.satish.slambook.api.ApiClient;
import com.teamtesla.satish.slambook.api.ApiService;
import com.teamtesla.satish.slambook.api.MSG;
import com.victor.loading.newton.NewtonCradleLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {


    Button mSignin_btn;
    EditText mMobile,mPassword;
    String username;
    TextView mSignupLink, mForgot_link;
    SharedPreferences logindetails;
    SharedPreferences.Editor logindetails_e;
    RelativeLayout mlogin_progress;
    private NewtonCradleLoading newtonCradleLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logindetails = getSharedPreferences("slam_book_login_details",MODE_PRIVATE);
        logindetails_e = logindetails.edit();
        mlogin_progress = (RelativeLayout)findViewById(R.id.login_progress);

        boolean res = logindetails.getBoolean("k1",false);
        if (res)
        {
            finish();
            Intent i = new Intent(this,Home.class);
            startActivity(i);
        }
        initialise();
//        if(!MyApplication.isNetworkAvailable(this)) {
//            checkInternet();
//        }

        newtonCradleLoading = (NewtonCradleLoading) findViewById(R.id.newton_cradle_loading);

        if (newtonCradleLoading.isStart()) {

            newtonCradleLoading.stop();
        } else {

            newtonCradleLoading.start();
        }
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

    private void checkInternet() {
        View view = findViewById(R.id.login_linear_layout);
        String message = "Please check with your internet connection";
        int duration = Snackbar.LENGTH_LONG;
        Snackbar.make(view, message, duration).show();
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

            LayoutInflater inflater = LayoutInflater.from(this);
            View forget_password_alert_message = inflater.inflate(R.layout.forgot_password_alert, null, false);
            Button mok_btn = (Button)forget_password_alert_message.findViewById(R.id.ok_forget_password_label);
            Button mcancel_btn = (Button)forget_password_alert_message.findViewById(R.id.cancel_forget_password_label);
            final EditText mobile_email = (EditText)forget_password_alert_message.findViewById(R.id.mobile_or_email_label);

            AlertDialog.Builder forgot_password_alert_builder = new AlertDialog.Builder(this);
            forgot_password_alert_builder.setView(forget_password_alert_message);
            forgot_password_alert_builder.setCancelable(false);
            final AlertDialog ad = forgot_password_alert_builder.create();
            ad.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(ad.getWindow().getAttributes());
            lp.width = 600;
            lp.height = 500;
           // ad.getWindow().setLayout(600, 480);
            ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            mcancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ad.dismiss();
                }
            });
            mok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = mobile_email.getText().toString();
                    //Toast.makeText(Login.this, "button working"+ username,Toast.LENGTH_SHORT).show();

                    ApiService service = ApiClient.getClient().create(ApiService.class);
                    Call<MSG> call = service.forgotpassword(username);
                    call.enqueue(new Callback<MSG>() {
                        @Override
                        public void onResponse(Call<MSG> call, Response<MSG> response) {
                            if(response.body().getSuccess() == 0)
                            {
                                Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                ad.dismiss();
                            }
                            else  if(response.body().getSuccess() == 1)
                            {
                                mobile_email.setError(response.body().getMessage());

                               // Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                mobile_email.setError(response.body().getMessage());
                               // Toast.makeText(Login.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<MSG> call, Throwable t) {
                            if (!MyApplication.isNetworkAvailable(Login.this)) {
                                checkInternet();

                            } else {
                                Toast.makeText(Login.this, "Oops..! Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }
    private void signIn() {

            final String mobile = mMobile.getText().toString().trim();
            final String password = mPassword.getText().toString();
            if (mobile.isEmpty()) {
                mMobile.setError("Mobile number should not be empty");
                mMobile.requestFocus();
            } else if (mobile.length() != 10) {
                mMobile.setError("Please enter valid Mobile number");
                mMobile.requestFocus();
            } else if (password.isEmpty()) {
                mPassword.setError("Password should not be empty");
                mPassword.requestFocus();
            } else if (password.length() <= 4) {
                mPassword.setError("Password length must be than 4");
                mPassword.requestFocus();
            } else if (!MyApplication.isNetworkAvailable(this)) {
                checkInternet();
            } else {

                //dialog ball
                mlogin_progress.setVisibility(View.VISIBLE);


                //end dialog ball


                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<MSG> call = service.userLogin(mobile, password);
                call.enqueue(new Callback<MSG>() {
                    @Override
                    public void onResponse(Call<MSG> call, Response<MSG> response) {
                        if (response.body().getSuccess() == 0) {
                            logindetails_e.putBoolean("k1", true);
                            logindetails_e.putString("Username", mobile);
                            logindetails_e.commit();
                            mlogin_progress.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);

                        } else if (response.body().getSuccess() == 2) {
                            Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            mlogin_progress.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            mlogin_progress.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onFailure(Call<MSG> call, Throwable t) {
                        mlogin_progress.setVisibility(View.GONE);
                        if (!MyApplication.isNetworkAvailable(Login.this)) {
                            checkInternet();

                        } else {
                            Toast.makeText(Login.this, "Oops..! Something Went Wrong", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
    }



