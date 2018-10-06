package cl.yerkos.pokedex.views.main;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.yerkos.pokedex.R;
import cl.yerkos.pokedex.adapters.ApiAdapter;
import cl.yerkos.pokedex.api.API;
import cl.yerkos.pokedex.api.services.BaseApiCall;
import cl.yerkos.pokedex.contract.PokemonListener;
import cl.yerkos.pokedex.models.BaseApiModel;
import cl.yerkos.pokedex.models.PokemonModel;
import cl.yerkos.pokedex.views.detail.ShowPokemonDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonsActivity extends AppCompatActivity implements PokemonListener {

    private RecyclerView pokemonsRecycler;
    private List<PokemonModel> listPokemons = new ArrayList<>();
    private ApiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons);

        pokemonsRecycler = findViewById(R.id.pokemonsRecycler);
        pokemonsRecycler.setLayoutManager(new GridLayoutManager(PokemonsActivity.this, 3));
        adapter = new ApiAdapter(listPokemons, this);
        pokemonsRecycler.setAdapter(adapter);

        BaseApiCall service = API.getApiConnect().create(BaseApiCall.class);
        Call<BaseApiModel> call = service.getAllPokemons();

        call.enqueue(new Callback<BaseApiModel>() {
            @Override
            public void onResponse(Call<BaseApiModel> call, Response<BaseApiModel> response) {
                if (response.isSuccessful()){
                    BaseApiModel model = response.body();

                    if (model != null && model.getPokemons() != null){
                        List<PokemonModel> listPokemons = model.getPokemons();
                        adapter.setPokemonModels(listPokemons);
                    } else {
                        Toast.makeText(PokemonsActivity.this, "No se encontraron pokemons", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseApiModel> call, Throwable t) {
                Toast.makeText(PokemonsActivity.this, "Oh! Algo salió mal. Vuelve a intentarlo.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void pokemonClickedListener(String name, String url) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("showPokemon");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment dialogFragment = ShowPokemonDialogFragment.newInstance(name, url);
        dialogFragment.show(ft, "showPokemon");
    }
}
