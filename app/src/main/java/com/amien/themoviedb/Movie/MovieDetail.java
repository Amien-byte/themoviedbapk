package com.amien.themoviedb.Movie;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amien.themoviedb.API.ApiService;
import com.amien.themoviedb.Model.MovieDetailModel;
import com.amien.themoviedb.R;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends Fragment {
    ImageView bigPoster;
    TextView bigTitle,budget,ravenue,runtime;

    public MovieDetail() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bigPoster = view.findViewById(R.id.bigPoster);
        bigTitle = view.findViewById(R.id.bigTitle);
        budget = view.findViewById(R.id.budget);
        ravenue = view.findViewById(R.id.ravenue);
        runtime = view.findViewById(R.id.runtime);

        String id = getArguments().getString("id");
        Log.e("detailid", id);

        viewData(id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    public void viewData(String id){
        final String token = "435fad1fce7ee24dffeed67bdd5f69dd";

        Callback<MovieDetailModel> callBack = new Callback<MovieDetailModel>(){
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                if (response.isSuccessful()) {
                    MovieDetailModel movie = response.body();

                    Log.e("poster", movie.getPoster_path());
//                    bigPoster.setImageDrawable(LoadImageFromWebOperations(movie.getPoster_path()).get());
                    bigTitle.setText(movie.getTitle());
                    budget.setText(movie.getBudget());
                    ravenue.setText(movie.getRevenue());
                    runtime.setText(movie.getRuntime());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                Log.e("Fail-getData", t.toString());
            }
        };

        ApiService.endpoint().getDetail(id, token).enqueue(callBack);
    }

    public class invoker implements Executor{

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    public AtomicReference<Drawable> LoadImageFromWebOperations(String url) {
        AtomicReference<Drawable> d = null;
        Executor executor = new invoker();
        executor.execute(() -> {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                d.set(Drawable.createFromStream(is, "src name"));
            } catch (Exception e) {
                Log.e("getImgfail", e.toString());
            }
        });

        return d;
    }
}