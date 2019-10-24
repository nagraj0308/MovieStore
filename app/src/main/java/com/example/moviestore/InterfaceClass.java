package com.example.moviestore;

import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

public class InterfaceClass {
    public interface ForModel{
        interface OnFinishedListener {


            void setMovieData(List<Result> data);

        }

       void doSomething(OnFinishedListener onFinishedListener,String listType);
        void searchMovie(OnFinishedListener onFinishedListener,String movieName);

    }
    public interface ForPresenter{
        void getMovieData(String listType);
        void searchMovie(String movieName);

    }
    public interface ForView{
        void getObject(List<Result> routes);


    }
}
