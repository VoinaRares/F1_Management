package main.com.consoleapp.repository;

import main.com.consoleapp.model.TeamSponsor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamSponsorDBRepository extends DBRepository<TeamSponsor> {
    public TeamSponsorDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(TeamSponsor obj) {
        String sql = "INSERT INTO Team_Sponsors(id, teamId, sponsorId, investment_Amount) VALUES(?,?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getTeamId());
            statement.setInt(3, obj.getSponsorId());
            statement.setDouble(4, obj.getInvestmentAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TeamSponsor read(int id) {
        String sql = "SELECT * FROM Team_Sponsors WHERE id = ?";
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
    public void update(TeamSponsor obj) {
        String sql = "UPDATE TEAM_SPONSORS SET teamId = ?, sponsorId = ?, investment_Amount = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getTeamId());
            statement.setInt(2, obj.getSponsorId());
            statement.setDouble(3, obj.getInvestmentAmount());
            statement.setInt(4, obj.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Team_Sponsors WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TeamSponsor> getAll() {
        String sql = "SELECT * FROM Team_Sponsors";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<TeamSponsor> teamSponsors = new ArrayList<>();

            while(resultSet.next()) {
                teamSponsors.add(extractFromResultSet(resultSet));
            }

            return teamSponsors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static TeamSponsor extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new TeamSponsor(
                resultSet.getInt("id"),
                resultSet.getInt("teamId"),
                resultSet.getInt("sponsorId"),
                resultSet.getInt("investment_Amount")
        );
    }
}
