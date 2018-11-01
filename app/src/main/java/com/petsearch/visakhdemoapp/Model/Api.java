package com.petsearch.visakhdemoapp.Model;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

//    @Headers({"Content-type: application/json","users_id:RetrofitSample","access_token:RetrofitSample"})
//    @GET("discover/movie")
//    public Call<Movies> getMovies(@Body RequestBody requestBody);

//    @Headers({"Content-type: application/json","users_id:RetrofitSample","access_token:RetrofitSample"})

    @GET("/3/discover/movie")
    Call<Movies> getMovies();

    @POST("/login")
    Call<Result> postPost(@Body RequestBody requestBody);

    @GET("/users")
    Call<ResponseBody> getPosts();
}
