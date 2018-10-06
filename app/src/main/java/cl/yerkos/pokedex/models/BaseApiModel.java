package cl.yerkos.pokedex.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseApiModel {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("previous")
    @Expose
    private String previous;
    @SerializedName("results")
    @Expose
    private List<PokemonModel> pokemonModels = null;
    @SerializedName("next")
    @Expose
    private String next;

    public BaseApiModel() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonModel> getPokemons() {
        return pokemonModels;
    }

    public void setPokemons(List<PokemonModel> pokemonModels) {
        this.pokemonModels = pokemonModels;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}

