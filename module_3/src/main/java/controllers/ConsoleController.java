package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AddingOfOperations;
import services.WriteToCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ConsoleController {

    private static final Logger logInfo = LoggerFactory.getLogger("info");
    private static final Logger logWarn = LoggerFactory.getLogger("warn");
    private static final Logger logError = LoggerFactory.getLogger("error");

    public void start() {
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name;
        String email;
        String password;
        try {
            System.out.println("Input your first and last names (separated by space):");
            name = reader.readLine();// Test Name
            logInfo.info("Inputted name: " + name);
            System.out.println("Input your email:");
            email = reader.readLine(); // testname@gmail.com
            logInfo.info("Inputted email: " + email);
            System.out.println("Input your password:");
            password = reader.readLine(); // password
            logInfo.info("Inputted password: " + password);
        } catch (IOException e) {
            System.out.println("Error when try to input data for signing in");
            logError.error("Error when try to input data for signing in");
            throw new RuntimeException(e);
        }

        while (true) {
            System.out.println("Choose an action:\n" +
                    "1 >> add new operation\n" +
                    "2 >> export operation for a certain period\n" +
                    "3 >> exit from program");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    AddingOfOperations adding = new AddingOfOperations();
                    adding.addOperation(name, email, password);
                }
                case "2" -> {
                    WriteToCSV writeToCSV = new WriteToCSV();
                    writeToCSV.exportInCSV(name, email, password);
                }
                case "3" -> System.exit(0);
                default -> {
                    System.out.println("Wrong input");
                    logWarn.warn("Incorrect number when chose action");
                }
            }
        }
    }
}
