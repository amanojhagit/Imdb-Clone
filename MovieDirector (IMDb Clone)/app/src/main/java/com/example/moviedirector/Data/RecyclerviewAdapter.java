package com.example.moviedirector.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.MockView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedirector.Activities.moviedetails;
import com.example.moviedirector.Model.model;
import com.example.moviedirector.R;
import com.example.moviedirector.Utils.Constants;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private Context context;
    private List<model> movieList;

    public RecyclerviewAdapter(Context context, List<model> movies) {
    this.context=context;
    movieList=movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model movie=movieList.get(position);
        String poster= Constants.posterurl +movie.getPosterpath();
        holder.title.setText(movie.getTitle());
        holder.date.setText(movie.getReleasedate());

        Picasso.with(context).load(poster).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);

    }



    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView date;
        ImageView poster;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            Context context=ctx;
            title=(TextView)itemView.findViewById(R.id.card_title);
            date=(TextView)itemView.findViewById(R.id.card_rel_date);
            poster= (ImageView) itemView.findViewById(R.id.card_image);
//            Intent nit=new Intent(this, moviedetails.class);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model movies= movieList.get(getAdapterPosition());
//                    Toast.makeText(context,"Row Tapped",Toast.LENGTH_LONG).show();
                    Intent movdel=new Intent(context, moviedetails.class);
                    movdel.putExtra("movie",movies);
                    context.startActivity(movdel);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
}
