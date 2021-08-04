package main;

import services.CountPrimeNumbers;

import java.util.*;

public class MainForCounting {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            numbers.add((int) (Math.random()*100));
        }

        System.out.println("All random generated numbers:");
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }

        List<Integer> firstPart = new ArrayList<>(numbers.subList(0, numbers.size()/2));
        List<Integer> secondPart = new ArrayList<>(numbers.subList(numbers.size()/2, numbers.size()));

        CountPrimeNumbers count1 = new CountPrimeNumbers(firstPart);
        CountPrimeNumbers count2 = new CountPrimeNumbers(secondPart);
        Thread thread1 = new Thread(count1);
        Thread thread2 = new Thread(count2);

        List<Integer> firstPrimes;
        List<Integer> secondPrimes;
//        thread1.start();
//        thread2.start();
        thread1.run();
        thread2.run();

        firstPrimes = count1.getPrimeNumbers();
        secondPrimes = count2.getPrimeNumbers();

        firstPrimes.addAll(secondPrimes);
        Collections.sort(firstPrimes);

        System.out.println("\nQuantity of prime numbers: " + firstPrimes.size());

        System.out.println("Prime numbers");
        var set = new LinkedHashSet<>();
        for (Integer firstPrime : firstPrimes) {
            System.out.print(firstPrime +" ");
            set.add(firstPrime);
        }

        System.out.println("\nPrime numbers without repetition");
        Iterator itr = set.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
    }
}
