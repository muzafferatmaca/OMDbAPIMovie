package com.muzafferatmaca.omdbapimovie.restApi;

import com.muzafferatmaca.omdbapimovie.models.DetailModel;
import com.muzafferatmaca.omdbapimovie.models.SearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Muzaffer Atmaca on 18.05.2022.
 */
public interface MovieDao {

    @GET("?")
    Call<SearchModel> allMovies(@Query("apikey")String API_KEY,@Query("s")String s);

    @GET("?")
    Call<DetailModel> allDetail(@Query("apikey")String API_KEY,@Query("i")String i);

}
