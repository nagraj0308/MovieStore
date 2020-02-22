package com.example.moviestore.data;


import android.os.Parcelable;

import com.example.moviestore.AutoValue.AutoValue_Movie;
import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Movie implements Parcelable {
   public static Movie create(double popularity, long id, boolean video, long voteCount, double voteAverage, String title, String releaseDate, String originalLanguage, String originalTitle, List<Long> genreIds, String backdropPath, boolean adult, String overview, String posterPath) {
        return new AutoValue_Movie(popularity, id, video,  voteCount,voteAverage, title, releaseDate,  originalLanguage,  originalTitle, genreIds,  backdropPath,  adult, overview, posterPath);
        }

    public abstract double getPopularity();
    public abstract long getId() ;
    public abstract boolean isVideo() ;
    public abstract long getVoteCount() ;
    public abstract double getVoteAverage() ;
    public abstract String getTitle();
    public abstract String getReleaseDate() ;
    public abstract String getOriginalLanguage() ;
    public abstract String getOriginalTitle();
    public abstract List<Long> getGenreIds() ;
    public abstract String getBackdropPath();
    public abstract boolean isAdult();
    public abstract String getOverview();
    public abstract String getPosterPath() ;
}
