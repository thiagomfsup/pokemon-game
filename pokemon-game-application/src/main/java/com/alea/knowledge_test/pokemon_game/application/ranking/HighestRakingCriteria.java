package com.alea.knowledge_test.pokemon_game.application.ranking;

import com.alea.knowledge_test.pokemon_game.domain.model.Pokemon;

import java.util.Comparator;

public class HighestRakingCriteria implements RankingCriteria {
    @Override
    public Comparator<Pokemon> sortCriteria() {
        return Comparator.comparingInt(Pokemon::height).reversed();
    }
}
