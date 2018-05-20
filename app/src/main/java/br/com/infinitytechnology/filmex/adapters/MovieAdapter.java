package br.com.infinitytechnology.filmex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.utils.DateUtil;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<Movie> mMovies = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLayoutMovie;
        public ImageView mImageViewPoster;
        public TextView mTextViewTitle;
        public TextView mTextViewReleaseDate;
        public TextView mTextViewVoteAverage;
        public TextView mTextViewPopularity;

        public ViewHolder(View view) {
            super(view);

            mLayoutMovie = (LinearLayout) view.findViewById(R.id.layout_movie);
            mImageViewPoster = (ImageView) view.findViewById(R.id.image_view_poster);
            mTextViewTitle = (TextView) view.findViewById(R.id.text_view_title);
            mTextViewReleaseDate = (TextView) view.findViewById(R.id.text_view_release_date);
            mTextViewVoteAverage = (TextView) view.findViewById(R.id.text_view_vote_average);
            mTextViewPopularity = (TextView) view.findViewById(R.id.text_view_popularity);
        }
    }

    public MovieAdapter(Context context, View.OnClickListener listener, ArrayList<Movie> movies) {
        mContext = context;
        mListener = listener;
        mMovies = movies;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        String apiBaseUrlImages = PropertyUtil.property(mContext, "api.base.url.images");

        Picasso.with(mContext)
                .load(apiBaseUrlImages + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImageViewPoster);

        holder.mTextViewTitle.setText(movie.getTitle());
        holder.mTextViewReleaseDate.setText(DateUtil.format(mContext, movie.getReleaseDate()));
        holder.mTextViewVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        holder.mTextViewPopularity.setText(String.valueOf(movie.getPopularity()));

        holder.mLayoutMovie.setTag(position);
        holder.mLayoutMovie.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}