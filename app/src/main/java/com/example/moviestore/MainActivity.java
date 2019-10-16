package com.example.moviestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean grid=true;
    RecyclerView rcv;
    EditText movieName;
    String jsonmsg=null,tvString=null;
    String url=null;
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    int gridSize=3;

    private String TAG = MainActivity.class.getSimpleName();
    private AsyncTask<Void, Void, Void> net;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initALL();
    }

    public void initALL(){

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), gridSize);
        linearLayoutManager=new LinearLayoutManager(getApplicationContext());

        movieName=findViewById(R.id.movie_name);

        rcv=findViewById(R.id.rcv);
        url = "https://api.themoviedb.org/3/movie/top_rated?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
        net =   new Net().execute();

    }

    protected class Net extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.e("Async Task"," STARTED" );
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response

            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {

                jsonmsg=jsonStr;}
            else{
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
            if(grid) {
                rcv.setLayoutManager(gridLayoutManager);
                rcv.setAdapter(new GridAdapter(jsonmsg));
            }else{
                rcv.setLayoutManager(linearLayoutManager);
                rcv.setAdapter(new ContactsAdapter(jsonmsg));
            }


        }

        @Override
        protected void onCancelled() {
            Log.e("Async Task"," Finished" );

            super.onCancelled();
        }
    }

    @Override
    protected void onDestroy() {
        if(net!=null && !net.isCancelled()){
            net.cancel(true);
        }

        Log.e("Activity Async "," ACtivity Destroyed");
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.grid_button) {
            if(grid) {
                item.setIcon(R.drawable.linear);
                grid=false;
                rcv.setLayoutManager(linearLayoutManager);
                rcv.setAdapter(new ContactsAdapter(jsonmsg));
            }
            else {
                item.setIcon(R.drawable.grid);
                grid=true;
                rcv.setLayoutManager(gridLayoutManager);
                rcv.setAdapter(new GridAdapter(jsonmsg));

            }
        }
        if (id == R.id.search_button) {
            if(movieName.getVisibility()==View.GONE){
                movieName.setVisibility(View.VISIBLE);

            }
            else{
                String string =movieName.getText().toString();
                if(string.length()>0) {
                    tvString = string;
                    url = "https://api.themoviedb.org/3/search/movie?api_key=6b8db85ce1e45beacf91815f5643cd76&query=" + tvString;
                    new Net().execute();
                }else{

                    url = "https://api.themoviedb.org/3/movie/top_rated?page=1&language=en-US&api_key=6b8db85ce1e45beacf91815f5643cd76";
                    net =   new Net().execute();

                }

                movieName.setVisibility(View.GONE);
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
