package com.alea.knowledge_test.pokemon_game.pokeapi_client;

import com.alea.knowledge_test.pokemon_game.application.repository.PokemonRepository;
import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;
import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.PokemonDTO;
import com.alea.knowledge_test.pokemon_game.pokeapi_client.dto.ResourceListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class PokeApiClient implements PokemonRepository {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final WebClient webClient;
    private final int limitPerPage;

    public PokeApiClient(WebClient.Builder webClientBuilder,
                         @Value("${pokemon-game.pokeapi-client.base-url}") String baseUrl,
                         @Value("${pokemon-game.pokeapi-client.limit-per-url}") int limitPerPage) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.limitPerPage = limitPerPage;
    }

    @Override
    public List<Pokemon> retrieveAllPokemons() {
        try {
            return fetchResources("/pokemon?limit=%d".formatted(limitPerPage), ResourceListDTO.class)
                    .expand(resourceListDTO -> resourceListDTO.next() == null ? Mono.empty() : fetchResources(resourceListDTO.next(), ResourceListDTO.class))
                    .flatMap(resourceListDTO -> Flux.fromIterable(Objects.requireNonNullElse(resourceListDTO.results(), List.of())))
                    .flatMap(resultDTO -> fetchResources(resultDTO.url(), PokemonDTO.class))
                    .collectList()
                    .toFuture()
                    .get()
                    .stream().map(PokemonDTO::toModel)
                    .toList();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> Mono<T> fetchResources(String url, Class<T> clazz) {
        log.debug("Calling {} URL. Expecting 'casting' to {}", url, clazz);
        return webClient.get().uri(url).retrieve().bodyToMono(clazz);
    }
}
