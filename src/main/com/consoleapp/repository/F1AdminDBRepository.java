package main.com.consoleapp.repository;

import main.com.consoleapp.model.F1Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class F1AdminDBRepository extends DBRepository<F1Admin> {
    public F1AdminDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(F1Admin obj) {
        String sql = "INSERT INTO f1admin (id, name, age, experience, salary, username, password)" +
                " VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setInt(3, obj.getAge());
            statement.setInt(4, obj.getExperience());
            statement.setFloat(5, obj.getSalary());
            statement.setString(6, obj.getUsername());
            statement.setString(7, obj.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public F1Admin read(int id) {
        String sql = "SELECT * FROM f1admin WHERE id = ?";
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
    public void update(F1Admin obj) {
        String sql = "UPDATE f1admins SET name = ?, age = ?, experience = ?, salary = ?, username = ?," +
                " password = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getAge());
            statement.setInt(3, obj.getExperience());
            statement.setFloat(4, obj.getSalary());
            statement.setString(5, obj.getUsername());
            statement.setString(6, obj.getPassword());
            statement.setInt(7, obj.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM f1admins WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<F1Admin> getAll() {
        String sql = "SELECT * FROM f1admins";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<F1Admin> list = new ArrayList<>();

            while(resultSet.next()) {
                list.add(extractFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private F1Admin extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new F1Admin(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("experience"),
                resultSet.getFloat("salary"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }
}
