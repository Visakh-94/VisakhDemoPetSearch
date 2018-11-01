package com.petsearch.visakhdemoapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.petsearch.visakhdemoapp.R;

public class SecondActivity extends AppCompatActivity {

    android.support.v7.app.ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.e("id in second",""+id);
        Toast.makeText(this,"Movie id selected :"+id,Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
