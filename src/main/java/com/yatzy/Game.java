package com.yatzy;

import com.yatzy.domain.model.Roll;
import com.yatzy.domain.service.Calculator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
@ComponentScan
@Command(name = "Yatzy", mixinStandardHelpOptions = true, description = "Play Yatzy")
public class Game implements Runnable {

    Calculator calculator = new Calculator();
    @CommandLine.Option(names = {"-rer", "--reRolls"}, description = "Number of dices reRolls", defaultValue = "3")
    private int reRolls;

    public static void main(String[] args) {
        new CommandLine(new Game()).execute(args);
    }

    @Override
    public void run() {
        Random random = new Random();
        int[] dices = IntStream.generate(() -> random.nextInt(6) + 1)
                .limit(5)
                .toArray();
        Roll roll = new Roll(dices);
        System.out.println("Here is your first roll: " + Arrays.toString(roll.dices()));
        calculator.execute(roll);
        System.out.println("The scores your roll gives you: ");
        roll.scores().forEach((scoreCategory, integer) -> System.out.println(scoreCategory + ": " + integer));
    }
}


