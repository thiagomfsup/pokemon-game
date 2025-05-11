package com.alea.knowledge_test.pokemon_game.config;

import com.alea.knowledge_test.pokemon_game.application.interactor.HeaviestPokemonInteractor;
import com.alea.knowledge_test.pokemon_game.application.interactor.HighestPokemonInteractor;
import com.alea.knowledge_test.pokemon_game.application.interactor.ListPokemonsInteractor;
import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.usecase.HeaviestPokemonUseCase;
import com.alea.knowledge_test.pokemon_game.application.usecase.HighestPokemonUseCase;
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
    public HighestPokemonUseCase highestPokemonUseCase(PokemonRepository pokemonRepository) {
        return new HighestPokemonInteractor(pokemonRepository);
    }

    @Bean
    public HeaviestPokemonUseCase heaviestPokemonUseCase(PokemonRepository pokemonRepository) {
        return new HeaviestPokemonInteractor(pokemonRepository);
    }
}
