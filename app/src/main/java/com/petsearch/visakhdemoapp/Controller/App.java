package com.petsearch.visakhdemoapp.Controller;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petsearch.visakhdemoapp.Model.Api;
import com.petsearch.visakhdemoapp.Model.Movies;
import com.petsearch.visakhdemoapp.Model.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App implements getMovies.Interactor{
    private getMovies.onGetMoviesListener mOnGetMovielistener;
    List<Result> allcountry = new ArrayList<>();
    List<String> allCountriesData = new ArrayList<>();

    public final static String BASE_URL = "https://api.themoviedb.org/";

    public App(getMovies.onGetMoviesListener mOnGetDatalistener){
        this.mOnGetMovielistener = mOnGetDatalistener;
    }
    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request original = chain.request();
                        HttpUrl originalhttpUrl = original.url();
                        HttpUrl url2 = originalhttpUrl.newBuilder()
                                .addQueryParameter("api_key","9d22c2fa2b88fc4c35be2d375d9c323a")
                                .build();

                        Log.e("url here",""+url2);
                        Request.Builder requestBuilder = original.newBuilder().url(url2);
                        Request request = requestBuilder
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

//        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Api request = retrofit.create(Api.class);
        retrofit2.Call<Movies> call = request.getMovies();
        call.enqueue(new retrofit2.Callback<Movies>() {
            @Override
            public void onResponse(retrofit2.Call<Movies> call, retrofit2.Response<Movies> response) {
                Movies jsonResponse = response.body();

                allcountry = jsonResponse.getResults();
                for(int i=0;i<allcountry.size();i++){
                    allCountriesData.add(allcountry.get(i).getTitle());
                }
                Log.d("Data", "Refreshed");
                mOnGetMovielistener.onSuccess("List Size: " + allCountriesData.size(), allcountry);



            }
            @Override
            public void onFailure(retrofit2.Call<Movies> call, Throwable t) {
                Log.v("Error",t.getMessage());
                mOnGetMovielistener.onFailure(t.getMessage());
            }
        });
    }
}
