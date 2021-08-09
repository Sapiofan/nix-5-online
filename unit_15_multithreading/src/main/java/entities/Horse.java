package entities;

import java.util.Random;

public class Horse implements Runnable {
    private int id;
    private int sleep;
    private Container container;
    private int distance = 0;

    @Override
    public void run() {
        int randomDistance, randomTime;
        Random random = new Random();
        while (distance < 1000) {
            randomDistance = random.nextInt(101) + 100;
            randomTime = random.nextInt(101) + 400;
            distance += randomDistance;
            try {
                if (distance < 1000) {
                    sleep += randomTime;
                    Thread.sleep(randomTime);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        container.addHorse(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public int getSleep() {
        return sleep;
    }
}
