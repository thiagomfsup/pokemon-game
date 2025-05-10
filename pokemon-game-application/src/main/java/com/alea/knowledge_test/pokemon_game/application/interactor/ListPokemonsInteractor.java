package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;

import java.util.List;

public class ListPokemonsInteractor implements ListPokemonsUseCase {

    private final PokemonRepository pokemonRepository;

    public ListPokemonsInteractor(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<PokemonRespose> listPokemons() {
        return pokemonRepository.retrieveAllPokemons()
                .stream().map(pokemon -> new PokemonRespose(pokemon.id(), pokemon.name()))
                .toList();
    }
}
