package br.com.infinitytechnology.filmex.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.utils.CircleTransform;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<Person> mPeople;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayoutCompat mLayoutPerson;
        private ImageView mImageViewProfile;
        private TextView mTextViewName;
        private TextView mTextViewPopularity;

        private ViewHolder(View view) {
            super(view);

            mLayoutPerson = view.findViewById(R.id.layout_person);
            mImageViewProfile = view.findViewById(R.id.image_view_profile);
            mTextViewName = view.findViewById(R.id.text_view_name);
            mTextViewPopularity = view.findViewById(R.id.text_view_popularity);
        }
    }

    public PersonAdapter(Context context, View.OnClickListener listener, ArrayList<Person> people) {
        mContext = context;
        mListener = listener;
        mPeople = people;
    }

    @Override
    @NonNull
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);

        return new PersonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        Person person = mPeople.get(position);

        String apiBaseUrlImages = PropertyUtil.property(mContext, "api.base.url.images");

        Picasso.with(mContext)
                .load(apiBaseUrlImages + person.getProfilePath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new CircleTransform())
                .into(holder.mImageViewProfile);

        holder.mTextViewName.setText(person.getName());
        holder.mTextViewPopularity.setText(String.valueOf(person.getPopularity()));

        holder.mLayoutPerson.setTag(position);
        holder.mLayoutPerson.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }
}