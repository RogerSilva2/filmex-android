package br.com.infinitytechnology.filmex.interfaces;

import br.com.infinitytechnology.filmex.entities.Movie;
import br.com.infinitytechnology.filmex.entities.ResponseWithMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("/3/movie/{movie_id}")
    Call<Movie> movie(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey,
                      @Query("language") String language,
                      @Query("append_to_response") String appendToResponse);

    @GET("/3/movie/popular")
    Call<ResponseWithMovies> moviePopular(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") Integer page,
                                          @Query("region") String region);

    @GET("/3/movie/top_rated")
    Call<ResponseWithMovies> movieTopRated(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") Integer page,
                                           @Query("region") String region);

    @GET("/3/movie/upcoming")
    Call<ResponseWithMovies> movieUpcoming(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") Integer page,
                                           @Query("region") String region);

    @GET("/3/movie/now_playing")
    Call<ResponseWithMovies> movieNowPlaying(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") Integer page,
                                             @Query("region") String region);

}