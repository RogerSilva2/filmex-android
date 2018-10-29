package br.com.infinitytechnology.filmex.interfaces;

import br.com.infinitytechnology.filmex.entities.ResponseWithKeywords;
import br.com.infinitytechnology.filmex.entities.ResponseWithMovies;
import br.com.infinitytechnology.filmex.entities.ResponseWithOneOf;
import br.com.infinitytechnology.filmex.entities.ResponseWithPeople;
import br.com.infinitytechnology.filmex.entities.ResponseWithProductionCompanies;
import br.com.infinitytechnology.filmex.entities.ResponseWithTvShows;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("/3/search/company")
    Call<ResponseWithProductionCompanies> searchCompany(@Query("api_key") String apiKey,
                                                        @Query("query") String query,
                                                        @Query("page") Integer page);

    @GET("/3/search/keyword")
    Call<ResponseWithKeywords> searchKeyword(@Query("api_key") String apiKey,
                                             @Query("query") String query,
                                             @Query("page") Integer page);

    @GET("/3/search/movie")
    Call<ResponseWithMovies> searchMovie(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("query") String query,
                                         @Query("page") Integer page,
                                         @Query("include_adult") Boolean includeAdult,
                                         @Query("region") String region,
                                         @Query("year") Integer year,
                                         @Query("primary_release_year") Integer primaryReleaseYear);

    @GET("/3/search/multi")
    Call<ResponseWithOneOf> searchMulti(@Query("api_key") String apiKey,
                                        @Query("language") String language,
                                        @Query("query") String query,
                                        @Query("page") Integer page,
                                        @Query("include_adult") Boolean includeAdult,
                                        @Query("region") String region);

    @GET("/3/search/person")
    Call<ResponseWithPeople> searchPerson(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("query") String query,
                                          @Query("page") Integer page,
                                          @Query("include_adult") Boolean includeAdult,
                                          @Query("region") String region);

    @GET("/3/search/tv")
    Call<ResponseWithTvShows> searchTvShow(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("query") String query,
                                           @Query("page") Integer page,
                                           @Query("first_air_date_year") Integer firstAirDateYear);

}