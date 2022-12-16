package com.amien.themoviedb.Model;

import java.util.List;

public class MovieModel {

    private List<Movies> results;

    public List<Movies> getResults() {
        return results;
    }

    public class Movies{
        private String id;
        private String poster_path;
        private String title;
        private String release_date;
        private String overview;

        public String getId() {
            return id;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public String getTitle() {
            return title;
        }

        public String getRelease_date() {
            return release_date;
        }

        public String getOverview() {
            return overview;
        }

        @Override
        public String toString() {
            return "Movies{" +
                    "poster_path='" + poster_path + '\'' +
                    ", title='" + title + '\'' +
                    ", release_date='" + release_date + '\'' +
                    ", overview='" + overview + '\'' +
                    '}';
        }
    }
}
