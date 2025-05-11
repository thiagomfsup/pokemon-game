package com.alea.knowledge_test.pokemon_game.application.usecase;

import com.alea.knowledge_test.pokemon_game.application.request.HeaviestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.response.HeaviestPokemonResponse;

public interface HeaviestPokemonUseCase {

    HeaviestPokemonResponse heaviestPokemon(HeaviestPokemonRequest heaviestPokemonRequest);

}
