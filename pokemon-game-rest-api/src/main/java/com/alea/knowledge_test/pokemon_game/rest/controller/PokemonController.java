package com.alea.knowledge_test.pokemon_game.rest.controller;

import com.alea.knowledge_test.pokemon_game.application.request.HighestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HighestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.HighestPokemonUseCase;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PokemonController {

    private final ListPokemonsUseCase listPokemonsUseCase;

    private final HighestPokemonUseCase highestPokemonUseCase;

    public PokemonController(ListPokemonsUseCase listPokemonsUseCase, HighestPokemonUseCase highestPokemonUseCase) {
        this.listPokemonsUseCase = listPokemonsUseCase;
        this.highestPokemonUseCase = highestPokemonUseCase;
    }

    @GetMapping(value = "/pokemon")
    public List<PokemonRespose> list() { // TODO Do no expose domain
        return listPokemonsUseCase.listPokemons();
    }

    @GetMapping(value = "/pokemon", params = "highest")
    public HighestPokemonResponse highestPokemons() {
        final var request = new HighestPokemonRequest(5);

        return highestPokemonUseCase.highestPokemon(request);
    }

}
