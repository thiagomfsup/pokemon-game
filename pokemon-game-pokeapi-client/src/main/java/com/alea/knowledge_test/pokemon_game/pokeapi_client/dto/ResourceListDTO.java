package com.alea.knowledge_test.pokemon_game.pokeapi_client.dto;

import java.util.List;

public record ResourceListDTO(int count, String next, String previous, List<ResultDTO> results) {
    public record ResultDTO(String name, String url) {
    }
}
