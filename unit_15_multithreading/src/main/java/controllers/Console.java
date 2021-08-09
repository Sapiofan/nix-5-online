package controllers;

import service.CountTime;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    public void chooseHorse() {
        System.out.println("Choose the number of horse(1-10)");
        int horse = correctInput();
        CountTime countTime = new CountTime();
        countTime.horseResult(horse);
    }

    private int correctInput() {
        int number;
        Scanner scanner = new Scanner(System.in);
        try {
            number = scanner.nextInt();
            if (number <= 0 || number >= 11) {
                System.out.println("You can choose horse number from 1 to 10. Repeat inputting, please");
                number = correctInput();
            }
            return number;
        } catch (InputMismatchException e) {
            throw new RuntimeException("Your input is not a number");
        }
    }
}
