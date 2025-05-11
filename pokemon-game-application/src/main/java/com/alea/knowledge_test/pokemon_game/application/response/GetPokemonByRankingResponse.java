package com.alea.knowledge_test.pokemon_game.application.response;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.List;

public record GetPokemonByRankingResponse(List<Pokemon> pokemons) {
}
