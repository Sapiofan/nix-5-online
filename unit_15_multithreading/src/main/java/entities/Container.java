package entities;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Horse> horses = new ArrayList<>();

    public void addHorse(Horse horse) {
        horses.add(horse);
    }

    public List<Horse> getHorses() {
        return horses;
    }
}
