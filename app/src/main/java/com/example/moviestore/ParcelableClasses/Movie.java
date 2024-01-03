
package com.example.moviestore.ParcelableClasses;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    public final static Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    };

    protected Movie(Parcel in) {
        in.readList(this.results, (Result.class.getClassLoader()));
        this.page = ((int) in.readValue((int.class.getClassLoader())));
        this.totalResults = ((int) in.readValue((int.class.getClassLoader())));
        this.dates = ((Dates) in.readValue((Dates.class.getClassLoader())));
        this.totalPages = ((int) in.readValue((int.class.getClassLoader())));
    }


    public List<Result> getResults() {
        return results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}
