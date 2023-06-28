package com.yatzy.domain.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Roll(int[] dices, Map<ScoreCategory, Integer> scores) {

    public Roll(int d1, int d2, int d3, int d4, int d5) {
        this(new int[]{d1, d2, d3, d4, d5},
                Stream.of(
                        Map.entry(ScoreCategory.YATZY, 0),
                        Map.entry(ScoreCategory.CHANCE, 0),
                        Map.entry(ScoreCategory.ONES, 0),
                        Map.entry(ScoreCategory.TWOS, 0),
                        Map.entry(ScoreCategory.THREES, 0),
                        Map.entry(ScoreCategory.FOURS, 0),
                        Map.entry(ScoreCategory.FIVES, 0),
                        Map.entry(ScoreCategory.SIXES, 0),
                        Map.entry(ScoreCategory.PAIR, 0),
                        Map.entry(ScoreCategory.TWO_PAIR, 0),
                        Map.entry(ScoreCategory.THREE_OF_A_KIND, 0),
                        Map.entry(ScoreCategory.FOUR_OF_A_KIND, 0),
                        Map.entry(ScoreCategory.SMALL_STRAIGHT, 0),
                        Map.entry(ScoreCategory.LARGE_STRAIGHT, 0),
                        Map.entry(ScoreCategory.FULL_HOUSE, 0)
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public Roll(int[] dices) {
        this(dices,
                Stream.of(
                        Map.entry(ScoreCategory.YATZY, 0),
                        Map.entry(ScoreCategory.CHANCE, 0),
                        Map.entry(ScoreCategory.ONES, 0),
                        Map.entry(ScoreCategory.TWOS, 0),
                        Map.entry(ScoreCategory.THREES, 0),
                        Map.entry(ScoreCategory.FOURS, 0),
                        Map.entry(ScoreCategory.FIVES, 0),
                        Map.entry(ScoreCategory.SIXES, 0),
                        Map.entry(ScoreCategory.PAIR, 0),
                        Map.entry(ScoreCategory.TWO_PAIR, 0),
                        Map.entry(ScoreCategory.THREE_OF_A_KIND, 0),
                        Map.entry(ScoreCategory.FOUR_OF_A_KIND, 0),
                        Map.entry(ScoreCategory.SMALL_STRAIGHT, 0),
                        Map.entry(ScoreCategory.LARGE_STRAIGHT, 0),
                        Map.entry(ScoreCategory.FULL_HOUSE, 0)
                ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

}
