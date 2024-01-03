package com.example.moviestore;


import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

public class Presenter implements ModelClass.IFPre {
    ModelClass modelClass;

    private IFView ifView;

    public Presenter(IFView ifView) {
        this.ifView = ifView;
        modelClass = new ModelClass(this);
    }

    void getMovieData(String listType) {
        modelClass.doSomething(listType);

    }


    public void searchMovie(String movieName) {
        modelClass.searchMovie(movieName);
    }

    @Override
    public void setMovies(List<Result> data) {
        if (ifView != null) {
            ifView.setData(data);
        }

    }

    public interface IFView {
        void setData(List<Result> results);
    }
}
