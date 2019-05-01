package br.com.infinitytechnology.filmex.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.adapters.TvShowAdapter;
import br.com.infinitytechnology.filmex.entities.ResponseWithTvShows;
import br.com.infinitytechnology.filmex.entities.TvShow;
import br.com.infinitytechnology.filmex.interfaces.TvShowsService;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_AIRING_TODAY;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_ON_TV;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_POPULAR_TV_SHOWS;
import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_TOP_RATED_TV_SHOWS;

public class TvShowsFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_TAG = "TAG";

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mLayoutConnectivityError;

    private String mTag;
    private ArrayList<TvShow> mTvShow = new ArrayList<>();

    private OnTvShowsFragmentInteractionListener mListener;

    public TvShowsFragment() {
    }

    public static TvShowsFragment newInstance(String tag) {
        TvShowsFragment fragment = new TvShowsFragment();
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
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putString(ARG_TAG, mTag);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_shows, container, false);

        mLayoutConnectivityError = view.findViewById(R.id.layout_connectivity_error);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_tv_shows);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        mRecyclerView = view.findViewById(R.id.recycler_view_tv_shows);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new TvShowAdapter(getActivity(), this, mTvShow));

        Button buttonTryAgain = view.findViewById(R.id.try_again);
        buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.show();
                refreshList();
            }
        });

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
        String apiKey = PropertyUtil.property(getActivityNonNull(), "api.key");
        Call<ResponseWithTvShows> tvShowsCall = currentTvShowsCall(apiKey, language, 1);
        tvShowsCall.enqueue(new Callback<ResponseWithTvShows>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWithTvShows> call,
                                   @NonNull Response<ResponseWithTvShows> response) {
                if (response.isSuccessful()) {
                    mTvShow.clear();
                    if (response.body() != null)  {
                        mTvShow.addAll(response.body().getResults());
                    }
                    refreshAdapter();
                } else {
                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    mLayoutConnectivityError.setVisibility(View.VISIBLE);
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_tv_shows));
                    showSnackbar(R.string.error_getting_tv_shows);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWithTvShows> call, @NonNull Throwable t) {
                mSwipeRefreshLayout.setVisibility(View.GONE);
                mLayoutConnectivityError.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private Call<ResponseWithTvShows> currentTvShowsCall(String apiKey, String language,
                                                         Integer page) {
        TvShowsService service =
                ServiceGenerator.createService(getActivity(), TvShowsService.class);
        switch (mTag) {
            case TAG_FRAGMENT_TOP_RATED_TV_SHOWS: {
                return service.tvShowTopRated(apiKey, language, page);
            }
            case TAG_FRAGMENT_ON_TV: {
                return service.tvShowOnTheAir(apiKey, language, page);
            }
            case TAG_FRAGMENT_AIRING_TODAY: {
                return service.tvShowAiringToday(apiKey, language, page);
            }
            case TAG_FRAGMENT_POPULAR_TV_SHOWS:
            default: {
                return service.tvShowPopular(apiKey, language, page);
            }
        }
    }

    private void refreshAdapter() {
        mLayoutConnectivityError.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(new TvShowAdapter(getActivity(), this, mTvShow));

        mSwipeRefreshLayout.setRefreshing(false);
        mProgressDialog.hide();
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mRecyclerView, resId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Integer id = (Integer) view.getTag();
        onButtonPressed(mTvShow.get(id));
    }

    public void onButtonPressed(TvShow tvShow) {
        if (mListener != null) {
            mListener.onTvShowsFragmentInteraction(tvShow);
        }
    }

    protected FragmentActivity getActivityNonNull() {
        if (super.getActivity() != null) {
            return super.getActivity();
        } else {
            throw new RuntimeException("TvShowsFragment null returned from getActivity()");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTvShowsFragmentInteractionListener) {
            mListener = (OnTvShowsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTvShowsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnTvShowsFragmentInteractionListener {
        void onTvShowsFragmentInteraction(TvShow tvShow);
    }
}