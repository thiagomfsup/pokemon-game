package com.alea.knowledge_test.pokemon_game.pokeapi_client;

import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.PokemonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PokeApiClientTest {

    private static final int LIMIT_PER_PAGE = 10;

    private static MockWebServer mockWebServer;
    private static PokeApiClient pokeApiClient;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        mockWebServer = new MockWebServer();
        pokeApiClient = new PokeApiClient(WebClient.builder(), mockWebServer.url("/").toString(), LIMIT_PER_PAGE);
        objectMapper = new ObjectMapper();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.close();
    }

    @Test
    void given_pokeApiReturnsEmptyResponse_when_callingPokeApi_then_returnEmptyList() {
        // given
        String emptyResponse = "{}";

        // when
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody(emptyResponse));

        // then
        final var pokemons = pokeApiClient.retrieveAllPokemons();

        assertThat(pokemons).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 3 })
    void given_allAvailablePagination_when_callingPokeApi_then_returnPokemonList(int numberOfPages) {
        // given
        final var numberOfInstances = LIMIT_PER_PAGE * numberOfPages;
        final var expectedPokemonDTOs = PokemonFixtures.generateRandomPokemons(numberOfInstances);
        final var allPages = PokemonFixtures.allPages(expectedPokemonDTOs, LIMIT_PER_PAGE);

        // when
        int page = 0;
        for(var resourceListDTO : allPages) {
            mockWebServer.enqueue(new MockResponse()
                    .setResponseCode(HttpStatus.OK.value())
                    .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                    .setBody(toJson(resourceListDTO)));

            expectedPokemonDTOs.stream()
                    .skip((long) LIMIT_PER_PAGE * page++)
                    .limit(resourceListDTO.count()).forEach(
                            pokemonDTO -> mockWebServer.enqueue(new MockResponse()
                                    .setResponseCode(HttpStatus.OK.value())
                                    .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                                    .setBody(toJson(pokemonDTO))));
        }

        // then
        final var pokemons = pokeApiClient.retrieveAllPokemons();

        assertThat(pokemons)
                .isNotEmpty()
                .hasSize(expectedPokemonDTOs.size())
                .isEqualTo(expectedPokemonDTOs.stream().map(PokemonDTO::toModel).toList());
    }

    @Test
    public void given_pokeApiFailsWithHTTP500_when_callingPokeApi_then_thrownAnException() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value()));

        Assertions.assertThatThrownBy(() -> pokeApiClient.retrieveAllPokemons())
                .isInstanceOf(RuntimeException.class);
    }

    private <T> String toJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}