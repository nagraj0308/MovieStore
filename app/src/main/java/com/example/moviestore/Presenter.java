package com.example.moviestore;

import com.example.moviestore.data.Movie;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class Presenter {
    private CompositeSubscription subscriptions;
    MainActivity mainActivity;
    ModelClass modelClass;
    public Presenter(MainActivity mainActivity1){
        this.mainActivity=mainActivity1;
        modelClass =new ModelClass();
    }



    void getMovieData(String listType){

        Subscription subscription = modelClass.doSomething(listType,this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(bookingDetails -> {
                    setMovieData(bookingDetails);

                }, new ErrorAction() {
                    @Override protected void handleError(Throwable throwable) {
                        if (showContent()) {
                            view.showContent();
                            showError("Error in Connection! Please try Again");
                        }
                    }
                });
        addToSubscription(subscription);

    }
    public void setMovieData (List<Movie> data) {
        if(mainActivity==null) {

        }else{
            mainActivity.getObject(data);
        }
    }

    public void searchMovie(String movieName){
       modelClass.searchMovie(movieName,this);

    }
    protected final void addToSubscription(rx.Subscription subscription) {
        if (null == subscriptions) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }
}
