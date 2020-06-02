package com.example.myselftravel3;

import com.example.myselftravel3.NetworkUser.AccountAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2/api/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    public static AccountAPI getAccountAPI(){
        return retrofit.create(AccountAPI.class);
    }
}
