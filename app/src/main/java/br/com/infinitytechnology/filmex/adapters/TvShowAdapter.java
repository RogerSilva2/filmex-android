package br.com.infinitytechnology.filmex.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
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
import br.com.infinitytechnology.filmex.entities.TvShow;
import br.com.infinitytechnology.filmex.utils.DateUtil;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<TvShow> mTvShows;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLayoutTvShow;
        private ImageView mImageViewPoster;
        private TextView mTextViewName;
        private TextView mTextViewFirstAirDate;
        private TextView mTextViewVoteAverage;
        private TextView mTextViewPopularity;

        private ViewHolder(View view) {
            super(view);

            mLayoutTvShow = view.findViewById(R.id.layout_tv_show);
            mImageViewPoster = view.findViewById(R.id.image_view_poster);
            mTextViewName = view.findViewById(R.id.text_view_name);
            mTextViewFirstAirDate = view.findViewById(R.id.text_view_first_air_date);
            mTextViewVoteAverage = view.findViewById(R.id.text_view_vote_average);
            mTextViewPopularity = view.findViewById(R.id.text_view_popularity);
        }
    }

    public TvShowAdapter(Context context, View.OnClickListener listener,
                         ArrayList<TvShow> tvShows) {
        mContext = context;
        mListener = listener;
        mTvShows = tvShows;
    }

    @Override
    @NonNull
    public TvShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tv_show, parent, false);

        return new TvShowAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, int position) {
        TvShow tvShow = mTvShows.get(position);

        String apiBaseUrlImages = PropertyUtil.property(mContext, "api.base.url.images");

        Picasso.with(mContext)
                .load(apiBaseUrlImages + tvShow.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mImageViewPoster);

        holder.mTextViewName.setText(tvShow.getName());
        holder.mTextViewFirstAirDate.setText(DateUtil.format(mContext, tvShow.getFirstAirDate()));
        holder.mTextViewVoteAverage.setText(String.valueOf(tvShow.getVoteAverage()));
        holder.mTextViewPopularity.setText(String.valueOf(tvShow.getPopularity()));

        holder.mLayoutTvShow.setTag(position);
        holder.mLayoutTvShow.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }
}