package entities;

import java.util.Objects;

public class Solution {
    private int problem_id;
    private int cost;

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
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
        Solution solution = (Solution) o;
        return problem_id == solution.problem_id &&
                cost == solution.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(problem_id, cost);
    }
}
