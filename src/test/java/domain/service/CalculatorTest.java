package domain.service;

import domain.model.Roll;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    /**
     * @Test public void verify_that_calculator_execute_all_score_categories() {
     * Calculator mockecCalculator = mock(Calculator.class);
     * Roll first_roll = new Roll(6, 5, 4, 3, 2);
     * mockecCalculator.execute(first_roll);
     * verify(mockecCalculator, times(1)).;
     * <p>
     * }
     **/
    @Test
    public void execute_calculate_every_score_category() {
        Roll first_roll = new Roll(3, 3, 4, 4, 4);
        Roll second_roll = new Roll(1, 2, 3, 4, 5);
        calculator.execute(first_roll);
        calculator.execute(second_roll);
        Assertions.assertThat(first_roll.scores().values().toArray()).containsExactlyInAnyOrder(new Integer[]{0, 6, 18, 0, 0, 0, 0, 0, 0, 14, 12, 18, 8, 12, 0});
        Assertions.assertThat(second_roll.scores().values().toArray()).containsExactlyInAnyOrder(new Integer[]{0, 3, 0, 0, 2, 0, 1, 0, 5, 0, 0, 15, 0, 4, 15});
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