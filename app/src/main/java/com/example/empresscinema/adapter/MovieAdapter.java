package com.example.empresscinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.empresscinema.BookingActivity;
import com.example.empresscinema.R;
import com.example.empresscinema.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private Context context;

    // Konstruktor
    public MovieAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());

        Glide.with(context)
                .load(movie.getImageUrl())
                .placeholder(R.drawable.placeholder)
                .into(holder.poster);

        // Fade-in animáció hozzáadása
        Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.item_fade_in);
        holder.itemView.startAnimation(fadeIn);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, BookingActivity.class);
            intent.putExtra("movie_id", movie.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, genre;
        ImageView poster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            genre = itemView.findViewById(R.id.textGenre);
            poster = itemView.findViewById(R.id.imagePoster);
        }
    }
}



