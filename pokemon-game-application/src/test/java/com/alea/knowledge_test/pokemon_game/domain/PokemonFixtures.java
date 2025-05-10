package com.alea.knowledge_test.pokemon_game.domain;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.List;

public class PokemonFixtures {

    public static List<Pokemon> pokemons() {
        return List.of(
                new Pokemon(1, "bulbasaur", 69, 7, 64),
                new Pokemon(2, "ivysaur", 1000, 130, 142),
                new Pokemon(3, "venusaur", 1000, 20, 263),
                new Pokemon(4, "charmander", 85, 6, 62),
                new Pokemon(5, "charmeleon", 190, 11, 142),
                new Pokemon(6, "charizard", 905, 17, 267),
                new Pokemon(7, "squirtle", 90, 5, 63),
                new Pokemon(8, "wartortle", 225, 10, 142));
    }

}