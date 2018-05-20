package br.com.infinitytechnology.filmex.activities;

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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.infinitytechnology.filmex.R;
import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.entities.TvShow;
import br.com.infinitytechnology.filmex.fragments.MoviesFragment;
import br.com.infinitytechnology.filmex.fragments.PeopleFragment;
import br.com.infinitytechnology.filmex.fragments.TvShowsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MoviesFragment.OnMoviesFragmentInteractionListener,
        TvShowsFragment.OnTvShowsFragmentInteractionListener,
        PeopleFragment.OnPeopleFragmentInteractionListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        commitMoviesFragment(TAG_FRAGMENT_POPULAR_MOVIES);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
//        int id = item.getItemId();
//        return id == R.id.action_settings || super.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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
                commitPeopleFragment(TAG_FRAGMENT_POPULAR_PEOPLE);
            } break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, MoviesFragment.newInstance(tag), tag)
                .commit();
    }

    private void commitTvShowsFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, TvShowsFragment.newInstance(tag), tag)
                .commit();
    }

    private void commitPeopleFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PeopleFragment.newInstance(tag), tag)
                .commit();
    }

    @Override
    public void onMoviesFragmentInteraction(Movie movie) {
    }

    @Override
    public void onTvShowsFragmentInteraction(TvShow tvShow) {
    }

    @Override
    public void onPeopleFragmentInteraction(Person person) {
    }
}