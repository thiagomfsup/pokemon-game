package com.alea.knowledge_test.pokemon_game.pokeapi_client;

import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.PokemonDTO;
import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.ResourceListDTO;
import org.instancio.Instancio;

import java.util.List;
import java.util.stream.IntStream;

public class PokemonFixtures {

    public static List<PokemonDTO> generateRandomPokemons(int numberOfInstances) {
        return Instancio.ofList(PokemonDTO.class).size(numberOfInstances).create();
    }

    public static List<ResourceListDTO> allPages(List<PokemonDTO> pokemons, int limitPerPage) {
        final var resultDTOS = pokemons.stream()
                .map(pokemon -> new ResourceListDTO.ResultDTO(pokemon.name(), "/pokemon/" + pokemon.id()))
                .toList();

        final var pages = Math.ceilDiv(pokemons.size(), limitPerPage);

        return IntStream.range(0, pages).boxed()
                .map(page -> resourceList((pages - (page + 1)) <= 0 ? null : "/pokemon?limit=" + limitPerPage ,
                        resultDTOS.stream()
                                .skip((long) limitPerPage * page)
                                .limit(limitPerPage)
                                .toList()))
                .toList();
    }

    private static ResourceListDTO resourceList(String next, List<ResourceListDTO.ResultDTO> results) {
        return new ResourceListDTO(results.size(), next, null, results);
    }
}
