package com.example.moviedirector.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.CaseMap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedirector.Model.model;
import com.example.moviedirector.R;
import com.example.moviedirector.Utils.Constants;

import it.sephiroth.android.library.picasso.Picasso;

public class moviedetails extends AppCompatActivity {
    private TextView titlemovie;
    private TextView yearmovie;
    private TextView biomovie;
    private ImageView backimage;
    private ImageView posterimage;
    private Bundle extras;
    private model moviedtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
        titlemovie=(TextView)findViewById(R.id.detail_title);
        yearmovie=(TextView)findViewById(R.id.detail_year);
        biomovie=(TextView)findViewById(R.id.detail_bio);
        backimage=(ImageView)findViewById(R.id.backimage);
        posterimage=(ImageView)findViewById(R.id.icon_image);

        moviedtl= (model) getIntent().getSerializableExtra("movie");
        String posterdtl= Constants.posterurl +moviedtl.getPosterpath();
        String backpath=Constants.posterurl+moviedtl.getBackgroundpath();
        titlemovie.setText(moviedtl.getTitle());
        yearmovie.setText(moviedtl.getReleasedate());
        biomovie.setText(moviedtl.getBio());
//        backimage.setImageResource(moviedtl.getPosterpath());
        Picasso.with(this).load(posterdtl).placeholder(android.R.drawable.ic_btn_speak_now).into(posterimage);
        Picasso.with(this).load(backpath).placeholder(android.R.drawable.ic_btn_speak_now).into(backimage);

    }
}