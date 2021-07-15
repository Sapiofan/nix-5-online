package entities;

import java.util.Objects;

public class Problem {
    private int id;
    private int from_id;
    private int to_id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return id == problem.id &&
                from_id == problem.from_id &&
                to_id == problem.to_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from_id, to_id);
    }
}
