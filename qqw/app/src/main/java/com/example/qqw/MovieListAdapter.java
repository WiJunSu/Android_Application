package com.example.qqw;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieListAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> movieList;

    View dialogView;
    Integer ImgID[] = {R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.e, R.drawable.f, R.drawable.g,
            R.drawable.h, R.drawable.k, R.drawable.l, R.drawable.m,
            R.drawable.d, R.drawable.i, R.drawable.n, R.drawable.j,
            R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r,
            R.drawable.s,R.drawable.t,R.drawable.u, R.drawable.v, R.drawable.x};

    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;

    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup parent) {
        View v = View.inflate(context, R.layout.fragment_action, null);
        TextView movieName = (TextView) v.findViewById(R.id.movieName);
        ImageView movieImage = (ImageView) v.findViewById(R.id.movieImage);

        TextView moviekind = (TextView) v.findViewById(R.id.moviekind);
        moviekind.setText(movieList.get(i).getMoviekind() + " 영화");


        movieImage.setImageResource(ImgID[movieList.get(i).getMovieID() - 1]);
        movieName.setText(movieList.get(i).getMovieTitle());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VActivity.class);

                intent.putExtra("movieID", String.valueOf(movieList.get(i).getMovieID()));
                intent.putExtra("movieTitle", movieList.get(i).getMovieTitle());
                intent.putExtra("movieDirector", movieList.get(i).getMovieDirector());
                intent.putExtra("movieActor", movieList.get(i).getMovieActor());
                intent.putExtra("movieStory", movieList.get(i).getMovieStory());
                intent.putExtra("movieKind", movieList.get(i).getMoviekind());

                context.startActivity(intent);
            }
        });

        v.setTag(movieList.get(i).getMovieID());
        return v;
    }
}
