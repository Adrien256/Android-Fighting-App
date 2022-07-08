package com.example.t1_adrien_alow5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.t1_adrien_alow5.databinding.ActivityMainBinding;
import com.example.t1_adrien_alow5.db.PokemonManagerSingleton;
import com.example.t1_adrien_alow5.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    int idSelected;
    int favoritePokemon = 0;

    // init sharedpreference

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // populate listView

        PokemonManagerSingleton pokemonManager = PokemonManagerSingleton.getInstance();
        List<Pokemon> pokeList = pokemonManager.getPokemonList();
        PokemonObjectAdapter pokeAdapter = new PokemonObjectAdapter(this, pokeList);
        binding.lvPokemon.setAdapter(pokeAdapter);


        binding.lvPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                idSelected = pokeList.get(position).getPokedexNumber();

                Intent intent = new Intent(MainActivity.this, ActivityScreen2.class);
                intent.putExtra("EXTRA_SELECTED_POKEID", idSelected);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // update the wins and losses
        PokemonManagerSingleton pokeManage = PokemonManagerSingleton.getInstance();
        List<Pokemon> pokeList = pokeManage.getPokemonList();
        PokemonObjectAdapter pokeAdapter = new PokemonObjectAdapter(this, pokeList);
        binding.lvPokemon.setAdapter(pokeAdapter);
    }
}