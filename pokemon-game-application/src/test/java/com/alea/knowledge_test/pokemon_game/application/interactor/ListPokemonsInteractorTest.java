package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListPokemonsInteractorTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private ListPokemonsInteractor listPokemonsInteractor;

    @Test
    public void shouldReturnPokemons_when_listingPokemons() {
        final var expectedListOfPokemons = Instancio.ofList(Pokemon.class).size(10).create();
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(expectedListOfPokemons);

        final var pokemonResposes = listPokemonsInteractor.listPokemons();

        Assertions.assertThat(pokemonResposes)
                .isNotEmpty();
    }

}