package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.HeaviestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.response.HeaviestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.usecase.HeaviestPokemonUseCase;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.Comparator;

public class HeaviestPokemonInteractor implements HeaviestPokemonUseCase {
    private final PokemonRepository pokemonRepository;

    public HeaviestPokemonInteractor(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public HeaviestPokemonResponse heaviestPokemon(HeaviestPokemonRequest heaviestPokemonRequest) {
        final var highestPokemons = pokemonRepository.retrieveAllPokemons().stream()
                .sorted(Comparator.comparingInt(Pokemon::weight).reversed())
                .limit(5)
                .toList();

        return new HeaviestPokemonResponse(highestPokemons);
    }
}
