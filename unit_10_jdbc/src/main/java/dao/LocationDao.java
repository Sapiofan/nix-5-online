package dao;

import entities.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDao {
    private final Connection connection;

    private final String ALL = "SELECT * FROM locations";
    private final String BYID = "SELECT * FROM locations WHERE id = ?";

    public LocationDao(Connection connection) {
        this.connection = connection;
    }

    public List<Location> allLocations(){
        List<Location> locations = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL);
            while (resultSet.next()){
                Location location = new Location();
                location.setId(resultSet.getInt(1));
                location.setName(resultSet.getString(2));
                locations.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

    public Location getId(int id){
        Location location = new Location();
        try(PreparedStatement statement = connection.prepareStatement(BYID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            location.setId(resultSet.getInt(1));
            location.setName(resultSet.getString(2));
            return location;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
