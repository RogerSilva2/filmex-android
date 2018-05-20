package br.com.infinitytechnology.filmex.interfaces;

import br.com.infinitytechnology.filmex.entities.ResponseWithMovies;
import br.com.infinitytechnology.filmex.entities.ResponseWithTvShows;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscoverService {

    @GET("/3/discover/movie")
    Call<ResponseWithMovies> discoverMovie(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("region") String region,
                                           @Query("sort_by") String sortBy,
                                           @Query("certification_country") String certificationCountry,
                                           @Query("certification") String certification,
                                           @Query("certification.lte") String certificationLte,
                                           @Query("include_adult") Boolean includeAdult,
                                           @Query("include_video") Boolean includeVideo,
                                           @Query("page") Integer page,
                                           @Query("primary_release_year") Integer primaryReleaseYear,
                                           @Query("primary_release_date.gte") String primaryReleaseDateGte,
                                           @Query("primary_release_date.lte") String primaryReleaseDateLte,
                                           @Query("release_date.gte") String releaseDateGte,
                                           @Query("release_date.lte") String releaseDateLte,
                                           @Query("vote_count.gte") Integer voteCountGte,
                                           @Query("vote_count.lte") Integer voteCountLte,
                                           @Query("vote_average.gte") Double voteAverageGte,
                                           @Query("vote_average.lte") Double voteAverageLte,
                                           @Query("with_cast") String withCast,
                                           @Query("with_crew") String withCrew,
                                           @Query("with_companies") String withCompanies,
                                           @Query("with_genres") String withGenres,
                                           @Query("with_keywords") String withKeywords,
                                           @Query("with_people") String withPeople,
                                           @Query("year") Integer year,
                                           @Query("without_genres") String withoutGenres,
                                           @Query("with_runtime.gte") Integer withRuntimeGte,
                                           @Query("with_runtime.lte") Integer withRuntimeLte,
                                           @Query("with_release_type") Integer withReleaseType,
                                           @Query("with_original_language") String withOriginalLanguage,
                                           @Query("without_keywords") String withoutKeywords);

    @GET("/3/discover/tv")
    Call<ResponseWithTvShows> discoverTvShow(@Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("sort_by") String sortBy,
                                             @Query("air_date.gte") String airDateGte,
                                             @Query("air_date.lte") String airDateLte,
                                             @Query("first_air_date.gte") String firstAirDateGte,
                                             @Query("first_air_date.lte") String firstAirDateLte,
                                             @Query("first_air_date_year") Integer firstAirDateYear,
                                             @Query("page") Integer page,
                                             @Query("timezone") String timezone,
                                             @Query("vote_average.gte") Double voteAverageGte,
                                             @Query("vote_count.gte") Integer voteCountGte,
                                             @Query("with_genres") String withGenres,
                                             @Query("with_networks") String withNetworks,
                                             @Query("without_genres") String withoutGenres,
                                             @Query("with_runtime.gte") Integer withRuntimeGte,
                                             @Query("with_runtime.lte") Integer withRuntimeLte,
                                             @Query("include_null_first_air_dates") Boolean includeNullFirstAirDates,
                                             @Query("with_original_language") String withOriginalLanguage,
                                             @Query("without_keywords") String withoutKeywords,
                                             @Query("screened_theatrically") Boolean screenedTheatrically,
                                             @Query("with_companies") String withCompanies);

}