package main.com.consoleapp.repository;

import main.com.consoleapp.model.Driver;
import main.com.consoleapp.model.Engineer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngineerDBRepository extends DBRepository<Engineer> {
    public EngineerDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Engineer obj) {
        String sql = "INSERT INTO ENGINEERS(id, name, age, experience, salary, specialty," +
                " yearsWithCurrentTeam, teamId, username, password) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, obj.getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Engineer read(int id) {
        String sql = "SELECT * FROM ENGINEERS WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Engineer obj) {
        String sql = "UPDATE ENGINEERS SET name = ?, age = ?, experience = ?, salary = ?,"
                + " specialty = ?, yearsWithCurrentTeam = ?, teamId = ?, username = ?, password = ? WHERE ID = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getAge());
            statement.setInt(3, obj.getExperience());
            statement.setDouble(4, obj.getSalary());
            statement.setString(5, obj.getSpecialty());
            statement.setInt(6, obj.getYearsWithCurrentTeam());
            statement.setInt(7, obj.getTeamId());
            statement.setString(8, obj.getUsername());
            statement.setString(9, obj.getPassword());
            statement.setInt(10, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id){
        String sql = "DELETE FROM ENGINEERS WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Engineer> getAll(){
        String sql = "SELECT * FROM ENGINEERS";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Engineer> engineers = new ArrayList<>();
            while(resultSet.next()){
                engineers.add(extractFromResultSet(resultSet));
            }
            return engineers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Engineer extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Engineer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("experience"),
                resultSet.getFloat("salary"),
                resultSet.getString("specialty"),
                resultSet.getInt("yearsWithCurrentTeam"),
                resultSet.getInt("teamId"),
                resultSet.getString("username"),
                resultSet.getString("password")
                );
    }
}
