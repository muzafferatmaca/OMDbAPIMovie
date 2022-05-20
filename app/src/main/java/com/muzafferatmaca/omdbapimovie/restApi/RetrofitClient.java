package com.muzafferatmaca.omdbapimovie.restApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muzaffer Atmaca on 18.05.2022.
 */
public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit getClient(String baseUrl){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
