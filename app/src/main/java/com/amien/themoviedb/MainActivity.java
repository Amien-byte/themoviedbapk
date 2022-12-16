package com.amien.themoviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amien.themoviedb.API.ApiService;
import com.amien.themoviedb.Model.MovieModel;
import com.amien.themoviedb.Movie.Movie;
import com.amien.themoviedb.Movie.MovieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button nowplaying, popular, toprated,upcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Movie.class);
        nowplaying = findViewById(R.id.nowplaying);
        popular = findViewById(R.id.popular);
        toprated = findViewById(R.id.toprated);
        upcoming = findViewById(R.id.upcoming);

        nowplaying.setOnClickListener(v -> {
            intent.putExtra("category", "now_playing");
            startActivity(intent);
        });

        popular.setOnClickListener(v -> {
            intent.putExtra("category", "popular");
            startActivity(intent);
        });

        toprated.setOnClickListener(v -> {
            intent.putExtra("category", "top_rated");
            startActivity(intent);
        });

        upcoming.setOnClickListener(v -> {
            intent.putExtra("category", "upcoming");
            startActivity(intent);
        });
    }
}