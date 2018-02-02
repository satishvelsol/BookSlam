package com.teamtesla.satish.slambook.api;

import com.teamtesla.satish.slambook.friends;
import com.teamtesla.satish.slambook.model.mainResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by user4 on 2/2/2018.
 */

public interface ApiService
{

@FormUrlEncoded
@POST("getFriends.php")
Call<mainResponse> dataLogin(@Field("u_mobile") String u_mobile);

//search friend list
@FormUrlEncoded
@POST("search.php")
Call<mainResponse> search(@Field("u_mobile") String u_mobile,
                          @Field("key_name") String key_name) ;

    @FormUrlEncoded
    @POST("login.php")
    Call<MSG> userLogin(@Field("mobile") String mobile,
                        @Field("password") String password);

    @FormUrlEncoded
    @POST("registration.php")
    Call<MSG> userSignup(@Field("name") String name,
                         @Field("email") String email,
                         @Field("mobile") String mobile,
                         @Field("password") String password);


    @FormUrlEncoded
    @POST("addFriend.php")
    Call<MSG> saveDetails(@Field("u_mobile") String u_mobile,
                          @Field("name") String name,
                          @Field("nick_name") String nickname,
                          @Field("dateofbirth") String dateofbirth,
                          @Field("best_friend") String best_friend,
                          @Field("f_mobile") String f_mobile,
                          @Field("address") String address,
                          @Field("fav_dish") String fav_dish,
                          @Field("fav_color") String fav_color,
                          @Field("hobbies") String hobbies
                          );

}
