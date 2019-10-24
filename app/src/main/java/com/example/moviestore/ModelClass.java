package com.example.moviestore;

import android.util.Log;
import com.example.moviestore.Networking.MovieApi;
import com.example.moviestore.Networking.RetrofitClass;
import com.example.moviestore.ParcelableClasses.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelClass implements InterfaceClass.ForModel {

    MovieApi movieApi = RetrofitClass.getClient().create(MovieApi.class);


    @Override
    public void doSomething(final OnFinishedListener onFinishedListener, String listType) {
        Call<Movie> call = movieApi.getMoviesList(listType);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    onFinishedListener.setMovieData(response.body().getResults());
                } else {

                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);

            }

        });
    }

    @Override
    public void searchMovie(final OnFinishedListener onFinishedListener, String movieName) {
        Call<Movie> call = movieApi.searchMovie(movieName);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    onFinishedListener.setMovieData(response.body().getResults());
                } else {
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);

            }
        });
    }
}
