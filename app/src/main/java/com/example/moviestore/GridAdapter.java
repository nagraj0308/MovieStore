package com.example.moviestore;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.VH> {
    JSONArray contactList;

    public GridAdapter(String cl) {
        try {
            JSONObject jsonObj = new JSONObject(cl);


            this.contactList = jsonObj.getJSONArray("results");
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
    public void onBindViewHolder(VH holder, int position) {
        try {
            JSONObject c = contactList.getJSONObject(position);
            String poster_path = c.getString("poster_path");
            holder.movieName.setText(c.getString("title"));
            Picasso.get().load("http://image.tmdb.org/t/p/w500" + poster_path).into(holder.moviePoster);


            String adult = c.getString("adult");
            final String stitle=c.getString("title");
            final String sbackdrop=c.getString("backdrop_path");
            final String spop=c.getString("popularity");
            final String svote=c.getString("vote_average");
            final String srdate=c.getString("release_date");
            final String soverview=c.getString("overview");
            final String slang=c.getString("original_language");
            final String svcount=c.getString("vote_count");
            final String scertitype;

            if (adult.equals("false")) {
                scertitype="U";
            } else {
                scertitype="A";
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), Information.class);
                    i.putExtra("stitle",stitle);
                    i.putExtra("sbackdrop",sbackdrop);
                    i.putExtra("scertitype",scertitype);
                    i.putExtra("spop",spop);
                    i.putExtra("svote",svote);
                    i.putExtra("soverview",soverview);
                    i.putExtra("srdate",srdate);
                    i.putExtra("slang",slang);
                    i.putExtra("svcount",svcount);
                    view.getContext().startActivity(i);
                }
            });


        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return contactList.length();
    }

    public class VH extends RecyclerView.ViewHolder {
        public TextView movieName;
        public ImageView moviePoster;


        public VH(View itemView) {
            super(itemView);
            this.moviePoster = itemView.findViewById(R.id.movie_poster);
            this.movieName = itemView.findViewById(R.id.movie_name);

        }
    }
}

