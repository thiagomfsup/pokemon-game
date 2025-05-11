package com.alea.knowledge_test.pokemon_game.application.usecase;

import com.alea.knowledge_test.pokemon_game.application.request.GetPokemonByRankingRequest;
import com.alea.knowledge_test.pokemon_game.application.response.GetPokemonByRankingResponse;

public interface GetPokemonByRankingUseCase {

    GetPokemonByRankingResponse getPokemonByRanking(GetPokemonByRankingRequest getPokemonByRankingRequest);

}
