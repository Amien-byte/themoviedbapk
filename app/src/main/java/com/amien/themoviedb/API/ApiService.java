package com.amien.themoviedb.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    static final String token = "435fad1fce7ee24dffeed67bdd5f69dd";
    private static Retrofit retrofit;
    public static ApiEndpoint endpoint (){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndpoint.class);
    }
}
