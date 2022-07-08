package com.example.t1_adrien_alow5.models;

public class Pokemon {
    private int pokedexNumber;
    private String name;
    private int winAmount;
    private int lossAmount;

    public Pokemon(int pokedexNumber, String name) {
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.winAmount = 0;
        this.lossAmount = 0;
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    public int getLossAmount() {
        return lossAmount;
    }

    public void setLossAmount(int lossAmount) {
        this.lossAmount = lossAmount;
    }
}
