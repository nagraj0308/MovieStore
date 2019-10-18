package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Boolean grid = true;
    RecyclerView rcv;
    String jsonmsg = null;
    String url = null;
    GridLayoutManager gridLayoutManager;
    SearchView searchView;
    LinearLayoutManager linearLayoutManager;
    TabLayout listType;
    int gridSize = 3;
    private String TAG = MainActivity.class.getSimpleName();
    private AsyncTask<Void, Void, Void> net;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listType=findViewById(R.id.listType);
        initALL();
        tabLayoutSection();
    }

    public void initALL() {
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), gridSize);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv = findViewById(R.id.rcv);
        callNowPlayingApi();
    }
    public void tabLayoutSection(){
        listType.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:callNowPlayingApi();break;
                    case 1:callUpcomingApi();break;
                    case 2:callTrendingApi();break;
                    case 3:callTopRatedApi();break;
                    default: break;
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
    protected void onDestroy() {
        if (net != null && !net.isCancelled()) {
            net.cancel(true);
        }

        Log.e("Activity Async ", " ACtivity Destroyed");
        super.onDestroy();
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
                    callSearchApi(query);
                } else {
                    callNowPlayingApi();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null) {
                    callSearchApi(newText);
                } else {
                    callNowPlayingApi();
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
                rcv.setLayoutManager(linearLayoutManager);
                rcv.setAdapter(new ContactsAdapter(jsonmsg));
            } else {
                item.setIcon(R.drawable.linear);
                grid = true;
                rcv.setLayoutManager(gridLayoutManager);
                rcv.setAdapter(new GridAdapter(jsonmsg));

            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void callSearchApi(String string) {
        url = "https://api.themoviedb.org/3/search/movie?api_key=6b8db85ce1e45beacf91815f5643cd76&query=" +string;
        new Net().execute();
    }
    public void callNowPlayingApi() {
        url = "https://api.themoviedb.org/3/movie/now_playing?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
        net = new Net().execute();
    }

    public void callUpcomingApi() {
        url = "https://api.themoviedb.org/3/movie/upcoming?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
        net = new Net().execute();
    }
    public void callTrendingApi() {
        url = "https://api.themoviedb.org/3/movie/popular?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
        net = new Net().execute();
    }

    public void callTopRatedApi() {
        url = "https://api.themoviedb.org/3/movie/top_rated?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
        net = new Net().execute();
    }













    protected class Net extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                jsonmsg = jsonStr;
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (grid) {
                rcv.setLayoutManager(gridLayoutManager);
                rcv.setAdapter(new GridAdapter(jsonmsg));
            } else {
                rcv.setLayoutManager(linearLayoutManager);
                rcv.setAdapter(new ContactsAdapter(jsonmsg));
            }


        }

        @Override
        protected void onCancelled() {
            Log.e("Async Task", " Finished");

            super.onCancelled();
        }
    }
}
