package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moviestore.ParcelableClasses.Result;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Boolean grid = true;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    List<Result> resultList, latestResultList;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    Presenter presenter;
    SearchView searchView;
    @BindView(R.id.listType)
    TabLayout listType;
    int gridSize = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initALL();
        tabLayoutSection();
    }

    public void initALL() {
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), gridSize);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        presenter = new Presenter(this);
        presenter.getMovieData("popular");
    }

    public void tabLayoutSection() {
        listType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        presenter.getMovieData("now_playing");
                        break;
                    case 1:
                        presenter.getMovieData("upcoming");
                        break;
                    case 2:
                        presenter.getMovieData("popular");
                        break;
                    case 3:
                        presenter.getMovieData("top_rated");
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    presenter.searchMovie(query);
                } else {
                    presenter.getMovieData("now_playing");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null) {
                    presenter.searchMovie(newText);
                } else {
                    presenter.getMovieData("now_playing");
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.grid_button) {
            if (grid) {
                item.setIcon(R.drawable.grid);
                grid = false;
                callRecycler();
            } else {
                item.setIcon(R.drawable.linear);
                grid = true;
                callRecycler();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void getObject(List<Result> results) {
        resultList = results;
        latestResultList = new ArrayList<>();
        latestResultList.addAll(results);
        callRecycler();
    }

    public void callRecycler() {
        if (grid) {
            rcv.setLayoutManager(gridLayoutManager);
            rcv.setAdapter(new GridAdapter(latestResultList));
        } else {
            rcv.setLayoutManager(linearLayoutManager);
            rcv.setAdapter(new ContactsAdapter(latestResultList));

        }

    }

}
