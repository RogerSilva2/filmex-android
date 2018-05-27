package br.com.infinitytechnology.filmex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Genre> mGenres = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName;

        public ViewHolder(View view) {
            super(view);

            mTextViewName = (TextView) view.findViewById(R.id.text_view_name);
        }
    }

    public GenreAdapter(Context context, ArrayList<Genre> genres) {
        mContext = context;
        mGenres = genres;
    }

    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Genre genre = mGenres.get(position);

        holder.mTextViewName.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }
}