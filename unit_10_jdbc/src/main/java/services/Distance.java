package services;

import dao.LocationDao;
import dao.ProblemDao;
import dao.RouteDao;
import dao.SolutionDao;
import entities.Problem;
import entities.Route;
import entities.Solution;
import solutions.MinDistances;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Distance {
    public void minimumDistance(){
        Properties properties = loadProperties();

        String url = properties.getProperty("url");

        try(Connection connection = DriverManager.getConnection(url, properties)){
            LocationDao locationDao = new LocationDao(connection);
            ProblemDao problemDao = new ProblemDao(connection);
            RouteDao routeDao = new RouteDao(connection);
            SolutionDao solutionDao = new SolutionDao(connection);

            int cities = locationDao.allLocations().size();

            List<Problem> problems = problemDao.allProblems();

            int[][] matrix = new int[cities][cities];
            List<Route> routes = routeDao.allRoutes();
            int i, j;
            for(Route r : routes){
                i = r.getFrom_id()-1;
                j = r.getTo_id()-1;
                matrix[i][j] = r.getCost();
            }

            List<Solution> solutions = new ArrayList<>();
            MinDistances distances = new MinDistances();
            int[][] results = distances.distance(cities, matrix);
            for (Problem p : problems){
                int id = p.getId();
                int distance = results[p.getFrom_id()-1][p.getTo_id()-1];
                Solution solution = new Solution();
                solution.setProblem_id(id);
                solution.setCost(distance);
                solutions.add(solution);
            }
            solutionDao.adding(solutions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties loadProperties() {

        Properties props = new Properties();

        try(InputStream input = Distance.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}
