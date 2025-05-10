package com.alea.knowledge_test.pokemon_game.pokeapi_client.dto;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

public record PokemonDTO(int id, String name, int base_experience, int height, int weight) {

    public Pokemon toModel() {
        return new Pokemon(id, name, weight, height, base_experience);
    }

}
