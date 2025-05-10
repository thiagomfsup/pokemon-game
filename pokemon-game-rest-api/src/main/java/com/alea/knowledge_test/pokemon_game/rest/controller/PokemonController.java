package com.alea.knowledge_test.pokemon_game.rest.controller;

import com.alea.knowledge_test.pokemon_game.application.response.PokemonRespose;
import com.alea.knowledge_test.pokemon_game.application.usecase.ListPokemonsUseCase;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PokemonController {

    private final ListPokemonsUseCase listPokemonsUseCase;

    public PokemonController(ListPokemonsUseCase listPokemonsUseCase) {
        this.listPokemonsUseCase = listPokemonsUseCase;
    }

    @GetMapping(value = "/pokemon")
    public List<PokemonRespose> list() { // TODO Do no expose domain
        return listPokemonsUseCase.listPokemons();
    }

    @GetMapping(value = "/pokemon", params = "highest")
    public void highestPokemons() {

    }

}
