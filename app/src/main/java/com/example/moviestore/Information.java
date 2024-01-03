package com.example.moviestore;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviestore.AutoValue.Item;
import com.example.moviestore.ParcelableClasses.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Information extends AppCompatActivity {
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.certiType)
    TextView certiType;
    @BindView(R.id.popularity)
    TextView pop;
    @BindView(R.id.vote_count)
    TextView vote_count;
    @BindView(R.id.vote_avg)
    TextView vote_avg;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.language)
    TextView language;

    String sbackdrop = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Information");
        initALL();
    }

    public void initALL() {
        try {
            Item item = serialToParcel((Result) getIntent().getExtras().getSerializable("AboutMovie"));
            String string;
            title.setText(item.title());
            string = item.adult() ? "A" : "U";
            certiType.setText(string);
            pop.setText(String.valueOf(item.popularity()));
            vote_avg.setText(String.valueOf(item.voteAverage()));
            overview.setText(item.overview());
            vote_count.setText(String.valueOf(item.voteCount()));
            language.setText(item.originalLanguage());
            release_date.setText(item.releaseDate());
            sbackdrop = item.backdropPath();
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w780" + sbackdrop)
                    .into(backdrop);
        } catch (Exception e) {
            Log.e("Error", e + "", e);

        }
    }

    private Item serialToParcel(Result movie) {
        return Item.create(movie.getPopularity(), movie.getId(), movie.isVideo(), movie.getVoteCount(), movie.getVoteAverage(), movie.getTitle(), movie.getReleaseDate(), movie.getOriginalLanguage(), movie.getOriginalTitle(), movie.getBackdropPath(), movie.isAdult(), movie.getOverview(), movie.getPosterPath());
    }


}
