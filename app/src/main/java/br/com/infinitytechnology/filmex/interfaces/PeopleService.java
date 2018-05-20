package br.com.infinitytechnology.filmex.interfaces;

import br.com.infinitytechnology.filmex.entities.Person;
import br.com.infinitytechnology.filmex.entities.ResponseWithPeople;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeopleService {

    @GET("/3/person/{person_id}")
    Call<Person> person(@Path("person_id") Integer personId, @Query("api_key") String apiKey,
                        @Query("language") String language,
                        @Query("append_to_response") String appendToResponse);

    @GET("/3/person/popular")
    Call<ResponseWithPeople> personPopular(@Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") Integer page);

}