package com.alea.knowledge_test.pokemon_game.rest.controller;

import com.alea.knowledge_test.pokemon_game.application.request.HeaviestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.request.HighestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HeaviestPokemonResponse;
import com.alea.knowledge_test.pokemon_game.application.response.HighestPokemonRequest;
import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.HeaviestPokemonUseCase;
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

    private final HeaviestPokemonUseCase heaviestPokemonUseCase;

    public PokemonController(ListPokemonsUseCase listPokemonsUseCase,
                             HighestPokemonUseCase highestPokemonUseCase,
                             HeaviestPokemonUseCase heaviestPokemonUseCase) {
        this.listPokemonsUseCase = listPokemonsUseCase;
        this.highestPokemonUseCase = highestPokemonUseCase;
        this.heaviestPokemonUseCase = heaviestPokemonUseCase;
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

    @GetMapping(value = "/pokemon", params = "heaviest")
    public HeaviestPokemonResponse heaviestPokemons() {
        final var request = new HeaviestPokemonRequest(5);

        return heaviestPokemonUseCase.heaviestPokemon(request);
    }

}
