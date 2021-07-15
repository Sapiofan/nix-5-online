package dao;

import entities.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {
    private final Connection connection;

    private final String ALL = "SELECT * FROM routes";
    private final String BYID = "SELECT * FROM routes WHERE id = ?";

    public RouteDao(Connection connection) {
        this.connection = connection;
    }

    public List<Route> allRoutes(){
        List<Route> routes = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL);
            while (resultSet.next()){
                Route route = new Route();
                route.setId(resultSet.getInt(1));
                route.setFrom_id(resultSet.getInt(2));
                route.setTo_id(resultSet.getInt(3));
                route.setCost(resultSet.getInt(4));
                routes.add(route);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return routes;
    }

    public Route getId(int id){
        Route route = new Route();
        try(PreparedStatement statement = connection.prepareStatement(BYID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            route.setId(resultSet.getInt(1));
            route.setFrom_id(resultSet.getInt(2));
            route.setTo_id(resultSet.getInt(3));
            route.setCost(resultSet.getInt(4));
            return route;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
