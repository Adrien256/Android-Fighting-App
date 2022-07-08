package com.example.t1_adrien_alow5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.t1_adrien_alow5.databinding.Activityscreen2Binding;
import com.example.t1_adrien_alow5.db.PokemonManagerSingleton;
import com.example.t1_adrien_alow5.models.Pokemon;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class ActivityScreen2 extends AppCompatActivity {
    private Activityscreen2Binding binding;
    Pokemon currPokemon;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = Activityscreen2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.sp = getSharedPreferences("KEY_POKEDEX_NUMBER", Context.MODE_PRIVATE);

        Intent intent = getIntent();

        // look up selected pokemon and then display them

        if (intent != null){
            int selectedId = intent.getIntExtra("EXTRA_SELECTED_POKEID", -1);

            // query singleton
            PokemonManagerSingleton pokeManager = PokemonManagerSingleton.getInstance();
            currPokemon = pokeManager.getPokemonById(selectedId);

            int imageid = getResources().getIdentifier(currPokemon.getName().toLowerCase(), "drawable",
                    getPackageName());
            binding.imageView.setImageResource(imageid);

            binding.tvPokeName.setText(currPokemon.getName());
            binding.tvPokeId.setText(String.valueOf(currPokemon.getPokedexNumber()));

            binding.buttonFight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Random RNG = new Random();
                    int player = RNG.nextInt(6 - 1) + 1;
                    int computer = RNG.nextInt(6 - 1) + 1;

                    if (player > computer){
                        Snackbar myBar = Snackbar.make(binding.getRoot(), "Player: " + player + ", Computer: " + computer + ", Winner: Player", Snackbar.LENGTH_SHORT);
                        myBar.show();
                        currPokemon.setWinAmount(currPokemon.getWinAmount() + 1);
                    }else{
                        Snackbar myBar = Snackbar.make(binding.getRoot(), "Player: " + player + ", Computer: " + computer + ", Winner: Computer", Snackbar.LENGTH_SHORT);
                        myBar.show();
                        currPokemon.setLossAmount(currPokemon.getLossAmount() + 1);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.screen2_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.miSetFavorite:
                // set favorite
                SharedPreferences.Editor spEditor = sp.edit();
                String keyValue = "POKEDEX_" + currPokemon.getPokedexNumber();
                spEditor.putBoolean(keyValue, true);
                spEditor.apply();
                Snackbar myBar = Snackbar.make(binding.getRoot(), "Set as favorite!", Snackbar.LENGTH_SHORT);
                myBar.show();
                return true;
            case R.id.miReset:
                currPokemon.setLossAmount(0);
                currPokemon.setWinAmount(0);
                SharedPreferences.Editor spEdit = sp.edit();
                String keyValue2 = "POKEDEX_" + currPokemon.getPokedexNumber();
                spEdit.remove(keyValue2);
                spEdit.commit();
                Snackbar myBar2 = Snackbar.make(binding.getRoot(), "Pokemon reset!", Snackbar.LENGTH_SHORT);
                myBar2.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
