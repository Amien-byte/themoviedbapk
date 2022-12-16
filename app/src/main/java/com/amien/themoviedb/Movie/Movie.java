package com.amien.themoviedb.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amien.themoviedb.API.ApiService;
import com.amien.themoviedb.Model.MovieModel;
import com.amien.themoviedb.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        Bundle bundle = new Bundle();
        bundle.putString("category", category);

        NavHostFragment finalHost = NavHostFragment.create(R.navigation.navigate, bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit();
    }
}