package com.example.moviestore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestore.ParcelableClasses.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.State;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.VH> {
    @State
    Result[] resultList;

    public ContactsAdapter(List<Result> results) {
        try {

            resultList = results.toArray(new Result[results.size()]);

        } catch (Exception e) {
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.linear_item, parent, false);
        return new VH(listItem);
    }


    @Override
    public void onBindViewHolder(VH holder, final int position) {
        try {

            String poster_path = resultList[position].getPosterPath();
            Glide.with(holder.itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w500" + poster_path)
                    .centerCrop()
                    .into(holder.poster);
            Boolean adult = resultList[position].isAdult();
            String original_language = resultList[position].getOriginalLanguage();
            holder.language.setText(original_language);
            String release_date = resultList[position].getReleaseDate();
            String year = release_date.substring(0, 4);
            holder.year.setText(year);
            holder.rating.setText(String.valueOf(resultList[position].getVoteAverage()));
            holder.title.setText(resultList[position].getTitle());

            if (adult.equals("false")) {
                holder.certiType.setText("U");
            } else {
                holder.certiType.setText("A");
            }


        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return resultList.length;
    }

    public class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.rating)
        TextView rating;
        @BindView(R.id.year)
        TextView year;
        @BindView(R.id.language)
        TextView language;
        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.certiType)
        TextView certiType;

        VH(View itemView) {
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

