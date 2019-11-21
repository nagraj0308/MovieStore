package com.example.moviestore;

import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

public class Presenter implements InterfaceClass.ForPresenter,InterfaceClass.ForModel.OnFinishedListener {
    InterfaceClass.ForView viewInterface;
    InterfaceClass.ForModel modelView;

    public Presenter(InterfaceClass.ForView viewInterface) {
        this.viewInterface = viewInterface;
        modelView = new ModelClass();
    }
    @Override
    public void getMovieData(String listType){
        modelView.doSomething(this,listType);

    }
    @Override
    public void setMovieData (List<Result> data) {
        viewInterface.getObject(data);
    }
    @Override
    public void searchMovie(String movieName){
       modelView.searchMovie(this,movieName);

    }
}
