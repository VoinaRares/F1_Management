package main.com.consoleapp.repository;

import main.com.consoleapp.model.Driver;
import main.com.consoleapp.model.Exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDBRepository extends DBRepository<Driver> {
    public DriverDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Driver obj){
        String sql = "INSERT INTO DRIVERS (ID, NAME, age, experience, salary, number, teamId, username, password)" +
                " VALUES(?, ?, ?, ?, ?, ?, ? , ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setInt(3, obj.getAge());
            statement.setInt(4, obj.getExperience());
            statement.setDouble(5, obj.getSalary());
            statement.setInt(6, obj.getNumber());
            statement.setInt(7, obj.getTeamId());
            statement.setString(8, obj.getUsername());
            statement.setString(9, obj.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public Driver read(int id){

        String sql = "SELECT * FROM DRIVERS WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public void update(Driver obj){
        String sql  = "UPDATE DRIVERS SET name = ?, age = ?, experience = ?, salary = ?," +
                "number = ?, teamId = ?, username = ?, password = ? WHERE ID = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getAge());
            statement.setInt(3, obj.getExperience());
            statement.setDouble(4, obj.getSalary());
            statement.setInt(5, obj.getNumber());
            statement.setInt(6, obj.getTeamId());
            statement.setString(7, obj.getUsername());
            statement.setString(8, obj.getPassword());
            statement.setInt(9, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM DRIVERS WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    @Override
    public List<Driver> getAll(){
        String sql = "SELECT * FROM DRIVERS";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Driver> drivers = new ArrayList<>();
            while(resultSet.next()){
                drivers.add(extractFromResultSet(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }

    private static Driver extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Driver(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("experience"),
                resultSet.getFloat("salary"),
                resultSet.getInt("number"),
                resultSet.getInt("teamid"),
                resultSet.getString("username"),
                resultSet.getString("password"));
    }
}
