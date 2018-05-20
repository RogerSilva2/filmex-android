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
import br.com.infinitytechnology.filmex.adapters.PersonAdapter;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.entities.ResponseWithPeople;
import br.com.infinitytechnology.filmex.interfaces.PeopleService;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.TAG_FRAGMENT_POPULAR_PEOPLE;

public class PeopleFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_TAG = "TAG";

    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private String mTag;
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
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_people);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_people);
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
        Call<ResponseWithPeople> peopleCall = currentPeopleCall(apiKey, language, 1);
        peopleCall.enqueue(new Callback<ResponseWithPeople>() {
            @Override
            public void onResponse(Call<ResponseWithPeople> call,
                                   Response<ResponseWithPeople> response) {
                if (response.isSuccessful()) {
                    mPeople.clear();
                    mPeople.addAll(response.body().getResults());
                    refreshAdapter();
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_people));
                    showSnackbar(R.string.error_getting_people);
                }
            }

            @Override
            public void onFailure(Call<ResponseWithPeople> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private Call<ResponseWithPeople> currentPeopleCall(String apikey, String language,
                                                       Integer page) {
        PeopleService service = ServiceGenerator.createService(getActivity(), PeopleService.class);
        switch (mTag) {
            case TAG_FRAGMENT_POPULAR_PEOPLE:
            default: {
                return service.personPopular(apikey, language, page);
            }
        }
    }

    private void refreshAdapter() {
        PersonAdapter adapter = new PersonAdapter(getActivity(), this, mPeople);
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
        onButtonPressed(mPeople.get(id));
    }

    public void onButtonPressed(Person person) {
        if (mListener != null) {
            mListener.onPeopleFragmentInteraction(person);
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

    public interface OnPeopleFragmentInteractionListener {
        void onPeopleFragmentInteraction(Person person);
    }
}