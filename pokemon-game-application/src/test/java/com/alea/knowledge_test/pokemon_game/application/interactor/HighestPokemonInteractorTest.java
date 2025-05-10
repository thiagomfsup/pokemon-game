package com.alea.knowledge_test.pokemon_game.application.interactor;


import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.HighestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HighestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.domain.PokemonFixtures;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class HighestPokemonInteractorTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private HighestPokemonInteractor highestPokemonInteractor;

    @Test
    void shouldReturnListOfHighestPokemon() {
        // given
        final var limit = 5;
        final var pokemons = PokemonFixtures.pokemons();
        final var expectedHighestPokemons = pokemons.stream()
                .sorted(Comparator.comparingInt(Pokemon::height))
                .limit(limit)
                .toList();
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(pokemons);

        // when
        final var request = new HighestPokemonRequest(limit);
        final var highestPokemonResponse = highestPokemonInteractor.highestPokemon(request);

        // then
        assertThat(highestPokemonResponse)
                .isNotNull()
                .extracting(HighestPokemonResponse::pokemons)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .isNotEmpty()
                .hasSize(limit)
                .isEqualTo(expectedHighestPokemons);
    }
}