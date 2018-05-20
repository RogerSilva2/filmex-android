package br.com.infinitytechnology.filmex.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.adapters.MovieAdapter;
import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.entities.ResponseWithMovies;
import br.com.infinitytechnology.filmex.interfaces.MoviesService;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_NOW_PLAYING;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_POPULAR_MOVIES;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_TOP_RATED_MOVIES;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_UPCOMING;

public class MoviesFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_TAG = "TAG";

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private String mTag;
    private ArrayList<Movie> mMovies = new ArrayList<>();

    private OnMoviesFragmentInteractionListener mListener;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance(String tag) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mTag = savedInstanceState.getString(ARG_TAG);
        } else if (getArguments() != null) {
            mTag = getArguments().getString(ARG_TAG);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(ARG_TAG, mTag);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_movies);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_movies);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        mProgressDialog.show();
        refreshList();

        return view;
    }

    private void refreshList() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage().concat("-").concat(locale.getCountry());
        String apiKey = PropertyUtil.property(getActivity(), "api.key");
        Call<ResponseWithMovies> moviesCall =
                currentMoviesCall(apiKey, language, 1, null);
        moviesCall.enqueue(new Callback<ResponseWithMovies>() {
            @Override
            public void onResponse(Call<ResponseWithMovies> call,
                                   Response<ResponseWithMovies> response) {
                if (response.isSuccessful()) {
                    mMovies.clear();
                    mMovies.addAll(response.body().getResults());
                    refreshAdapter();
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_movies));
                    showSnackbar(R.string.error_getting_movies);
                }
            }

            @Override
            public void onFailure(Call<ResponseWithMovies> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private Call<ResponseWithMovies> currentMoviesCall(String apikey, String language, Integer page,
                                                       String region) {
        MoviesService service = ServiceGenerator.createService(getActivity(), MoviesService.class);
        switch (mTag) {
            case TAG_FRAGMENT_TOP_RATED_MOVIES: {
                return service.movieTopRated(apikey, language, page, region);
            }
            case TAG_FRAGMENT_UPCOMING: {
                return service.movieUpcoming(apikey, language, page, region);
            }
            case TAG_FRAGMENT_NOW_PLAYING: {
                return service.movieNowPlaying(apikey, language, page, region);
            }
            case TAG_FRAGMENT_POPULAR_MOVIES:
            default: {
                return service.moviePopular(apikey, language, page, region);
            }
        }
    }

    private void refreshAdapter() {
        MovieAdapter adapter = new MovieAdapter(getActivity(), this, mMovies);
        mRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setRefreshing(false);
        mProgressDialog.hide();
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mRecyclerView, resId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Integer id = (Integer) view.getTag();
        onButtonPressed(mMovies.get(id));
    }

    public void onButtonPressed(Movie movie) {
        if (mListener != null) {
            mListener.onMoviesFragmentInteraction(movie);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMoviesFragmentInteractionListener) {
            mListener = (OnMoviesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMoviesFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMoviesFragmentInteractionListener {
        void onMoviesFragmentInteraction(Movie movie);
    }
}