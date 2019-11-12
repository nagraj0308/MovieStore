package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviestore.AutoValue.Result;
import com.squareup.picasso.Picasso;

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
    String sbackdrop;

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
            Result result = getIntent().getExtras().getParcelable("AboutMovie");
            String string;
            title.setText(result.getTitle());
            string = result.isAdult() ? "A" : "U";
            certiType.setText(string);
            pop.setText(String.valueOf(result.getPopularity()));
            vote_avg.setText(String.valueOf(result.getVoteAverage()));
            overview.setText(result.getOverview());
            vote_count.setText(String.valueOf(result.getVoteCount()));
            language.setText(result.getOriginalLanguage());
            release_date.setText(result.getReleaseDate());
            sbackdrop = result.getBackdropPath();
            Picasso.get().load("http://image.tmdb.org/t/p/w780" + sbackdrop).into(backdrop);
        } catch (Exception e) {
            Log.e("Error", e + "", e);

        }
    }


}
