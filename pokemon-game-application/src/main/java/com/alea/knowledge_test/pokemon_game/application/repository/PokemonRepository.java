package com.alea.knowledge_test.pokemon_game.application.repository;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PokemonRepository {
    List<Pokemon> retrieveAllPokemons();
}
