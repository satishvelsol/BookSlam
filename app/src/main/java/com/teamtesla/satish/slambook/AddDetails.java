package com.teamtesla.satish.slambook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teamtesla.satish.slambook.api.ApiClient;
import com.teamtesla.satish.slambook.api.ApiService;
import com.teamtesla.satish.slambook.api.MSG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDetails extends AppCompatActivity implements View.OnClickListener{

    EditText mName_et,mNickName_et,mDob_dp,mMobile_et,mAddress_et,mBestfriend_et,mFavDish_et,mFavColor_et,mHobbies_et;
    Button mSave_btn,mClear_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        initalise();
    }

    private void initalise() {

        mName_et = (EditText)findViewById(R.id.name_input);
        mNickName_et = (EditText)findViewById(R.id.nick_name_input);
        mDob_dp = (EditText)findViewById(R.id.dob_input);
        mMobile_et = (EditText)findViewById(R.id.mobile_input);
        mAddress_et = (EditText)findViewById(R.id.address_input);
        mBestfriend_et = (EditText)findViewById(R.id.bestfriend_input);
        mFavDish_et = (EditText)findViewById(R.id.fav_dish_input);
        mFavColor_et = (EditText)findViewById(R.id.fav_color_input);
        mHobbies_et = (EditText)findViewById(R.id.hobbies_input);

        mSave_btn = (Button) findViewById(R.id.save_button);
        mClear_btn = (Button) findViewById(R.id.clear_button);

        mSave_btn.setOnClickListener(this);
        mClear_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if(id == R.id.save_button)
        {
            saveToServer();
        }
        else if(id == R.id.clear_button)
        {
            clearFields();
        }
    }

    private void clearFields() {

        mName_et.setText("");
        mNickName_et.setText("");
        mDob_dp.setText("");
        mMobile_et.setText("");
        mAddress_et.setText("");
        mBestfriend_et.setText("");
        mFavDish_et.setText("");
        mFavColor_et.setText("");
        mHobbies_et.setText("");
        mName_et.requestFocus();

    }

    private void saveToServer() {

        String namePattern= "/^[A-Za-z ]+$/";


        String name = mName_et.getText().toString();
        String nick_name =  mNickName_et.getText().toString().trim();
        String dob = mDob_dp.getText().toString();
        String mobile = mMobile_et.getText().toString().trim();
        String address = mAddress_et.getText().toString();
        String bfriend = mBestfriend_et.getText().toString().trim();
        String dish  = mFavDish_et.getText().toString().trim();
        String color = mFavColor_et.getText().toString().trim();
        String hobbies = mHobbies_et.getText().toString();

            if(name.isEmpty())
            {
                mName_et.setError("Your Name");
                mName_et.requestFocus();
            }
            else if(name.matches(namePattern))
            {
                mName_et.setError("Enter valid Name");
                mName_et.requestFocus();
            }
            else if(nick_name.isEmpty())
            {
                mNickName_et.setError("Your nickname");
                mNickName_et.requestFocus();
            }
            else if(dob.isEmpty())
            {
                mDob_dp.setError("Your Date Of Birth");
                mDob_dp.requestFocus();
            }
            else if(mobile.isEmpty())
            {
                mMobile_et.setError("Your Mobile Number");
                mMobile_et.requestFocus();
            }
            else if(mobile.length()!=10)
            {
                mMobile_et.setError("Invalid Mobile Number");
                mMobile_et.requestFocus();
            }
            else if(address.isEmpty())
            {
                mAddress_et.setError("Your Address");
                mAddress_et.requestFocus();
            }
            else if(bfriend.isEmpty())
            {
                mBestfriend_et.setError("Your Bestfriend");
                mBestfriend_et.requestFocus();
            }
            else if(dish.isEmpty())
            {
                mFavDish_et.setError("Your favourite dish");
                mFavDish_et.requestFocus();
            }
            else if(color.isEmpty())
            {
                mFavColor_et.setError("Your favourite colour");
                mFavColor_et.requestFocus();
            }
            else if(hobbies.isEmpty())
            {
                mHobbies_et.setError("Your hobbies");
                mHobbies_et.requestFocus();
            }
            else
            {

                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<MSG> call = service.saveDetails("8330966928",name,nick_name,dob,bfriend,mobile,address,dish,color,hobbies);
                call.enqueue(new Callback<MSG>() {
                    @Override
                    public void onResponse(Call<MSG> call, Response<MSG> response) {
                        if(response.body().getSuccess() == 0)
                        {
                            Toast.makeText(AddDetails.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else  if(response.body().getSuccess() == 2)
                        {
                            Toast.makeText(AddDetails.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(AddDetails.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MSG> call, Throwable t) {

                        Toast.makeText(AddDetails.this, "Please check with your internet connection", Toast.LENGTH_SHORT).show();

                    }
                });


            }

    }


}
