package com.example.moviedirector.Activities;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirector.Data.RecyclerviewAdapter;
import com.example.moviedirector.Model.model;
import com.example.moviedirector.R;
import com.example.moviedirector.Utils.Constants;
import com.example.moviedirector.Utils.prefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     private RecyclerView recyclerView;
     private RecyclerviewAdapter recyclerviewAdapter;
     private RequestQueue queue;
     private List<model> movieListfinal;
     private AlertDialog.Builder alertdialogbuilder;
     private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue= Volley.newRequestQueue(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showsearchdialog();
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        prefs prefs=new prefs(MainActivity.this);
        String searchterm=prefs.getSearch();

        movieListfinal=new ArrayList<>();
        movieListfinal=getMovies(searchterm);

        Log.d("Action Returned Value", String.valueOf(movieListfinal.size()));

        recyclerviewAdapter=new RecyclerviewAdapter(this,movieListfinal);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerviewAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_search) {
            showsearchdialog();
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public List<model> getMovies(String searchterm) {
        movieListfinal.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.Url+ searchterm,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Action","Entered try");
                    JSONArray movieArray = response.getJSONArray("results");
                    for (int i = 0; i < movieArray.length(); i++) {
                        JSONObject movieobj = movieArray.getJSONObject(i);
                        model movie = new model();
                        movie.setTitle(movieobj.getString("title"));
                        movie.setReleasedate(movieobj.getString("release_date"));
                        movie.setBio(movieobj.getString("overview"));
                        movie.setPosterpath(movieobj.getString("poster_path"));
                        movie.setBackgroundpath(movieobj.getString("backdrop_path"));
                        Log.d("Title",movie.getTitle());
                        movieListfinal.add(movie);
//                        Log.d("Action For loop", String.valueOf(movieList.size()));
                    }

                    Log.d("Action for loop out", String.valueOf(movieListfinal.size()));
//                    Log.d("Action",String.valueOf(movieListfinal.get()))
//                    return movieList;
                    recyclerviewAdapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Action", "ran into exception");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Action", "Ran into error response");
            }
        });
        Log.d("Action 11", String.valueOf(movieListfinal.size()));
        queue.add(jsonObjectRequest);
        Log.d("Action before return", String.valueOf(movieListfinal.size()));
        return movieListfinal;
    }

    public void showsearchdialog(){
        alertdialogbuilder=new AlertDialog.Builder(this);
        View dialogview=getLayoutInflater().inflate(R.layout.search_dialog,null);

        EditText searchmovie=(EditText)dialogview.findViewById(R.id.Search_item);
        Button submmitbutton=(Button)dialogview.findViewById(R.id.search_submit_button);
         alertdialogbuilder.setView(dialogview);
         alertDialog=alertdialogbuilder.create();
         alertDialog.show();

         submmitbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 prefs pref=new prefs(MainActivity.this);

                 if (!searchmovie.getText().toString().isEmpty()){
                     String search=searchmovie.getText().toString();
                     pref.setSearch(search);
                     movieListfinal.clear();

                     getMovies(search);
//                     recyclerviewAdapter.notifyDataSetChanged();
                 }
                 alertDialog.dismiss();

             }
         });

    }
}