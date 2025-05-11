package com.alea.knowledge_test.pokemon_game.application.request;

import com.alea.knowledge_test.pokemon_game.application.ranking.RankingCriteria;

public record GetPokemonByRankingRequest(RankingCriteria rankingCriteria, int limit) {
}
