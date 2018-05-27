package br.com.infinitytechnology.filmex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;

public class AlsoKnownAsAdapter extends RecyclerView.Adapter<AlsoKnownAsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mNames = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName;

        public ViewHolder(View view) {
            super(view);

            mTextViewName = (TextView) view.findViewById(R.id.text_view_name);
        }
    }

    public AlsoKnownAsAdapter(Context context, ArrayList<String> names) {
        mContext = context;
        mNames = names;
    }

    @Override
    public AlsoKnownAsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_also_known_as, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mNames.get(position);

        holder.mTextViewName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }
}