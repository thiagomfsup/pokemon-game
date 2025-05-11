package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.HighestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HighestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.usecase.HighestPokemonUseCase;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.Comparator;

public class HighestPokemonInteractor implements HighestPokemonUseCase {

    private final PokemonRepository pokemonRepository;

    public HighestPokemonInteractor(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public HighestPokemonResponse highestPokemon(HighestPokemonRequest request) {
        final var highestPokemons = pokemonRepository.retrieveAllPokemons().stream()
                .sorted(Comparator.comparingInt(Pokemon::height).reversed())
                .limit(5)
                .toList();

        return new HighestPokemonResponse(highestPokemons);
    }
}
