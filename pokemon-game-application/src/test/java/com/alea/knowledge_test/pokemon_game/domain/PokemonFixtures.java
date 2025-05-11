package com.alea.knowledge_test.pokemon_game.domain;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.Comparator;
import java.util.List;

public class PokemonFixtures {

    public static final List<Pokemon> POKEMONS = List.of(
            new Pokemon(1, "bulbasaur", 69, 7, 64),
            new Pokemon(2, "ivysaur", 1000, 130, 142),
            new Pokemon(3, "venusaur", 1000, 20, 263),
            new Pokemon(4, "charmander", 85, 6, 62),
            new Pokemon(5, "charmeleon", 190, 11, 142),
            new Pokemon(6, "charizard", 905, 17, 267),
            new Pokemon(7, "squirtle", 90, 5, 63),
            new Pokemon(8, "wartortle", 225, 10, 142));

    public static List<Pokemon> highestPokemons(int limit) {
        return POKEMONS.stream()
                .sorted(Comparator.comparingInt(Pokemon::height).reversed())
                .limit(limit)
                .toList();
    }

    public static List<Pokemon> heaviestPokemons(int limit) {
        return POKEMONS.stream()
                .sorted(Comparator.comparingInt(Pokemon::weight).reversed())
                .limit(limit)
                .toList();
    }

    public static List<Pokemon> pokemonsRankedByBaseExperience(int limit) {
        return POKEMONS.stream()
                .sorted(Comparator.comparingInt(Pokemon::baseExperience).reversed())
                .limit(limit)
                .toList();
    }
}