package cl.yerkos.pokedex.views.detail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cl.yerkos.pokedex.R;

public class ShowPokemonDialogFragment extends DialogFragment {

    private ImageView pokemon_photo;
    private TextView pokename_detail_tv;
    private static final String PHOTO_URL_BASE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private static final String POKE_NAME = "cl.yerkos.pokedex.views.detail.POKE_NAME";
    private static final String POKE_URL = "cl.yerkos.pokedex.views.detail.POKE_URL";
    private String namePokemon;
    private String urlPokemon;


    public static ShowPokemonDialogFragment newInstance(String name, String url) {
        ShowPokemonDialogFragment dialogFragment = new ShowPokemonDialogFragment();
        Bundle args = new Bundle();
        args.putString(POKE_NAME, name);
        args.putString(POKE_URL, url);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            namePokemon = getArguments().getString(POKE_NAME);
            urlPokemon = getArguments().getString(POKE_URL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_pokemon_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokemon_photo = view.findViewById(R.id.pokemon_photo);
        pokename_detail_tv = view.findViewById(R.id.pokename_detail_tv);

        String url = urlPokemon;
        String[] splitUrl = url.split("https://pokeapi.co/api/v2/pokemon/");
        String[] pokemonNumber = splitUrl[1].split("/");
        String pokemonId = pokemonNumber[0];

        Picasso.get().load(PHOTO_URL_BASE + pokemonId +".png").into(pokemon_photo);
        pokename_detail_tv.setText(namePokemon);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }
}
