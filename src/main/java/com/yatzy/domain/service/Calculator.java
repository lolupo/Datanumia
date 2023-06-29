package com.yatzy.domain.service;

import com.yatzy.domain.model.Roll;
import com.yatzy.domain.model.ScoreCategory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calculator {

    public void execute(Roll roll) {
        roll.scores().forEach((category, score) -> {
            int calculatedScore = calculateScoreByCategory(category, roll.dices());
            roll.scores().put(category, calculatedScore);
        });
    }

    protected int calculateScoreByCategory(ScoreCategory category, int[] dices) {
        switch (category) {
            case YATZY -> {
                return yatzy(dices);
            }
            case CHANCE -> {
                return chance(dices);
            }
            case ONES -> {
                return onesTwosThreesFoursFivesSixes(dices, 1);
            }
            case TWOS -> {
                return onesTwosThreesFoursFivesSixes(dices, 2);
            }
            case THREES -> {
                return onesTwosThreesFoursFivesSixes(dices, 3);
            }
            case FOURS -> {
                return onesTwosThreesFoursFivesSixes(dices, 4);
            }
            case FIVES -> {
                return onesTwosThreesFoursFivesSixes(dices, 5);
            }
            case SIXES -> {
                return onesTwosThreesFoursFivesSixes(dices, 6);
            }
            case PAIR -> {
                return score_pair(dices);
            }
            case TWO_PAIR -> {
                return two_pair(dices);
            }
            case THREE_OF_A_KIND -> {
                return three_of_a_kind(dices);
            }
            case FOUR_OF_A_KIND -> {
                return four_of_a_kind(dices);
            }
            case SMALL_STRAIGHT -> {
                return smallStraight(dices);
            }
            case LARGE_STRAIGHT -> {
                return largeStraight(dices);
            }
            case FULL_HOUSE -> {
                return fullHouse(dices);
            }
            default -> {
                return 0;
            }
        }
    }

    protected int chance(int[] dices) {
        return IntStream.of(dices).sum();
    }

    protected int yatzy(int[] dices) {
        return IntStream.of(dices).distinct().count() == 1 ? 50 : 0;
    }

    protected int onesTwosThreesFoursFivesSixes(int[] dices, int number) {
        return IntStream.of(dices).filter(d -> d == number).sum();

    }

    protected int score_pair(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> countOccurrences(dices, n) >= 2).max().orElse(0) * 2;
    }

    protected int two_pair(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> countOccurrences(dices, n) >= 2).sum() * 2;
    }

    protected int four_of_a_kind(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> countOccurrences(dices, n) >= 4).max().orElse(0) * 4;
    }

    protected int three_of_a_kind(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> countOccurrences(dices, n) >= 3).max().orElse(0) * 3;
    }

    protected long countOccurrences(int[] numbers, int n) {
        return IntStream.of(numbers).filter(i -> i == n).count();
    }


    protected int smallStraight(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> n >= 1 && n <= 5).count() == 5 ? 15 : 0;
    }

    protected int largeStraight(int[] dices) {
        return IntStream.of(dices).distinct().filter(n -> n >= 2 && n <= 6).count() == 5 ? 20 : 0;
    }

    protected int fullHouse(int[] dices) {
        AtomicInteger result = new AtomicInteger(0);
        Arrays.stream(dices)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> {
                    if (v.intValue() == 2 || v.intValue() == 3) {
                        result.addAndGet(k * v.intValue());
                    }
                });
        return result.get();
    }


}
