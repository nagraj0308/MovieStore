package com.example.moviestore;

import android.util.Log;

import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

public class Presenter {
    MainActivity mainActivity;
    ModelClass modelClass;
    public Presenter(MainActivity mainActivity1){
        this.mainActivity=mainActivity1;
        modelClass =new ModelClass();
    }

    void getMovieData(String listType){
        modelClass.doSomething(listType,this);

    }
    public void setMovieData (List<Result> data) {
        if(mainActivity==null) {
            Log.e("NagRaj","mainActivity is null");

        }else{
            mainActivity.getObject(data);
        }
    }

    public void searchMovie(String movieName){
       modelClass.searchMovie(movieName,this);

    }
}
