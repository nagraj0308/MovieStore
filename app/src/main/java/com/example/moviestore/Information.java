package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviestore.AutoValue.Result;
import com.squareup.picasso.Picasso;

public class Information extends AppCompatActivity {
    ImageView backdrop;
    TextView title, certiType, pop, vote_count, vote_avg, overview, release_date, language;
    String sbackdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().setTitle("Information");
        initALL();
    }

    public void initALL() {
        backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.title);
        certiType = findViewById(R.id.certiType);
        pop = findViewById(R.id.popularity);
        vote_count = findViewById(R.id.vote_count);
        vote_avg = findViewById(R.id.vote_avg);
        overview = findViewById(R.id.overview);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
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
