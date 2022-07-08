package com.example.t1_adrien_alow5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.t1_adrien_alow5.databinding.CustomRowLayoutBinding;
import com.example.t1_adrien_alow5.db.PokemonManagerSingleton;
import com.example.t1_adrien_alow5.models.Pokemon;

import java.util.List;

public class PokemonObjectAdapter extends ArrayAdapter {
    private List<Pokemon> pokeList;

    public PokemonObjectAdapter(@NonNull Context context, List<Pokemon> pokeList){
        super(context, 0);
        this.pokeList = pokeList;
    }


    @Override
    public int getCount(){return pokeList.size();}

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Pokemon currPokemon = pokeList.get(position);

        // get favorite from sharedPreference
        SharedPreferences sp = getContext().getSharedPreferences("KEY_POKEDEX_NUMBER", 0);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_row_layout, parent, false);
        }



        CustomRowLayoutBinding binding = CustomRowLayoutBinding.bind(convertView);

        if (sp.contains("POKEDEX_25")){
            if (sp.getBoolean("POKEDEX_25", false)){
                if (currPokemon.getPokedexNumber() == 25){
                    binding.Linearlay.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
            }
        }
        if (sp.contains("POKEDEX_12")){
            if (sp.getBoolean("POKEDEX_12", false)){
                if (currPokemon.getPokedexNumber() == 12){
                    binding.Linearlay.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
            }
        }
        if (sp.contains("POKEDEX_19")){
            if (sp.getBoolean("POKEDEX_19", false)){
                if (currPokemon.getPokedexNumber() == 19){
                    binding.Linearlay.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
            }
        }
        if (sp.contains("POKEDEX_147")){
            if (sp.getBoolean("POKEDEX_147", false)){
                if (currPokemon.getPokedexNumber() == 147){
                    binding.Linearlay.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
            }
        }



        binding.tvPokemonName.setText(currPokemon.getName());
        binding.tvWins.setText(String.valueOf(currPokemon.getWinAmount()));
        binding.tvLosses.setText(String.valueOf(currPokemon.getLossAmount()));

        return convertView;
    }
}
