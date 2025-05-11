package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.GetPokemonByRankingRequest;
import com.alea.knowledge_test.pokemon_game.application.response.GetPokemonByRankingResponse;
import com.alea.knowledge_test.pokemon_game.application.usecase.GetPokemonByRankingUseCase;

public class GetPokemonByRankingInteractor implements GetPokemonByRankingUseCase {

    private final PokemonRepository pokemonRepository;

    public GetPokemonByRankingInteractor(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public GetPokemonByRankingResponse getPokemonByRanking(GetPokemonByRankingRequest getPokemonByRankingRequest) {
        final var pokemonsByRanking = pokemonRepository.retrieveAllPokemons().stream()
                .sorted(getPokemonByRankingRequest.rankingCriteria().sortCriteria())
                .limit(getPokemonByRankingRequest.limit())
                .toList();

        return new GetPokemonByRankingResponse(pokemonsByRanking);
    }
}
