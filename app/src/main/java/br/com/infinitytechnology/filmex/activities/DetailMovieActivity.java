package br.com.infinitytechnology.filmex.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.interfaces.MoviesService;
import br.com.infinitytechnology.filmex.utils.DateUtil;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.ARGS;
import static br.com.infinitytechnology.filmex.activities.MainActivity.ARG_MOVIE_ID;

public class DetailMovieActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    private Integer mMovieId;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
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

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view_genres);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new GenreAdapter(new ArrayList<Genre>()));

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        mProgressDialog.show();

        if (savedInstanceState != null) {
            mMovieId = savedInstanceState.getInt(ARG_MOVIE_ID);
        } else {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(ARGS);
            if (bundle != null) {
                mMovieId = bundle.getInt(ARG_MOVIE_ID);
            }
        }

        requestMovie();
    }

    @Override
    public void onBackPressed() {
        mProgressDialog.dismiss();
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_MOVIE_ID, mMovieId);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void requestMovie() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage().concat("-").concat(locale.getCountry());
        String apiKey = PropertyUtil.property(this, "api.key");
        MoviesService service = ServiceGenerator.createService(this, MoviesService.class);
        Call<Movie> movieCall = service.movie(mMovieId, apiKey, language, null);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.isSuccessful()) {
                    mMovie = response.body();
                    loadMovie();
                } else {
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_movie));
                    showSnackbar(R.string.error_getting_movie);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private void loadMovie() {
        if (mMovie != null) {
            ImageView imageViewBackdrop = findViewById(R.id.image_view_backdrop);
            ImageView imageViewPoster = findViewById(R.id.image_view_poster);
            TextView textViewTitle = findViewById(R.id.text_view_title);
            TextView textViewOriginalTitle = findViewById(R.id.text_view_original_title);
            TextView textViewStatus = findViewById(R.id.text_view_status);
            TextView textViewTextOverview = findViewById(R.id.text_view_text_overview);
            TextView textViewOverview = findViewById(R.id.text_view_overview);
            TextView textViewReleaseDate = findViewById(R.id.text_view_release_date);
            TextView textViewVoteAverage = findViewById(R.id.text_view_vote_average);
            TextView textViewPopularity = findViewById(R.id.text_view_popularity);

            String apiBaseUrlImages =
                    PropertyUtil.property(this, "api.base.url.images");

            Picasso.with(this)
                    .load(apiBaseUrlImages + mMovie.getBackdropPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageViewBackdrop);

            Picasso.with(this)
                    .load(apiBaseUrlImages + mMovie.getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageViewPoster);

            textViewTitle.setText(mMovie.getTitle());
            textViewOriginalTitle.setText(mMovie.getOriginalTitle());

            textViewStatus.setText(convertStatus(mMovie.getStatus()));
            if (mMovie.getOverview() != null && !mMovie.getOverview().isEmpty()) {
                textViewOverview.setText(mMovie.getOverview());
            } else {
                textViewTextOverview.setVisibility(View.GONE);
                textViewOverview.setVisibility(View.GONE);
            }
            textViewReleaseDate.setText(
                    DateUtil.formatShort(this, mMovie.getReleaseDate()));
            textViewVoteAverage.setText(String.valueOf(mMovie.getVoteAverage()));
            textViewPopularity.setText(String.valueOf(mMovie.getPopularity()));

            mRecyclerView.setAdapter(new GenreAdapter(new ArrayList<>(mMovie.getGenres())));
        }
        mProgressDialog.hide();
    }

    private String convertStatus(String status) {
        switch (status) {
            case "Planned": {
                return getString(R.string.text_planned);
            }
            case "In Production": {
                return getString(R.string.text_in_production);
            }
            case "Post Production": {
                return getString(R.string.text_post_production);
            }
            case "Released": {
                return getString(R.string.text_released);
            }
            case "Canceled": {
                return getString(R.string.text_canceled);
            }
            case "Rumored":
            default: {
                return getString(R.string.text_rumored);
            }
        }
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mFab, resId, Snackbar.LENGTH_LONG).show();
    }
}