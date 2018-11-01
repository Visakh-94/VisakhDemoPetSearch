package com.petsearch.visakhdemoapp.Controller;

import android.content.Context;

import com.petsearch.visakhdemoapp.Model.Movies;
import com.petsearch.visakhdemoapp.Model.Result;

import java.util.List;

public interface getMovies {

    interface View{
        void onGetMoviesSuccess(String message, List<Result> list);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getMoviesFromURL(Context context, String url);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetMoviesListener{
        void onSuccess(String message, List<Result> list);
        void onFailure(String message);
    }

    interface onItemInteraction{
        void onClick2(Integer pos);

    }
}
