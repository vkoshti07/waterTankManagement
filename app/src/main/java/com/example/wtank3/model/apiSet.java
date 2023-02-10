package com.example.wtank3.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiSet {

    @GET("readData.php")
    Call<List<responseModel>> getData();

    @GET("Katargam.php")
    Call<List<responseModel>> getKatargamData();

    @GET("majuragate.php")
    Call<List<responseModel>> getMajuragateData();

    @GET("Athvagate.php")
    Call<List<responseModel>> getAthvagatData();



    @FormUrlEncoded
    @POST("register.php")
    Call<register_response> getRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
            );

    @FormUrlEncoded
    @POST("watertanklogin.php")
    Call<login_response> getLogin(
            @Field("email") String email,
            @Field("password") String password
            );

}
