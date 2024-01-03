package com.example.moviestore.AutoValue;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;


@AutoValue
public abstract class Item implements Parcelable {
    public static Item create(float popularity, int id, boolean video, int voteCount, float voteAverage,
                              String title, String releaseDate, String originalLanguage, String originalTitle,
                              String backdropPath, boolean adult, String overview, String posterPath) {
        return new AutoValue_Item(popularity, id, video, voteCount, voteAverage, title, releaseDate, originalLanguage, originalTitle, backdropPath, adult, overview, posterPath);
    }

    public abstract float popularity();

    public abstract int id();

    public abstract boolean video();

    public abstract int voteCount();

    public abstract float voteAverage();

    public abstract String title();

    public abstract String releaseDate();

    public abstract String originalLanguage();

    public abstract String originalTitle();

    public abstract String backdropPath();

    public abstract boolean adult();

    public abstract String overview();

    public abstract String posterPath();
}
