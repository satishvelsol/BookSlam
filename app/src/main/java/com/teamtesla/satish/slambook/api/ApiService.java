package com.teamtesla.satish.slambook.api;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Admin on 1/3/2018.
 */

public interface ApiService
{
    @FormUrlEncoded
    @POST("login.php")
    Call<MSG> userLogin(@Field("mobile") String mobile, @Field("password") String password);

    @FormUrlEncoded
    @POST("registration.php")
    Call<MSG> userSignup(@Field("name") String name,
                         @Field("email") String email,
                         @Field("mobile") String mobile,
                         @Field("password") String password);


    @FormUrlEncoded
    @POST("addFriend.php")
    Call<MSG> saveDetails(@Field("u_mobile") String umobile,
                          @Field("name") String name,
                          @Field("nick_name") String nick_name,
                          @Field("dateofbirth") String dob,
                          @Field("best_friend") String bfriend,
                          @Field("f_mobile") String fmobile,
                          @Field("address") String address,
                          @Field("fav_dish") String dish,
                          @Field("fav_color") String colour,
                          @Field("hobbies") String hobbies);

}
