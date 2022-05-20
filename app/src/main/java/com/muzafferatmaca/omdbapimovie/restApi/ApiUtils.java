package com.muzafferatmaca.omdbapimovie.restApi;

/**
 * Created by Muzaffer Atmaca on 18.05.2022.
 */
public class ApiUtils {

    public static final String BASE_URL = "http://www.omdbapi.com/";
    public static final String API_KEY = "26e2c81a";

    public static MovieDao movieDao() {

        return RetrofitClient.getClient(BASE_URL).create(MovieDao.class);

    }

}
