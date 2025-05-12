package com.alea.knowledge_test.pokemon_game.application.ranking;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.function.ToIntFunction;

public interface RankingCriteria extends ToIntFunction<Pokemon> {
}
