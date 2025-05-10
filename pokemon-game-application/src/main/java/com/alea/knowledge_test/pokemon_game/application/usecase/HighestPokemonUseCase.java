package com.alea.knowledge_test.pokemon_game.application.usecase;

import com.alea.knowledge_test.pokemon_game.application.request.HighestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HighestPokemonRequest;

public interface HighestPokemonUseCase {

    HighestPokemonResponse highestPokemon(HighestPokemonRequest request);

}
