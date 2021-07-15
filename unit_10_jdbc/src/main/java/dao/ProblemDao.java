package dao;

import entities.Problem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemDao {
    private final Connection connection;

    private static final String ALL = "SELECT * FROM problems LEFT JOIN solutions ON solutions.problem_id=problems.id WHERE cost is NULL";

    public ProblemDao(Connection connection) {
        this.connection = connection;
    }


    public List<Problem> allProblems(){
        List<Problem> problems = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Problem problem = new Problem();
                problem.setId(resultSet.getInt(1));
                problem.setFrom_id(resultSet.getInt(2));
                problem.setTo_id(resultSet.getInt(3));
                problems.add(problem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return problems;
    }
}
