package com.example.moviestore.AutoValue;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.List;
@AutoValue
public abstract class Result implements Parcelable {
   public static Result create(float popularity, int id, boolean video, int voteCount, float voteAverage, String title, String releaseDate, String originalLanguage, String originalTitle, List<Integer> genreIds, String backdropPath, boolean adult, String overview, String posterPath) {
        return new AutoValue_Result(popularity, id, video,  voteCount,voteAverage, title, releaseDate,  originalLanguage,  originalTitle, genreIds,  backdropPath,  adult, overview, posterPath);
        }

    public abstract float getPopularity();
    public abstract int getId() ;
    public abstract boolean isVideo() ;
    public abstract int getVoteCount() ;
    public abstract float getVoteAverage() ;
    public abstract String getTitle();
    public abstract String getReleaseDate() ;
    public abstract String getOriginalLanguage() ;
    public abstract String getOriginalTitle();
    public abstract List<Integer> getGenreIds() ;
    public abstract String getBackdropPath();
    public abstract boolean isAdult();
    public abstract String getOverview();
    public abstract String getPosterPath() ;
}
