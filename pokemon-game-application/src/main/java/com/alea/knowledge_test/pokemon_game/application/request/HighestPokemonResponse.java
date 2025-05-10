package com.alea.knowledge_test.pokemon_game.application.request;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.List;

public record HighestPokemonResponse(List<Pokemon> pokemons) {
}
