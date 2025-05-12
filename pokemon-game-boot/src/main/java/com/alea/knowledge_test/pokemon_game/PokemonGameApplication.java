package com.alea.knowledge_test.pokemon_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokemonGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonGameApplication.class, args);
	}

}
