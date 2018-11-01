package com.petsearch.visakhdemoapp.Controller;

import android.content.Context;

import com.petsearch.visakhdemoapp.Model.Result;

import java.util.List;

public class Presenter implements getMovies.Presenter, getMovies.onGetMoviesListener,getMovies.onItemInteraction {
    private getMovies.View mGetDataView;
    private getMovies.onItemInteraction mGetDataView2;
    private App mIntractor;
    public Presenter(getMovies.View mGetDataView,getMovies.onItemInteraction mGetDataView2){
        this.mGetDataView = mGetDataView;
        this.mGetDataView2 = mGetDataView2;
        mIntractor = new App(this);
    }

    @Override
    public void onSuccess(String message, List<Result> allCountriesData) {
        mGetDataView.onGetMoviesSuccess(message, allCountriesData);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }

    @Override
    public void getMoviesFromURL(Context context, String url) {

        mIntractor.initRetrofitCall(context,url);

    }

//    @Override
//    public void onClick(Integer pos) {
//        mGetDataView2.onClick2(pos);
//    }

    @Override
    public void onClick2(Integer pos) {
        mGetDataView2.onClick2(pos);
    }
}
