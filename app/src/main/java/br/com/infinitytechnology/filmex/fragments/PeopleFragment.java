package br.com.infinitytechnology.filmex.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.adapters.PersonAdapter;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.entities.ResponseWithPeople;
import br.com.infinitytechnology.filmex.interfaces.PeopleService;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.ARG_PAGE;
import static br.com.infinitytechnology.filmex.activities.MainActivity.ARG_TAG;

public class PeopleFragment extends FragmentPagination implements View.OnClickListener {

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mLayoutConnectivityError;

    private String mTag;
    private Integer mPage;
    private ArrayList<Person> mPeople = new ArrayList<>();

    private OnPeopleFragmentInteractionListener mListener;

    public PeopleFragment() {
    }

    public static PeopleFragment newInstance(String tag) {
        PeopleFragment fragment = new PeopleFragment();
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
            mPage = savedInstanceState.getInt(ARG_PAGE, 1);
        } else if (getArguments() != null) {
            mTag = getArguments().getString(ARG_TAG);
            mPage = getArguments().getInt(ARG_PAGE, 1);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putString(ARG_TAG, mTag);
        savedInstanceState.putInt(ARG_PAGE, mPage);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        mLayoutConnectivityError = view.findViewById(R.id.layout_connectivity_error);

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_people);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        mRecyclerView = view.findViewById(R.id.recycler_view_people);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new PersonAdapter(getActivity(), this, mPeople));

        Button buttonTryAgain = view.findViewById(R.id.button_try_again);
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
        Call<ResponseWithPeople> peopleCall = currentPeopleCall(apiKey, language, mPage);
        peopleCall.enqueue(new Callback<ResponseWithPeople>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWithPeople> call,
                                   @NonNull Response<ResponseWithPeople> response) {
                if (response.isSuccessful()) {
                    mPeople.clear();
                    if (response.body() != null) {
                        mPage = response.body().getPage();
                        Integer totalPages = response.body().getTotalPages();
                        mPeople.addAll(response.body().getResults());
                        onPaginationPressed(new Pair<>(mPage, totalPages));
                    }
                    refreshAdapter();
                } else {
                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    mLayoutConnectivityError.setVisibility(View.VISIBLE);
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_people));
                    showSnackbar(R.string.error_getting_people);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWithPeople> call, @NonNull Throwable t) {
                mSwipeRefreshLayout.setVisibility(View.GONE);
                mLayoutConnectivityError.setVisibility(View.VISIBLE);
                mSwipeRefreshLayout.setRefreshing(false);
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private Call<ResponseWithPeople> currentPeopleCall(String apiKey, String language,
                                                       Integer page) {
        PeopleService service = ServiceGenerator.createService(getActivity(), PeopleService.class);
        return service.personPopular(apiKey, language, page);
    }

    private void refreshAdapter() {
        mLayoutConnectivityError.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setAdapter(new PersonAdapter(getActivity(), this, mPeople));

        mSwipeRefreshLayout.setRefreshing(false);
        mProgressDialog.hide();
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mRecyclerView, resId, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Integer id = (Integer) view.getTag();
        onButtonPressed(mPeople.get(id));
    }

    private void onButtonPressed(Person person) {
        if (mListener != null) {
            mListener.onPeopleFragmentInteraction(person);
        }
    }

    private void onPaginationPressed(Pair<Integer, Integer> pair) {
        if (mListener != null) {
            mListener.onPaginationFragmentInteraction(pair);
        }
    }

    protected FragmentActivity getActivityNonNull() {
        if (super.getActivity() != null) {
            return super.getActivity();
        } else {
            throw new RuntimeException("PeopleFragment null returned from getActivity()");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPeopleFragmentInteractionListener) {
            mListener = (OnPeopleFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPeopleFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void paginationPrevious() {
        mProgressDialog.show();
        mPage--;
        refreshList();
    }

    @Override
    public void paginationNext() {
        mProgressDialog.show();
        mPage++;
        refreshList();
    }

    public interface OnPeopleFragmentInteractionListener {
        void onPeopleFragmentInteraction(Person person);
        void onPaginationFragmentInteraction(Pair<Integer, Integer> pair);
    }
}