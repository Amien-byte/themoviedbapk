package com.amien.themoviedb.API;

import com.amien.themoviedb.Model.MovieDetailModel;
import com.amien.themoviedb.Model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiEndpoint {

    @GET("now_playing")
    Call<MovieModel> getNowPlaying(@Query("api_key") String token);

    @GET("popular")
    Call<MovieModel> getPopular(@Query("api_key") String token);

    @GET("top_rated")
    Call<MovieModel> getTopRated(@Query("api_key") String token);

    @GET("upcoming")
    Call<MovieModel> getUpcoming(@Query("api_key") String token);

    @GET("{id}")
    Call<MovieDetailModel> getDetail(@Path(value = "id", encoded = true) String id, @Query("api_key") String token);
}
