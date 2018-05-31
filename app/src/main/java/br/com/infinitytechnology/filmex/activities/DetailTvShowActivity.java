package br.com.infinitytechnology.filmex.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.adapters.GenreAdapter;
import br.com.infinitytechnology.filmex.entities.Genre;
import br.com.infinitytechnology.filmex.entities.TvShow;
import br.com.infinitytechnology.filmex.interfaces.TvShowsService;
import br.com.infinitytechnology.filmex.utils.DateUtil;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.ARGS;
import static br.com.infinitytechnology.filmex.activities.MainActivity.ARG_TV_SHOW_ID;

public class DetailTvShowActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    private Integer mTvShowId;
    private TvShow mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.BLACK);
        }

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_genres);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new GenreAdapter(this, new ArrayList<Genre>()));

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        mProgressDialog.show();

        if (savedInstanceState != null) {
            mTvShowId = savedInstanceState.getInt(ARG_TV_SHOW_ID);
        } else {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(ARGS);
            if (bundle != null) {
                mTvShowId = bundle.getInt(ARG_TV_SHOW_ID);
            }
        }

        requestTvShow();
    }

    @Override
    public void onBackPressed() {
        mProgressDialog.dismiss();
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_TV_SHOW_ID, mTvShowId);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void requestTvShow() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage().concat("-").concat(locale.getCountry());
        String apiKey = PropertyUtil.property(this, "api.key");
        TvShowsService service = ServiceGenerator.createService(this, TvShowsService.class);
        Call<TvShow> tvShowCall = service.tvShow(mTvShowId, apiKey, language, null);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if (response.isSuccessful()) {
                    mTvShow = response.body();
                    loadTvShow();
                } else {
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_tv_show));
                    showSnackbar(R.string.error_getting_tv_show);
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private void loadTvShow() {
        if (mTvShow != null) {
            ImageView imageViewBackdrop = (ImageView) findViewById(R.id.image_view_backdrop);
            ImageView imageViewPoster = (ImageView) findViewById(R.id.image_view_poster);
            TextView textViewName = (TextView) findViewById(R.id.text_view_name);
            TextView textViewOriginalName = (TextView) findViewById(R.id.text_view_original_name);
            TextView textViewStatus = (TextView) findViewById(R.id.text_view_status);
            TextView textViewTextOverview = (TextView) findViewById(R.id.text_view_text_overview);
            TextView textViewOverview = (TextView) findViewById(R.id.text_view_overview);
            TextView textViewFirstAirDate = (TextView) findViewById(R.id.text_view_first_air_date);
            TextView textViewVoteAverage = (TextView) findViewById(R.id.text_view_vote_average);
            TextView textViewPopularity = (TextView) findViewById(R.id.text_view_popularity);

            String apiBaseUrlImages = PropertyUtil.property(this, "api.base.url.images");

            Picasso.with(this)
                    .load(apiBaseUrlImages + mTvShow.getBackdropPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageViewBackdrop);

            Picasso.with(this)
                    .load(apiBaseUrlImages + mTvShow.getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageViewPoster);

            textViewName.setText(mTvShow.getName());
            textViewOriginalName.setText(mTvShow.getOriginalName());

            textViewStatus.setText(convertStatus(mTvShow.getStatus()));
            if (mTvShow.getOverview() != null && !mTvShow.getOverview().isEmpty()) {
                textViewOverview.setText(mTvShow.getOverview());
            } else {
                textViewTextOverview.setVisibility(View.GONE);
                textViewOverview.setVisibility(View.GONE);
            }
            textViewFirstAirDate.setText(DateUtil.formatShort(this, mTvShow.getFirstAirDate()));
            textViewVoteAverage.setText(String.valueOf(mTvShow.getVoteAverage()));
            textViewPopularity.setText(String.valueOf(mTvShow.getPopularity()));

            mRecyclerView.setAdapter(new GenreAdapter(this, new ArrayList<>(mTvShow.getGenres())));
        }
        mProgressDialog.hide();
    }

    private String convertStatus(String status) {
        switch (status) {
            case "Returning Series": {
                return getString(R.string.text_returning_series);
            }
            case "Planned": {
                return getString(R.string.text_planned_female);
            }
            case "In Production": {
                return getString(R.string.text_in_production);
            }
            case "Ended": {
                return getString(R.string.text_ended);
            }
            case "Canceled": {
                return getString(R.string.text_canceled_female);
            }
            case "Pilot":
            default: {
                return getString(R.string.text_pilot);
            }
        }
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mFab, resId, Snackbar.LENGTH_LONG).show();
    }
}