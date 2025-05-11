package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.HeaviestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.response.HeaviestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.domain.PokemonFixtures;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class HeaviestPokemonInteractorTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private HeaviestPokemonInteractor heaviestPokemonInteractor;

    @Test
    void shouldReturnListOfHeaviestPokemon() {
        // given
        final int limit = 5;
        final var expectedHeaviestPokemons = PokemonFixtures.heaviestPokemons(limit);
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(PokemonFixtures.POKEMONS);

        // when
        final var request = new HeaviestPokemonRequest(limit);
        final var heaviestPokemonResponse = heaviestPokemonInteractor.heaviestPokemon(request);

        // then
        assertThat(heaviestPokemonResponse)
                .isNotNull()
                .extracting(HeaviestPokemonResponse::pokemons)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .isNotEmpty()
                .hasSize(limit)
                .isEqualTo(expectedHeaviestPokemons);
    }

}