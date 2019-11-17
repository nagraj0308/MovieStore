package com.example.moviestore;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.moviestore.ParcelableClasses.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.VH> {
    @State Result[] resultList;

    public GridAdapter(List<Result> results) {
        try {
            resultList = results.toArray(new Result[results.size()]);
        } catch (Exception e) {
        }
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.grid_item, parent, false);
        return new VH(listItem);
    }


    @Override
    public void onBindViewHolder(VH holder, final int position) {
        try {
            String poster_path = resultList[position].getPosterPath();
            holder.movieName.setText(resultList[position].getTitle());
            Picasso.get().load("http://image.tmdb.org/t/p/w500" + poster_path).into(holder.moviePoster);
        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return resultList.length;
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_name) TextView movieName;
        @BindView(R.id.movie_poster) ImageView moviePoster;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                Intent i = new Intent(view.getContext(), Information.class);
                i.putExtra("AboutMovie", serialToParcel(resultList[getAdapterPosition()]));
                view.getContext().startActivity(i);
            });

        }
    }

    private com.example.moviestore.AutoValue.Result serialToParcel(Result movie) {
        com.example.moviestore.AutoValue.Result newMovie;
        newMovie = com.example.moviestore.AutoValue.Result.create(movie.getPopularity(), movie.getId(), movie.isVideo(), movie.getVoteCount(), movie.getVoteAverage(), movie.getTitle(), movie.getReleaseDate(), movie.getOriginalLanguage(), movie.getOriginalTitle(), movie.getGenreIds(), movie.getBackdropPath(), movie.isAdult(), movie.getOverview(), movie.getPosterPath());
        return newMovie;

    }


}

