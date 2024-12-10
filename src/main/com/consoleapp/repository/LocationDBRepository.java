package main.com.consoleapp.repository;

import main.com.consoleapp.model.Exceptions.DatabaseException;
import main.com.consoleapp.model.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Same as Race
public class LocationDBRepository extends DBRepository<Location> {
    public LocationDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Location location) {
        String sql = "INSERT INTO locations (id, country, continent, coordinate_x, coordinate_y) VALUES (?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, location.getId());
            statement.setString(2, location.getCountry());
            statement.setString(3, location.getContinent());
            statement.setInt(4, location.getCoordinateX());
            statement.setInt(5, location.getCoordinateY());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public Location read(int id) {
        String sql = "SELECT * FROM locations WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return extractFromResultSet(resultSet);
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public void update(Location location) {
        String sql = "UPDATE LOCATIONS SET country = ?, continent = ?, coordinate_x = ?, coordinate_y = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, location.getCountry());
            statement.setString(2, location.getContinent());
            statement.setInt(3, location.getCoordinateX());
            statement.setInt(4, location.getCoordinateY());
            statement.setInt(5, location.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM locations WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public List<Location> getAll() {
        String sql = "SELECT * FROM locations";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Location> locations = new ArrayList<>();
            while(resultSet.next()) {
                locations.add(extractFromResultSet(resultSet));
            }
            return locations;
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    private static Location extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Location(
                resultSet.getInt("id"),
                resultSet.getString("country"),
                resultSet.getString("continent"),
                resultSet.getInt("coordinate_x"),
                resultSet.getInt("coordinate_y")
        );
    }
}
