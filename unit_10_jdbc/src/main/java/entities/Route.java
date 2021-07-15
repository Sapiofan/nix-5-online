package entities;

import java.util.Objects;

public class Route {
    private int id;
    private int from_id;
    private int to_id;
    private int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                from_id == route.from_id &&
                to_id == route.to_id &&
                cost == route.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from_id, to_id, cost);
    }
}
