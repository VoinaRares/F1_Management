package main.com.consoleapp.repository;

import main.com.consoleapp.model.TeamManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamManagerDBRepository extends DBRepository<TeamManager> {
    public TeamManagerDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(TeamManager obj) {
        String sql = "INSERT INTO Team_Managers (id, name, age, experience, salary, teamId, username, password)" +
                " VALUES (?,?,?,?,?,?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setInt(3, obj.getAge());
            statement.setInt(4, obj.getExperience());
            statement.setFloat(5, obj.getSalary());
            statement.setInt(6, obj.getTeamId());
            statement.setString(7, obj.getUsername());
            statement.setString(8, obj.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TeamManager read(int id) {
        String sql = "SELECT * FROM Team_Managers WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(TeamManager obj) {
        String sql = "UPDATE TEAM_MANAGERS SET name = ?, age = ?, experience = ?, salary = ?, teamId = ?," +
                " username = ?, password = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setInt(2, obj.getAge());
            statement.setInt(3, obj.getExperience());
            statement.setFloat(4, obj.getSalary());
            statement.setInt(5, obj.getTeamId());
            statement.setString(6, obj.getUsername());
            statement.setString(7, obj.getPassword());
            statement.setInt(8, obj.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Team_Managers WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TeamManager> getAll() {
        String sql = "SELECT * FROM Team_Managers";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<TeamManager> teamManagers = new ArrayList<>();
            while(resultSet.next()) {
                teamManagers.add(extractFromResultSet(resultSet));
            }

            return teamManagers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TeamManager extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new TeamManager(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("experience"),
                resultSet.getFloat("salary"),
                resultSet.getInt("teamId"),
                resultSet.getString("username"),
                resultSet.getString("password")

        );
    }
}
