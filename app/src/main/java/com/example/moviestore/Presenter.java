package com.example.moviestore;


import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

public class Presenter {
    ModelClass modelClass;

    private IFView ifView;

    public Presenter(IFView ifView) {
        this.ifView = ifView;
        modelClass = new ModelClass();
    }

    void getMovieData(String listType) {
        modelClass.doSomething(listType, this);

    }

    public void setMovieData(List<Result> data) {
        if (ifView != null) {
            ifView.setData(data);
        }
    }

    public void searchMovie(String movieName) {
        modelClass.searchMovie(movieName, this);

    }

    public interface IFView {
        void setData(List<Result> results);
    }
}
