package main;

import services.CountPrimeNumbers;
import services.HelloFromThread;

import java.util.*;
import java.util.concurrent.Callable;

public class MainClass {
    public static void main(String[] args) {
        countPrimeNumbers();
        helloFromThread();
    }

    private static void countPrimeNumbers() {
        System.out.println("Task 1: Count prime numbers\n");

        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            numbers.add((int) (Math.random() * 100));
        }

        System.out.println("All random generated numbers:");
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        List<Integer> firstPart = new ArrayList<>(numbers.subList(0, numbers.size() / 2));
        List<Integer> secondPart = new ArrayList<>(numbers.subList(numbers.size() / 2, numbers.size()));

        CountPrimeNumbers count1 = new CountPrimeNumbers(firstPart);
        CountPrimeNumbers count2 = new CountPrimeNumbers(secondPart);
        Thread thread1 = new Thread(count1);
        Thread thread2 = new Thread(count2);

        List<Integer> firstPrimes;
        List<Integer> secondPrimes;
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        firstPrimes = count1.getPrimeNumbers();
        secondPrimes = count2.getPrimeNumbers();

        firstPrimes.addAll(secondPrimes);
        Collections.sort(firstPrimes);

        System.out.println("\nQuantity of prime numbers: " + firstPrimes.size());

        System.out.println("Prime numbers");
        Set<Integer> set = new LinkedHashSet<>();
        for (Integer firstPrime : firstPrimes) {
            System.out.print(firstPrime + " ");
            set.add(firstPrime);
        }

        System.out.println("\nPrime numbers without repetition");
        for (Integer i : set) {
            System.out.print(i + " ");
        }
    }

    private static void helloFromThread() {
        System.out.println("\n\nTask 2: Hello from thread\n");

        Callable<String> thread = new HelloFromThread("Hello from thread " + 0, 0);
        try {
            thread.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
