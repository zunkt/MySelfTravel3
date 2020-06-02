package com.example.myselftravel3.NetworkUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AccountAPI {
    @GET("get-Account.php")
    Call<LoadResult> getUsers();

    @POST("add-Account.php")
    @FormUrlEncoded
    Call<AddResult> addUser(@Field("username") String name,
                            @Field("password") String password);

    @POST("update-Account.php")
    @FormUrlEncoded
    Call<AddResult> updateUser(@Field("id") int id,
                               @Field("username") String name,
                               @Field("password") String password);

    @POST("delete-Account.php")
    @FormUrlEncoded
    Call<Result> deleteUser(@Field("id") int id);
}
