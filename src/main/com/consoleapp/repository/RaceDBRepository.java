package main.com.consoleapp.repository;


import main.com.consoleapp.model.Date;
import main.com.consoleapp.model.Race;
import main.com.consoleapp.model.TeamSponsor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaceDBRepository extends DBRepository<Race>{
    private static LocationDBRepository locationDB;
    private static TeamSponsorDBRepository teamSponsorDB;

    public RaceDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        locationDB = new LocationDBRepository(dbUrl, dbUser, dbPassword);
        teamSponsorDB = new TeamSponsorDBRepository(dbUrl, dbUser, dbPassword);
    }



    //SHOULD ALSO ADD A LOCATION TO THIS
    @Override
    public void create(Race race) {
        String sql = "INSERT INTO RACES(id, year, month, day, location_id) VALUES(?,?,?,?,?)";
        locationDB.create(race.getLocation());
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, race.getId());
            statement.setInt(2, race.getDate().getYear());
            statement.setInt(3, race.getDate().getMonth());
            statement.setInt(4, race.getDate().getDay());
            statement.setInt(5, race.getLocation().getId());

            for(TeamSponsor teamSponsor : race.getTeamSponsors()) {
                sql = "INSERT INTO race_team_sponsor(team_sponsor_id, race_id) VALUES(?,?)";
                try(PreparedStatement statement2 = connection.prepareStatement(sql)) {
                    statement2.setInt(1, teamSponsor.getId());
                    statement2.setInt(2, race.getId());
                    statement2.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Race read(int id) {
        String sql = "SELECT * FROM RACES WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return extractFromResultSet(rs);
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Race race) {
        String sql = "UPDATE RACES SET year = ?, month = ?, day = ?, location_id = ? WHERE id = ?";
        locationDB.update(race.getLocation());
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, race.getDate().getYear());
            statement.setInt(2, race.getDate().getMonth());
            statement.setInt(3, race.getDate().getDay());
            statement.setInt(4, race.getLocation().getId());
            statement.setInt(5, race.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //WILL HAVE TO ADD CASCADE TO THE DELETE IN THE DATABASE
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM RACES WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Race> getAll() {
        String sql = "SELECT * FROM RACES";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<Race> races = new ArrayList<>();
            while(rs.next()) {
                races.add(extractFromResultSet(rs));
            }

            return races;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Integer> getAllTeamSponsorIds(int raceId) {
        List<Integer> teamSponsorIds = new ArrayList<>();
        String sql = "SELECT team_sponsor_id FROM race_team_sponsor WHERE race_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, raceId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                teamSponsorIds.add(rs.getInt("team_sponsor_id"));
            }
            return teamSponsorIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private  List<TeamSponsor> extractTeamSponsorsFromResultSet(ResultSet rs) throws SQLException {
        List<Integer> teamSponsorIds = getAllTeamSponsorIds(rs.getInt("race_id"));
        List<TeamSponsor> teamSponsors = new ArrayList<>();

        for(Integer teamSponsorId : teamSponsorIds) {
            teamSponsors.add(teamSponsorDB.read(teamSponsorId));
        }

        return teamSponsors;
    }

    private  Race extractFromResultSet(ResultSet rs) throws SQLException {
        return new Race(
                rs.getInt("id"),
                locationDB.read(rs.getInt("location_id")),
                new Date(
                        rs.getInt("year"),
                        rs.getInt("month"),
                        rs.getInt("day")
                ),
                extractTeamSponsorsFromResultSet(rs)
        );
    }
}
