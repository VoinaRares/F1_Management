package main.com.consoleapp.repository;

import main.com.consoleapp.model.Sponsor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SponsorDBRepository extends DBRepository<Sponsor> {
    public SponsorDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Sponsor sponsor) {
         String sql = "INSERT INTO sponsors (id, sponsorName, investment_Amount, country) VALUES (?,?,?,?)";
         try(PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, sponsor.getId());
             statement.setString(2, sponsor.getSponsorName());
             statement.setDouble(3, sponsor.getInvestmentAmount());
             statement.setString(4, sponsor.getCountry());
             statement.execute();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public Sponsor read(int id) {
        String sql = "SELECT * FROM sponsors WHERE id = ?";
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
    public void update(Sponsor sponsor) {
        String sql = "UPDATE SPONSORS SET sponsorName = ?, investment_Amount = ?, country = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sponsor.getSponsorName());
            statement.setDouble(2, sponsor.getInvestmentAmount());
            statement.setString(3, sponsor.getCountry());
            statement.setInt(4, sponsor.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM sponsors WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sponsor> getAll() {
        String sql = "SELECT * FROM sponsors";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Sponsor> sponsors = new ArrayList<>();
            while(resultSet.next()) {
                sponsors.add(extractFromResultSet(resultSet));
            }
            return sponsors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Sponsor extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Sponsor(
                resultSet.getInt("id"),
                resultSet.getString("sponsorName"),
                resultSet.getInt("investment_Amount"),
                resultSet.getString("country")
        );
    }
}
