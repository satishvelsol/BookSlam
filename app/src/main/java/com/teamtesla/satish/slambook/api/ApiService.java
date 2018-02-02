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
Call<mainResponse> dataLogin(@Field("u_mobile") String u_mobile) ;

}
