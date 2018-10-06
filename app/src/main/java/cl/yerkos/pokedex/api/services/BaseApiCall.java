package cl.yerkos.pokedex.api.services;

import cl.yerkos.pokedex.models.BaseApiModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseApiCall {

    @GET("pokemon")
    Call<BaseApiModel> getAllPokemons();

}
