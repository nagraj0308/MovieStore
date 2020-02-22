package com.example.moviestore;

import com.example.moviestore.Networking.MovieApi;
import com.example.moviestore.Networking.RetrofitClass;
import com.example.moviestore.pojo.MovieResponsePojo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ModelClass {
    Presenter presenter;
    MovieApi movieApi = RetrofitClass.getClient().create(MovieApi.class);

    public void doSomething( String listType,Presenter presenter1) {
        this.presenter=presenter1;
        Observable<MovieResponsePojo> moviesList=movieApi.getMoviesList(listType);
        moviesList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.)
                .subscribe(this::handleResults, this::handleError);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    presenter.setMovieData(response.body().getResults());
                } else {

                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("TAG", t.getMessage(), t);

            }

        });
    }



    public void searchMovie(String movieName,Presenter presenter1) {
        this.presenter=presenter1;
        Call<Movie> call = movieApi.searchMovie(movieName);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.e("Response Code", response.code() + "");
                if (response.code() == 200) {
                    presenter.setMovieData(response.body().getResults());
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
