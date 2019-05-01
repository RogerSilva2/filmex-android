package br.com.infinitytechnology.filmex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.entities.TvShow;
import br.com.infinitytechnology.filmex.fragments.FragmentPagination;
import br.com.infinitytechnology.filmex.fragments.MoviesFragment;
import br.com.infinitytechnology.filmex.fragments.PeopleFragment;
import br.com.infinitytechnology.filmex.fragments.TvShowsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MoviesFragment.OnMoviesFragmentInteractionListener,
        TvShowsFragment.OnTvShowsFragmentInteractionListener,
        PeopleFragment.OnPeopleFragmentInteractionListener {

    public static final String ARGS = "ARGS";
    public static final String ARG_MOVIE_ID = "MOVIE_ID";
    public static final String ARG_TV_SHOW_ID = "TV_SHOW_ID";
    public static final String ARG_PERSON_ID = "PERSON_ID";

    public static final String ARG_TAG = "TAG";
    public static final String ARG_PAGE = "PAGE";

    public static final String TAG_FRAGMENT_POPULAR_MOVIES = "FRAGMENT_POPULAR_MOVIES";
    public static final String TAG_FRAGMENT_TOP_RATED_MOVIES = "FRAGMENT_TOP_RATED_MOVIES";
    public static final String TAG_FRAGMENT_UPCOMING = "FRAGMENT_UPCOMING";
    public static final String TAG_FRAGMENT_NOW_PLAYING = "FRAGMENT_NOW_PLAYING";

    public static final String TAG_FRAGMENT_POPULAR_TV_SHOWS = "FRAGMENT_POPULAR_TV_SHOWS";
    public static final String TAG_FRAGMENT_TOP_RATED_TV_SHOWS = "FRAGMENT_TOP_RATED_TV_SHOWS";
    public static final String TAG_FRAGMENT_ON_TV = "FRAGMENT_ON_TV";
    public static final String TAG_FRAGMENT_AIRING_TODAY = "FRAGMENT_AIRING_TODAY";

    public static final String TAG_FRAGMENT_POPULAR_PEOPLE = "FRAGMENT_POPULAR_PEOPLE";

    private NavigationView mNavigationView;
    private TextView mTextViewPage;
    private TextView mTextViewTotalPages;
    private Button mButtonPrevious;
    private Button mButtonNext;

    private String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayoutCompat layoutPage = findViewById(R.id.layout_page);
        mTextViewPage = layoutPage.findViewById(R.id.text_view_name);

        LinearLayoutCompat layoutTotalPages = findViewById(R.id.layout_total_pages);
        mTextViewTotalPages = layoutTotalPages.findViewById(R.id.text_view_name);

        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPagination fragment =
                        (FragmentPagination) getSupportFragmentManager().findFragmentByTag(mTag);
                if (fragment != null) {
                    fragment.paginationPrevious();
                }
            }
        });

        mButtonNext = findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPagination fragment =
                        (FragmentPagination) getSupportFragmentManager().findFragmentByTag(mTag);
                if (fragment != null) {
                    fragment.paginationNext();
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        commitMoviesFragment(TAG_FRAGMENT_POPULAR_MOVIES);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_acknowledgments) {
            Intent intent = new Intent(this, AcknowledgmentsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        unCheckAllMenuItems(mNavigationView.getMenu());
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.nav_popular_movies: {
                commitMoviesFragment(TAG_FRAGMENT_POPULAR_MOVIES);
            } break;
            case R.id.nav_top_rated_movies: {
                commitMoviesFragment(TAG_FRAGMENT_TOP_RATED_MOVIES);
            } break;
            case R.id.nav_upcoming: {
                commitMoviesFragment(TAG_FRAGMENT_UPCOMING);
            } break;
            case R.id.nav_now_playing: {
                commitMoviesFragment(TAG_FRAGMENT_NOW_PLAYING);
            } break;
            case R.id.nav_popular_tv_shows: {
                commitTvShowsFragment(TAG_FRAGMENT_POPULAR_TV_SHOWS);
            } break;
            case R.id.nav_top_rated_tv_shows: {
                commitTvShowsFragment(TAG_FRAGMENT_TOP_RATED_TV_SHOWS);
            } break;
            case R.id.nav_on_tv: {
                commitTvShowsFragment(TAG_FRAGMENT_ON_TV);
            } break;
            case R.id.nav_airing_today: {
                commitTvShowsFragment(TAG_FRAGMENT_AIRING_TODAY);
            } break;
            case R.id.nav_popular_people: {
                commitPeopleFragment();
            } break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void unCheckAllMenuItems(@NonNull final Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                unCheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    private void commitMoviesFragment(String tag) {
        mTag = tag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, MoviesFragment.newInstance(mTag), mTag)
                .commit();
    }

    private void commitTvShowsFragment(String tag) {
        mTag = tag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, TvShowsFragment.newInstance(mTag), mTag)
                .commit();
    }

    private void commitPeopleFragment() {
        mTag = TAG_FRAGMENT_POPULAR_PEOPLE;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PeopleFragment.newInstance(mTag), mTag)
                .commit();
    }

    @Override
    public void onMoviesFragmentInteraction(Movie movie) {
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movie.getId());

        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(ARGS, args);

        startActivity(intent);
    }

    @Override
    public void onTvShowsFragmentInteraction(TvShow tvShow) {
        Bundle args = new Bundle();
        args.putInt(ARG_TV_SHOW_ID, tvShow.getId());

        Intent intent = new Intent(this, DetailTvShowActivity.class);
        intent.putExtra(ARGS, args);

        startActivity(intent);
    }

    @Override
    public void onPeopleFragmentInteraction(Person person) {
        Bundle args = new Bundle();
        args.putInt(ARG_PERSON_ID, person.getId());

        Intent intent = new Intent(this, DetailPersonActivity.class);
        intent.putExtra(ARGS, args);

        startActivity(intent);
    }

    @Override
    public void onPaginationFragmentInteraction(Pair<Integer, Integer> pair) {
        mTextViewPage.setText(String.valueOf(pair.first));
        mTextViewTotalPages.setText(String.valueOf(pair.second));

        mButtonPrevious.setVisibility(View.VISIBLE);
        mButtonNext.setVisibility(View.VISIBLE);

        if (pair.first.equals(1)) {
            mButtonPrevious.setVisibility(View.INVISIBLE);
        } else if (pair.first.equals(pair.second)) {
            mButtonNext.setVisibility(View.INVISIBLE);
        }
    }
}