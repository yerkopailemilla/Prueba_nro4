package cl.yerkos.pokedex.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cl.yerkos.pokedex.R;
import cl.yerkos.pokedex.contract.PokemonListener;
import cl.yerkos.pokedex.models.BaseApiModel;
import cl.yerkos.pokedex.models.PokemonModel;

public class ApiAdapter extends RecyclerView.Adapter<ApiAdapter.PokemonViewHolder> {

    private List<PokemonModel> pokemonModels;
    private PokemonListener pokemonListener;

    public ApiAdapter(List<PokemonModel> pokemonModels, PokemonListener pokemonListener) {
        this.pokemonModels = pokemonModels;
        this.pokemonListener = pokemonListener;
    }

    public void setPokemonModels(List<PokemonModel> pokemonModels) {
        this.pokemonModels = pokemonModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder holder, int position) {
        PokemonModel model = pokemonModels.get(position);
        holder.pokemonn_name_tv.setText(model.getName());

        holder.lyt_pkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int auxPosition = holder.getAdapterPosition();
                PokemonModel auxPokemon = pokemonModels.get(auxPosition);
                pokemonListener.pokemonClickedListener(auxPokemon.getName(), auxPokemon.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonModels.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout lyt_pkm;
        private TextView pokemonn_name_tv;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            lyt_pkm = itemView.findViewById(R.id.lyt_pkm);
            pokemonn_name_tv = itemView.findViewById(R.id.pokemonn_name_tv);
        }
    }
}
