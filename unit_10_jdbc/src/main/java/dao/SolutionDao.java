package dao;

import entities.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private final Connection connection;

    private final String INSERT = "INSERT INTO solutions (problem_id, cost) VALUES (?, ?)";
    private final String ALL = "SELECT * FROM solutions";

    public SolutionDao(Connection connection) {
        this.connection = connection;
    }

    public List<Solution> allSolutions(){
        List<Solution> solutions = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL);
            while (resultSet.next()){
                Solution solution = new Solution();
                solution.setProblem_id(resultSet.getInt(1));
                solution.setCost(resultSet.getInt(2));
                solutions.add(solution);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return solutions;
    }

    public void adding(List<Solution> solutions){

        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            for (Solution solution : solutions) {
                statement.setInt(1, solution.getProblem_id());
                statement.setInt(2, solution.getCost());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
