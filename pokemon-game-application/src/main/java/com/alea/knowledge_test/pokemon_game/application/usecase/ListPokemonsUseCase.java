package com.alea.knowledge_test.pokemon_game.application.usecase;

import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;

import java.util.List;

public interface ListPokemonsUseCase {
    List<PokemonRespose> listPokemons();
}
