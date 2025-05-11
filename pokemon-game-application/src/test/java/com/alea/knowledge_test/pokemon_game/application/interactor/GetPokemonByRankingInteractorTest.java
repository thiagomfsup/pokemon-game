package com.alea.knowledge_test.pokemon_game.application.interactor;

import com.alea.knowledge_test.pokemon_game.application.ranking.BaseExperienceRankingCriteria;
import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.application.request.GetPokemonByRankingRequest;
import com.alea.knowledge_test.pokemon_game.application.ranking.HeaviestRankingCriteria;
import com.alea.knowledge_test.pokemon_game.application.ranking.HighestRakingCriteria;
import com.alea.knowledge_test.pokemon_game.application.response.GetPokemonByRankingResponse;
import com.alea.knowledge_test.pokemon_game.domain.PokemonFixtures;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GetPokemonByRankingInteractorTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private GetPokemonByRankingInteractor getPokemonByRankingInteractor;

    @Test
    void shouldReturnListOfHeaviestPokemon() {
        // given
        final int limit = new Random().nextInt(PokemonFixtures.POKEMONS.size() + 1);
        final var expectedHeaviestPokemons = PokemonFixtures.heaviestPokemons(limit);
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(PokemonFixtures.POKEMONS);

        // when
        final var request = new GetPokemonByRankingRequest(new HeaviestRankingCriteria(), limit);
        final var getPokemonByRankingResponse = getPokemonByRankingInteractor.getPokemonByRanking(request);

        // then
        assertThat(getPokemonByRankingResponse)
                .isNotNull()
                .extracting(GetPokemonByRankingResponse::pokemons)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .isNotEmpty()
                .hasSize(limit)
                .isEqualTo(expectedHeaviestPokemons);
    }

    @Test
    void shouldReturnListOfHighestPokemon() {
        // given
        final int limit = new Random().nextInt(PokemonFixtures.POKEMONS.size() + 1);
        final var expectedHighestPokemons = PokemonFixtures.highestPokemons(limit);
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(PokemonFixtures.POKEMONS);

        // when
        final var request = new GetPokemonByRankingRequest(new HighestRakingCriteria(), limit);
        final var getPokemonByRankingResponse = getPokemonByRankingInteractor.getPokemonByRanking(request);

        // then
        assertThat(getPokemonByRankingResponse)
                .isNotNull()
                .extracting(GetPokemonByRankingResponse::pokemons)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .isNotEmpty()
                .hasSize(limit)
                .isEqualTo(expectedHighestPokemons);
    }

    @Test
    void shouldReturnListOfPokemonRankedByBaseExperience() {
        // given
        final int limit = new Random().nextInt(PokemonFixtures.POKEMONS.size() + 1);
        final var expectedPokemonsRankedByBaseExperience = PokemonFixtures.pokemonsRankedByBaseExperience(limit);
        Mockito.when(pokemonRepository.retrieveAllPokemons()).thenReturn(PokemonFixtures.POKEMONS);

        // when
        final var request = new GetPokemonByRankingRequest(new BaseExperienceRankingCriteria(), limit);
        final var getPokemonByRankingResponse = getPokemonByRankingInteractor.getPokemonByRanking(request);

        // then
        assertThat(getPokemonByRankingResponse)
                .isNotNull()
                .extracting(GetPokemonByRankingResponse::pokemons)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .isNotEmpty()
                .hasSize(limit)
                .isEqualTo(expectedPokemonsRankedByBaseExperience);
    }

}