package br.com.infinitytechnology.filmex.interfaces;

import br.com.infinitytechnology.filmex.entities.ResponseWithTvShows;
import br.com.infinitytechnology.filmex.entities.TvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvShowsService {

    @GET("/3/tv/{tv_id}")
    Call<TvShow> tvShow(@Path("tv_id") Integer tvId, @Query("api_key") String apiKey,
                        @Query("language") String language,
                        @Query("append_to_response") String appendToResponse);

    @GET("/3/tv/popular")
    Call<ResponseWithTvShows> tvShowPopular(@Query("api_key") String apiKey,
                                            @Query("language") String language,
                                            @Query("page") Integer page);

    @GET("/3/tv/top_rated")
    Call<ResponseWithTvShows> tvShowTopRated(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") Integer page);

    @GET("/3/tv/on_the_air")
    Call<ResponseWithTvShows> tvShowOnTheAir(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") Integer page);

    @GET("/3/tv/airing_today")
    Call<ResponseWithTvShows> tvShowAiringToday(@Query("api_key") String apiKey,
                                                @Query("language") String language,
                                                @Query("page") Integer page);

}