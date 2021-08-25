package service;

import entities.Horse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountTime {

    private static final int THREADS = 10;

    public void horseResult(int number) {
        ExecutorService service = Executors.newFixedThreadPool(THREADS);
//        ExecutorService service = Executors.newCachedThreadPool();

        List<Horse> horses = new ArrayList<>();
        List<Horse> synHorses = Collections.synchronizedList(horses);

        for (int i = 1; i <= THREADS; i++) {
            Horse horse = new Horse();
            horse.setId(i);
            horse.setHorses(synHorses);
            service.execute(horse);
        }
        service.shutdown();

        try {
            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int counter = 1;
        int horseForWin = 0;
        for (Horse horse : horses) {
            System.out.println(counter + " place: " + horse.getId() + " horse" + ", sleep time (milliseconds): " + horse.getSleep());
            counter++;
            if (horse.getId() == number)
                horseForWin = counter;
        }
        System.out.println("\nYour horse took " + (horseForWin - 1) + " place");
    }
}
