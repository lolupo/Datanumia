package com.yatzy;

import com.yatzy.domain.model.Roll;
import com.yatzy.domain.service.Calculator;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

@Command(name = "Yatzy", mixinStandardHelpOptions = true, description = "Play Yatzy")
public class Game implements Runnable {

    final Calculator calculator = new Calculator();
    @CommandLine.Option(names = {"-rer", "--reRolls"}, description = "Number of dices reRolls", defaultValue = "3")
    private int reRolls;

    public static void main(String[] args) {
        new CommandLine(new Game()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Welcome to Yatzy - The Dice Game!");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int[] dices = IntStream.generate(() -> random.nextInt(6) + 1)
                .limit(5)
                .toArray();
        Roll roll = new Roll(dices);

        boolean exit = false;
        while (!exit) {
            announceRoll(roll);
            calculator.execute(roll);
            displayScores(roll);
            if (reRolls == 0) {
                exit = true;
            } else {
                System.out.println("You can re roll one of your dices : " + reRolls + " re-rolls left");
                System.out.println("Which dice do you want to re roll? (1-5) / Or type 'exit' to exit");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    exit = true;
                } else {
                    if(!input.matches("[1-5]")) {
                        System.out.println("Invalid input !");
                        continue;
                    }
                    int diceIndex = Integer.parseInt(input) - 1;
                    roll.dices()[diceIndex] = random.nextInt(6) + 1;
                    reRolls--;
                }
            }

        }
        if (reRolls == 0) {
            System.out.println("You have no more re-rolls left");
        }
        System.out.println("Thanks for playing!");
        System.out.println("Ready to exit? (y/n)");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Bye!");
        }
        scanner.close();
        System.exit(0);
    }

    private void displayScores(Roll roll) {
        System.out.println("The scores this roll gives you: ");
        roll.scores().forEach((scoreCategory, integer) -> System.out.println(scoreCategory + ": " + integer));
    }

    private void announceRoll(Roll roll) {
        System.out.println("Here is your roll: " + Arrays.toString(roll.dices()));
    }


}


