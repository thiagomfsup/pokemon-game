package com.alea.knowledge_test.pokemon_game.rest.controller;

import com.alea.knowledge_test.pokemon_game.application.request.GetPokemonByRankingRequest;
import com.alea.knowledge_test.pokemon_game.application.response.GetPokemonByRankingResponse;
import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.GetPokemonByRankingUseCase;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PokemonController {

    private final ListPokemonsUseCase listPokemonsUseCase;

    private final GetPokemonByRankingUseCase getPokemonByRanking;

    private final int defaultTopLimit;

    public PokemonController(ListPokemonsUseCase listPokemonsUseCase,
                             GetPokemonByRankingUseCase getPokemonByRanking,
                             @Value("${pokemon-game.ranking.default-top-limit}") int defaultTop) {
        this.listPokemonsUseCase = listPokemonsUseCase;
        this.getPokemonByRanking = getPokemonByRanking;
        this.defaultTopLimit = defaultTop;
    }

    @GetMapping(value = "/pokemon")
    public List<PokemonRespose> list() { // TODO Do no expose domain
        return listPokemonsUseCase.listPokemons();
    }

    @GetMapping(value = "/pokemon/top/height")
    public GetPokemonByRankingResponse highestPokemons() {
        final var request = new GetPokemonByRankingRequest(Pokemon::height, defaultTopLimit);

        return getPokemonByRanking.getPokemonByRanking(request);
    }

    @GetMapping(value = "/pokemon/top/weight")
    public GetPokemonByRankingResponse heaviestPokemons() {
        final var request = new GetPokemonByRankingRequest(Pokemon::weight, defaultTopLimit);

        return getPokemonByRanking.getPokemonByRanking(request);
    }

    @GetMapping(value = "/pokemon/top/base_experience")
    public GetPokemonByRankingResponse pokemonsRankedByBaseExperience() {
        final var request = new GetPokemonByRankingRequest(Pokemon::baseExperience, defaultTopLimit);

        return getPokemonByRanking.getPokemonByRanking(request);
    }

}
