package com.amien.themoviedb.Movie;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.amien.themoviedb.API.ApiService;
import com.amien.themoviedb.Model.MovieModel;
import com.amien.themoviedb.R;
import com.amien.themoviedb.RecyclerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieList extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = this.getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        String category = getArguments().getString("category");
        viewData(category, recyclerView, context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    public void viewData(String category,RecyclerView recyclerView, Context context){
        final String token = "435fad1fce7ee24dffeed67bdd5f69dd";

        Callback<MovieModel> callBack = new Callback<MovieModel>(){
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    List<MovieModel.Movies> movies = response.body().getResults();

                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(context,movies);
                    recyclerAdapter.setOnItemClickListener((position, v) -> {
                        Bundle bundle = new Bundle();
                        bundle.putString("id", movies.get(position).getId());

                        Fragment fragment = new MovieDetail();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, fragment)
                                .addToBackStack(null)
                                .commit();
                    });
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.e("Fail-getData", t.toString());
            }
        };

        if(category.equals("now_playing")) {
            ApiService.endpoint().getNowPlaying(token).enqueue(callBack);
        }else if(category.equals("popular")){
            ApiService.endpoint().getPopular(token).enqueue(callBack);
        }else if(category.equals("top_rated")){
            ApiService.endpoint().getTopRated(token).enqueue(callBack);
        }else if(category.equals("upcoming")){
            ApiService.endpoint().getUpcoming(token).enqueue(callBack);
        }


    }

    class Movies{
        List<MovieModel.Movies> movies;

        public List<MovieModel.Movies> getMovies() {
            return movies;
        }

        public void setMovies(List<MovieModel.Movies> movies) {
            this.movies = movies;
        }
    }
}