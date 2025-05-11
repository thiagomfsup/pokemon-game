package com.alea.knowledge_test.pokemon_game.config;

import com.alea.knowledge_test.pokemon_game.application.interactor.GetPokemonByRankingInteractor;
import com.alea.knowledge_test.pokemon_game.application.interactor.ListPokemonsInteractor;
import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.usecase.GetPokemonByRankingUseCase;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public ListPokemonsUseCase listPokemonsUseCase(PokemonRepository pokemonRepository) {
        return new ListPokemonsInteractor(pokemonRepository);
    }

    @Bean
    public GetPokemonByRankingUseCase getPokemonByRanking(PokemonRepository pokemonRepository) {
        return new GetPokemonByRankingInteractor(pokemonRepository);
    }
}
