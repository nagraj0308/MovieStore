package com.example.moviestore.Networking;

import com.example.moviestore.ParcelableClasses.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/movie/{listType}?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76")
    Call<Movie> getMoviesList(@Path("listType") String listType);

    @GET("3/search/movie?api_key=6b8db85ce1e45beacf91815f5643cd76")
    Call<Movie> searchMovie(@Query("query") String movieName);

}
