package com.example.t1_adrien_alow5.db;

import com.example.t1_adrien_alow5.models.Pokemon;

import java.util.ArrayList;

public class PokemonManagerSingleton {
    // constructor
    private PokemonManagerSingleton(){
        loadPokemon();
    }

    private static PokemonManagerSingleton instance = null;

    public static PokemonManagerSingleton getInstance(){
        if (instance == null){
            instance = new PokemonManagerSingleton();
        }
        return instance;
    }
    // properties
    private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();

    // methods

    private void loadPokemon(){
        this.pokemonList.add(new Pokemon(12, "Caterpie"));
        this.pokemonList.add(new Pokemon(19, "Rattata"));
        this.pokemonList.add(new Pokemon(25, "Pikachu"));
        this.pokemonList.add(new Pokemon(147, "Dratini"));
    }

    // getter

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public Pokemon getPokemonById(int pokeId){
        Pokemon currPokemon = null;
        for (int i = 0; i < this.pokemonList.size(); i++){
            currPokemon = this.pokemonList.get(i);
            if (currPokemon.getPokedexNumber() == pokeId){
                return currPokemon;
            }
        }
        return currPokemon;
    }
}


