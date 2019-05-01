package br.com.infinitytechnology.filmex.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Genre;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private ArrayList<Genre> mGenres;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;

        private ViewHolder(View view) {
            super(view);

            mTextViewName = view.findViewById(R.id.text_view_name);
        }
    }

    public GenreAdapter(ArrayList<Genre> genres) {
        mGenres = genres;
    }

    @Override
    @NonNull
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_genre, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = mGenres.get(position);

        holder.mTextViewName.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }
}