package com.example.moviestore.Networking;

import com.example.moviestore.pojo.MovieResponsePojo;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("3/movie/{listType}?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76")
    Observable<MovieResponsePojo> getMoviesList(@Path("listType") String listType);

    @GET("3/search/movie?api_key=6b8db85ce1e45beacf91815f5643cd76")
    Observable<MovieResponsePojo> searchMovie(@Query("query") String movieName);



    //https://api.themoviedb.org/3/search/movie?api_key=6b8db85ce1e45beacf91815f5643cd76&query=ironman

}
