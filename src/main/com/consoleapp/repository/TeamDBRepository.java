package main.com.consoleapp.repository;

import main.com.consoleapp.model.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDBRepository extends DBRepository<Team> {
    public TeamDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Team team) {
        String sql = "INSERT INTO teams (id, team_name, budget) VALUES (?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, team.getId());
            statement.setString(2, team.getTeamName());
            statement.setDouble(3, team.getBudget());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Team read(int id) {
        String sql = "SELECT * FROM teams WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return extractFromResultSet(resultSet);
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Team team) {
        String sql = "UPDATE teams SET team_name = ?, budget = ? WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, team.getTeamName());
            statement.setDouble(2, team.getBudget());
            statement.setInt(3, team.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Team> getAll() {
        String sql = "SELECT * FROM teams";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Team> teams = new ArrayList<>();
            while(resultSet.next()) {
                teams.add(extractFromResultSet(resultSet));
            }

            return teams;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Team extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Team(
                resultSet.getInt("id"),
                resultSet.getString("team_name"),
                resultSet.getInt("budget")
        );
    }
}
