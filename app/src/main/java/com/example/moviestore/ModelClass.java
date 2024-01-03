package com.example.moviestore;

import android.util.Log;

import com.example.moviestore.Networking.MovieApi;
import com.example.moviestore.Networking.RetrofitClass;
import com.example.moviestore.ParcelableClasses.Movie;
import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelClass {
    IFPre ifPre;

    public ModelClass(IFPre ifPre) {
        this.ifPre = ifPre;
    }

    MovieApi movieApi = RetrofitClass.getClient().create(MovieApi.class);


    public void doSomething(String listType) {
        Call<Movie> call = movieApi.getMoviesList(listType);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    ifPre.setMovies(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);

            }

        });
    }


    public void searchMovie(String movieName) {
        Call<Movie> call = movieApi.searchMovie(movieName);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    ifPre.setMovies(response.body().getResults());
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);

            }
        });
    }

    public interface IFPre {
        void setMovies(List<Result> data);
    }
}
