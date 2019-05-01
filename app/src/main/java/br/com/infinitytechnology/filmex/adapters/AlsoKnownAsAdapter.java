package br.com.infinitytechnology.filmex.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;

public class AlsoKnownAsAdapter extends RecyclerView.Adapter<AlsoKnownAsAdapter.ViewHolder> {

    private ArrayList<String> mNames;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName;

        private ViewHolder(View view) {
            super(view);

            mTextViewName = view.findViewById(R.id.text_view_name);
        }
    }

    public AlsoKnownAsAdapter(ArrayList<String> names) {
        mNames = names;
    }

    @Override
    @NonNull
    public AlsoKnownAsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_also_known_as, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mNames.get(position);

        holder.mTextViewName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }
}