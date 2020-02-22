package com.example.moviestore.data;

import android.os.Parcelable;

import com.example.moviestore.AutoValue.AutoValue_MovieResponse;
import com.example.moviestore.pojo.MoviePojo;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class MovieResponse  implements Parcelable {

    public static MovieResponse create(long page, long totalResults, long totalPages,
                                       List<MoviePojo> results) {
        return new AutoValue_MovieResponse(page,totalResults,totalPages,results);
    }

    public abstract long page();

    public abstract long totalResults();

    public abstract long totalPages();

    public abstract List<MoviePojo> results();
}
