package com.yatzy.domain.service;

import com.yatzy.domain.model.Roll;
import com.yatzy.domain.model.ScoreCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculatorTest {

    Calculator calculator = new Calculator();


    @Test
    public void execute_calculate_every_score_category() {
        Roll roll = new Roll(1, 2, 3, 4, 5);
        calculator.execute(roll);
        assertEquals(roll.scores().get(ScoreCategory.YATZY), 0);
        assertEquals(roll.scores().get(ScoreCategory.CHANCE), 15);
        assertEquals(roll.scores().get(ScoreCategory.ONES), 1);
        assertEquals(roll.scores().get(ScoreCategory.TWOS), 2);
        assertEquals(roll.scores().get(ScoreCategory.THREES), 3);
        assertEquals(roll.scores().get(ScoreCategory.FOURS), 4);
        assertEquals(roll.scores().get(ScoreCategory.FIVES), 5);
        assertEquals(roll.scores().get(ScoreCategory.SIXES), 0);
        assertEquals(roll.scores().get(ScoreCategory.PAIR), 0);
        assertEquals(roll.scores().get(ScoreCategory.TWO_PAIR), 0);
        assertEquals(roll.scores().get(ScoreCategory.THREE_OF_A_KIND), 0);
        assertEquals(roll.scores().get(ScoreCategory.FOUR_OF_A_KIND), 0);
        assertEquals(roll.scores().get(ScoreCategory.SMALL_STRAIGHT), 15);
        assertEquals(roll.scores().get(ScoreCategory.LARGE_STRAIGHT), 0);
        assertEquals(roll.scores().get(ScoreCategory.FULL_HOUSE), 0);
    }

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertEquals(15, calculator.chance(new int[]{2, 3, 4, 5, 1}));
        assertEquals(16, calculator.chance(new int[]{3, 3, 4, 5, 1}));
    }

    @Test
    public void yatzy_scores_50() {
        assertEquals(50, calculator.yatzy(new int[]{4, 4, 4, 4, 4}));
        assertEquals(50, calculator.yatzy(new int[]{6, 6, 6, 6, 6}));
        assertEquals(0, calculator.yatzy(new int[]{6, 6, 6, 6, 3}));
    }

    @Test
    public void test_1s() {
        assertEquals(1, calculator.onesTwosThreesFoursFivesSixes(new int[]{1, 2, 3, 4, 5}, 1));
        assertEquals(2, calculator.onesTwosThreesFoursFivesSixes(new int[]{1, 2, 1, 4, 5}, 1));
        assertEquals(0, calculator.onesTwosThreesFoursFivesSixes(new int[]{6, 2, 2, 4, 5}, 1));
        assertEquals(4, calculator.onesTwosThreesFoursFivesSixes(new int[]{1, 2, 1, 1, 1}, 1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, calculator.onesTwosThreesFoursFivesSixes(new int[]{1, 2, 3, 2, 6}, 2));
        assertEquals(10, calculator.onesTwosThreesFoursFivesSixes(new int[]{2, 2, 2, 2, 2}, 2));
    }

    @Test
    public void test_threes() {
        assertEquals(6, calculator.onesTwosThreesFoursFivesSixes(new int[]{1, 2, 3, 2, 3}, 3));
        assertEquals(12, calculator.onesTwosThreesFoursFivesSixes(new int[]{2, 3, 3, 3, 3}, 3));
    }

    @Test
    public void fours_test() {
        assertEquals(12, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 4, 5, 5}, 4));
        assertEquals(8, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 5, 5, 5}, 4));
        assertEquals(4, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 5, 5, 5, 5}, 4));
    }

    @Test
    public void fives() {
        assertEquals(10, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 4, 5, 5}, 5));
        assertEquals(15, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 5, 5, 5}, 5));
        assertEquals(20, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 5, 5, 5, 5}, 5));
    }

    @Test
    public void sixes_test() {
        assertEquals(0, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 4, 5, 5}, 6));
        assertEquals(6, calculator.onesTwosThreesFoursFivesSixes(new int[]{4, 4, 6, 5, 5}, 6));
        assertEquals(18, calculator.onesTwosThreesFoursFivesSixes(new int[]{6, 5, 6, 6, 5}, 6));
    }

    @Test
    public void one_pair() {
        assertEquals(6, calculator.score_pair(new int[]{3, 4, 3, 5, 6}));
        assertEquals(10, calculator.score_pair(new int[]{5, 3, 3, 3, 5}));
        assertEquals(12, calculator.score_pair(new int[]{5, 3, 6, 6, 5}));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, calculator.two_pair(new int[]{3, 3, 5, 4, 5}));
        assertEquals(16, calculator.two_pair(new int[]{3, 3, 5, 5, 5}));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, calculator.three_of_a_kind(new int[]{3, 3, 3, 4, 5}));
        assertEquals(15, calculator.three_of_a_kind(new int[]{5, 3, 5, 4, 5}));
        assertEquals(9, calculator.three_of_a_kind(new int[]{3, 3, 3, 3, 5}));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, calculator.four_of_a_kind(new int[]{3, 3, 3, 3, 5}));
        assertEquals(20, calculator.four_of_a_kind(new int[]{5, 5, 5, 4, 5}));
        assertEquals(9, calculator.three_of_a_kind(new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, calculator.smallStraight(new int[]{1, 2, 3, 4, 5}));
        assertEquals(15, calculator.smallStraight(new int[]{2, 3, 4, 5, 1}));
        assertEquals(0, calculator.smallStraight(new int[]{1, 2, 2, 4, 5}));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, calculator.largeStraight(new int[]{6, 2, 3, 4, 5}));
        assertEquals(20, calculator.largeStraight(new int[]{2, 3, 4, 5, 6}));
        assertEquals(0, calculator.largeStraight(new int[]{1, 2, 2, 4, 5}));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, calculator.fullHouse(new int[]{6, 2, 2, 2, 6}));
        assertEquals(0, calculator.fullHouse(new int[]{2, 3, 4, 5, 6}));
    }

}