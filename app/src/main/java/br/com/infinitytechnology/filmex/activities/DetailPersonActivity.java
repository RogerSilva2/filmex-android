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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.adapters.AlsoKnownAsAdapter;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.interfaces.PeopleService;
import br.com.infinitytechnology.filmex.utils.CircleTransform;
import br.com.infinitytechnology.filmex.utils.DateUtil;
import br.com.infinitytechnology.filmex.utils.PropertyUtil;
import br.com.infinitytechnology.filmex.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.infinitytechnology.filmex.activities.MainActivity.ARGS;
import static br.com.infinitytechnology.filmex.activities.MainActivity.ARG_PERSON_ID;

public class DetailPersonActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    private Integer mPersonId;
    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);
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

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_also_known_as);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(new AlsoKnownAsAdapter(this, new ArrayList<String>()));

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("");
        mProgressDialog.setMessage(getString(R.string.message_loading));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        mProgressDialog.show();

        if (savedInstanceState != null) {
            mPersonId = savedInstanceState.getInt(ARG_PERSON_ID);
        } else {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(ARGS);
            if (bundle != null) {
                mPersonId = bundle.getInt(ARG_PERSON_ID);
            }
        }

        requestPerson();
    }

    @Override
    public void onBackPressed() {
        mProgressDialog.dismiss();
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_PERSON_ID, mPersonId);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void requestPerson() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage().concat("-").concat(locale.getCountry());
        String apiKey = PropertyUtil.property(this, "api.key");
        PeopleService service = ServiceGenerator.createService(this, PeopleService.class);
        Call<Person> personCall = service.person(mPersonId, apiKey, language, null);
        personCall.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    mPerson = response.body();
                    loadPerson();
                } else {
                    mProgressDialog.hide();
                    Log.i(getString(R.string.app_name), getString(R.string.error_getting_person));
                    showSnackbar(R.string.error_getting_person);
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                mProgressDialog.hide();
                Log.e(getString(R.string.app_name), getString(R.string.error_server_unavailable), t);
                showSnackbar(R.string.error_server_unavailable);
            }
        });
    }

    private void loadPerson() {
        if (mPerson != null) {
            ImageView imageViewProfile = (ImageView) findViewById(R.id.image_view_profile);
            TextView textViewName = (TextView) findViewById(R.id.text_view_name);
            TextView textViewPopularity = (TextView) findViewById(R.id.text_view_popularity);
            LinearLayout layoutBirthday = (LinearLayout) findViewById(R.id.layout_birthday);
            TextView textViewBirthday = (TextView) findViewById(R.id.text_view_birthday);
            TextView textViewTextPlaceOfBirth = (TextView) findViewById(R.id.text_view_text_place_of_birth);
            TextView textViewPlaceOfBirth = (TextView) findViewById(R.id.text_view_place_of_birth);
            TextView textViewTextBiography = (TextView) findViewById(R.id.text_view_text_biography);
            TextView textViewBiography = (TextView) findViewById(R.id.text_view_biography);

            String apiBaseUrlImages = PropertyUtil.property(this, "api.base.url.images");

            Picasso.with(this)
                    .load(apiBaseUrlImages + mPerson.getProfilePath())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new CircleTransform())
                    .into(imageViewProfile);

            textViewName.setText(mPerson.getName());
            textViewPopularity.setText(String.valueOf(mPerson.getPopularity()));
            if (mPerson.getBirthday() != null && !mPerson.getBirthday().isEmpty()) {
                textViewBirthday.setText(DateUtil.formatShort(this, mPerson.getBirthday()));
            } else {
                layoutBirthday.setVisibility(View.GONE);
            }
            if (mPerson.getPlaceOfBirth() != null && !mPerson.getPlaceOfBirth().isEmpty()) {
                textViewPlaceOfBirth.setText(mPerson.getPlaceOfBirth());
            } else {
                textViewTextPlaceOfBirth.setVisibility(View.GONE);
                textViewPlaceOfBirth.setVisibility(View.GONE);
            }
            if (mPerson.getBiography() != null && !mPerson.getBiography().isEmpty()) {
                textViewBiography.setText(mPerson.getBiography());
            } else {
                textViewTextBiography.setVisibility(View.GONE);
                textViewBiography.setVisibility(View.GONE);
            }

            mRecyclerView.setAdapter(new AlsoKnownAsAdapter(this, new ArrayList<>(mPerson.getAlsoKnownAs())));
        }
        mProgressDialog.hide();
    }

    private void showSnackbar(@StringRes int resId) {
        Snackbar.make(mFab, resId, Snackbar.LENGTH_LONG).show();
    }
}