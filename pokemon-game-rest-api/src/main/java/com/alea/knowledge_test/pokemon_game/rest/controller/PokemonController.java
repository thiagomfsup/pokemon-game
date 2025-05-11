package com.alea.knowledge_test.pokemon_game.rest.controller;

import com.alea.knowledge_test.pokemon_game.application.request.GetPokemonByRankingRequest;
import com.alea.knowledge_test.pokemon_game.application.ranking.HeaviestRankingCriteria;
import com.alea.knowledge_test.pokemon_game.application.ranking.HighestRakingCriteria;
import com.alea.knowledge_test.pokemon_game.application.response.GetPokemonByRankingResponse;
import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.GetPokemonByRankingUseCase;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PokemonController {

    private final ListPokemonsUseCase listPokemonsUseCase;

    private final GetPokemonByRankingUseCase getPokemonByRanking;

    public PokemonController(ListPokemonsUseCase listPokemonsUseCase,
                             GetPokemonByRankingUseCase getPokemonByRanking) {
        this.listPokemonsUseCase = listPokemonsUseCase;
        this.getPokemonByRanking = getPokemonByRanking;
    }

    @GetMapping(value = "/pokemon")
    public List<PokemonRespose> list() { // TODO Do no expose domain
        return listPokemonsUseCase.listPokemons();
    }

    @GetMapping(value = "/pokemon", params = "highest")
    public GetPokemonByRankingResponse highestPokemons() {
        final var request = new GetPokemonByRankingRequest(new HighestRakingCriteria(), 5);

        return getPokemonByRanking.getPokemonByRanking(request);
    }

    @GetMapping(value = "/pokemon", params = "heaviest")
    public GetPokemonByRankingResponse heaviestPokemons() {
        final var request = new GetPokemonByRankingRequest(new HeaviestRankingCriteria(), 5);

        return getPokemonByRanking.getPokemonByRanking(request);
    }

}
