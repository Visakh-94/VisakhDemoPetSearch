package com.petsearch.visakhdemoapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.petsearch.visakhdemoapp.Adapter.RecyclerAdapter;
import com.petsearch.visakhdemoapp.Controller.Presenter;
import com.petsearch.visakhdemoapp.Controller.getMovies;
import com.petsearch.visakhdemoapp.Model.Result;
import com.petsearch.visakhdemoapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements getMovies.View,getMovies.onItemInteraction{
    private Presenter mPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(this,this);
        mPresenter.getMoviesFromURL(getApplicationContext(), "");
        recyclerView = (RecyclerView)findViewById(R.id.movie_list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onGetMoviesSuccess(String message, List<Result> list) {

        countryAdapter = new RecyclerAdapter(getApplicationContext(), list,mPresenter);
        recyclerView.setAdapter(countryAdapter);

    }

    @Override
    public void onGetDataFailure(String message) {

    }


    @Override
    public void onClick2(Integer pos) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("id",pos.toString());
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
