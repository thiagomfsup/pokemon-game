package com.alea.knowledge_test.pokemon_game;

import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.PokemonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 8081)
@TestPropertySource(properties = {
        "pokemon-game.pokeapi-client.base-url=http://localhost:8081",
        "pokemon-game.pokeapi-client.limit-per-url=5"
})
public class PokemonGameIT {
    @Autowired
    private MockMvc mvc;

    @Value("${pokemon-game.pokeapi-client.limit-per-url}")
    private int limitPerUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws IOException {
        final var firstPageAsString = Files.readString(ResourceUtils.getFile("classpath:pokeapi_response/firstpage.json").toPath());
        final var secondPageAsString = Files.readString(ResourceUtils.getFile("classpath:pokeapi_response/secondpage.json").toPath());

        stubFor(WireMock.get(urlPathEqualTo("/pokemon"))
                .withQueryParam("limit", equalTo(String.valueOf(limitPerUrl)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(firstPageAsString)));

        stubFor(WireMock.get(urlPathEqualTo("/pokemon"))
                .withQueryParam("limit", equalTo(String.valueOf(limitPerUrl)))
                .withQueryParam("offset", equalTo("5"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(secondPageAsString)));

        for (Resource resource : new PathMatchingResourcePatternResolver().getResources("classpath:pokeapi_response/pokemon/*.json")) {
            final var pokemonDTO = objectMapper.readValue(resource.getInputStream(), PokemonDTO.class);
            stubFor(WireMock.get(urlPathEqualTo("/pokemon/%d/".formatted(pokemonDTO.id())))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            .withBody(objectMapper.writeValueAsString(pokemonDTO))));
        }
    }

    @Test
    public void shouldRetrieveListOfHighestPokemon() throws Exception {
        final var highestAsString = Files.readString(ResourceUtils
                .getFile("classpath:pokeapi_response/highest.json").toPath());

        mvc.perform(get("/api/v1/pokemon/top/height").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(highestAsString));
    }

    @Test
    public void shouldRetrieveListOfHeaviestPokemon() throws Exception {
        final var heaviestAsString = Files.readString(ResourceUtils
                .getFile("classpath:pokeapi_response/heaviest.json").toPath());

        mvc.perform(get("/api/v1/pokemon/top/weight").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(heaviestAsString));
    }

    @Test
    public void shouldRetrievePokemonRankedByBaseExperience() throws Exception {
        final var baseExperienceRankingAsString = Files.readString(ResourceUtils
                .getFile("classpath:pokeapi_response/baseExperienceRanking.json").toPath());

        mvc.perform(get("/api/v1/pokemon/top/base_experience").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(baseExperienceRankingAsString));
    }
}
