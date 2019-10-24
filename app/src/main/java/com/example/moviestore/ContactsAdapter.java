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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.VH> {
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
            Picasso.get().load("http://image.tmdb.org/t/p/w500" + poster_path).into(holder.poster);
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
        TextView title;
        TextView rating;
        TextView year;
        TextView language;
        ImageView poster;
        TextView certiType;

        VH(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.year = itemView.findViewById(R.id.year);
            this.language = itemView.findViewById(R.id.language);
            this.rating = itemView.findViewById(R.id.rating);
            this.poster = itemView.findViewById(R.id.poster);
            this.certiType = itemView.findViewById(R.id.certiType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), Information.class);
                    i.putExtra("AboutMovie", serialToParcel(resultList[getAdapterPosition()]));
                    view.getContext().startActivity(i);
                }
            });


        }
    }

    private com.example.moviestore.AutoValue.Result serialToParcel(Result movie){
        com.example.moviestore.AutoValue.Result newMovie;
        newMovie= com.example.moviestore.AutoValue.Result.create(movie.getPopularity(),movie.getId(),movie.isVideo(),movie.getVoteCount(),movie.getVoteAverage(),movie.getTitle(),movie.getReleaseDate(),movie.getOriginalLanguage(),movie.getOriginalTitle(),movie.getGenreIds(),movie.getBackdropPath(),movie.isAdult(),movie.getOverview(),movie.getPosterPath());
        return newMovie;

    }
}

