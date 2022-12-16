package com.amien.themoviedb.Model;

import java.util.List;

public class MovieDetailModel {

    private final String img_BaseUrl = "https://image.tmdb.org/t/p/w440_and_h660_face";
    private String title;
    private String id;
    private String revenue;
    private String runtime;
    private String budget;
    private String poster_path;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getBudget() {
        return budget;
    }

    public String getPoster_path() {
        return img_BaseUrl+poster_path;
    }
}
