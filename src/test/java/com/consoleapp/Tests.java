package com.consoleapp;

import main.com.consoleapp.model.Exceptions.DatabaseException;
import main.com.consoleapp.repository.*;
import main.com.consoleapp.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void CRUDDriver() {
        DriverDBRepository driverRepository = new DriverDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Driver driver = new Driver(15, "Test", 22, 3, 12341, 14,
                0, "4", "y");
        driverRepository.create(driver);
        assertEquals("Test", driverRepository.read(15).getName());
        assertEquals("y", driverRepository.read(15).getPassword());
        driverRepository.update(new Driver(15, "Update", 22, 3, 12341, 14,
                0, "4", "y"));
        assertEquals("Update", driverRepository.read(15).getName());
        assertEquals("y", driverRepository.read(15).getPassword());
        driverRepository.delete(15);
        assertNull(driverRepository.read(15));
    }

    @Test
    public void CRUDEngineer(){
        EngineerDBRepository engineerDBRepository = new EngineerDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Engineer engineer = new Engineer(15,"Test" ,22, 3, 12314,
                "Aerodynamics", 3, 0, "-1", "y");

        engineerDBRepository.create(engineer);
        assertEquals("Test", engineerDBRepository.read(15).getName());
        assertEquals("y", engineerDBRepository.read(15).getPassword());
        engineerDBRepository.update(new Engineer(15,"Update" ,22, 3, 12314,
                "Aerodynamics", 3, 0, "-1", "y"));
        assertEquals("Update", engineerDBRepository.read(15).getName());
        assertEquals("y", engineerDBRepository.read(15).getPassword());
        engineerDBRepository.delete(15);
        assertNull(engineerDBRepository.read(15));
    }

    @Test
    public void CRUDF1Admin(){
        F1AdminDBRepository f1AdminDBRepository = new F1AdminDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        F1Admin f1Admin = new F1Admin(15, "Test", 22, 3, 1234,
                "14", "y");
        f1AdminDBRepository.create(f1Admin);
        assertEquals("Test", f1AdminDBRepository.read(15).getName());
        assertEquals("y", f1AdminDBRepository.read(15).getPassword());
        f1AdminDBRepository.update(new F1Admin(15, "Update", 22, 3, 1234,
                "14", "y"));
        assertEquals("Update", f1AdminDBRepository.read(15).getName());
        assertEquals("y", f1AdminDBRepository.read(15).getPassword());
        f1AdminDBRepository.delete(15);
        assertNull(f1AdminDBRepository.read(15));
    }

    @Test
    public void CRUDLocation(){
        LocationDBRepository locationDBRepository = new LocationDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Location location = new Location(15, "Test", "Test", 0, 0);
        locationDBRepository.create(location);
        assertEquals("Test", locationDBRepository.read(15).getCountry());
        assertEquals("Test", locationDBRepository.read(15).getContinent());
        assertEquals(0, locationDBRepository.read(15).getCoordinateX());
        locationDBRepository.update(new Location(15, "Update", "Test", 20, 20));
        assertEquals("Update", locationDBRepository.read(15).getCountry());
        assertEquals("Test", locationDBRepository.read(15).getContinent());
        assertEquals(20, locationDBRepository.read(15).getCoordinateX());
        locationDBRepository.delete(15);
        assertNull(locationDBRepository.read(15));
    }

    @Test
    public void CRUDRaces(){
        RaceDBRepository raceDBRepository = new RaceDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        LocationDBRepository locationDBRepository = new LocationDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Race race = new Race(15, new Location(15, "Test", "Test", 0, 0));
        raceDBRepository.create(race);
        assertEquals(15, raceDBRepository.read(15).getId());
        assertEquals(15, raceDBRepository.read(15).getLocation().getId());

        raceDBRepository.update(new Race(15, new Location(15, "Update", "Test", 20, 20)));
        assertEquals("Update", raceDBRepository.read(15).getLocation().getCountry());
        raceDBRepository.delete(15);
        assertNull(raceDBRepository.read(15));
        locationDBRepository.delete(15);
    }

    @Test
    public void CRUDSponsor(){
        SponsorDBRepository sponsorDBRepository = new SponsorDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Sponsor sponsor = new Sponsor(15, "Test", 123, "Test");
        sponsorDBRepository.create(sponsor);
        assertEquals(123, sponsorDBRepository.read(15).getInvestmentAmount());
        assertEquals("Test", sponsorDBRepository.read(15).getCountry());
        assertEquals("Test", sponsorDBRepository.read(15).getSponsorName());
        sponsorDBRepository.update(new Sponsor(15, "Update", 123, "Test"));
        assertEquals("Update", sponsorDBRepository.read(15).getSponsorName());
        sponsorDBRepository.delete(15);
        assertNull(sponsorDBRepository.read(15));
    }

    @Test
    public void CRUDTeamManager(){
        TeamManagerDBRepository teamManagerDBRepository = new TeamManagerDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

        TeamManager teamManager = new TeamManager(15, "Test", 15, 3,
                2123, 0, "15", "y");
        teamManagerDBRepository.create(teamManager);
        assertEquals("Test", teamManagerDBRepository.read(15).getName());
        assertEquals("y", teamManagerDBRepository.read(15).getPassword());
        teamManagerDBRepository.update(new TeamManager(15, "Update", 15, 3, 2123,
                0, "15", "y"));
        assertEquals("Update", teamManagerDBRepository.read(15).getName());
        assertEquals("y", teamManagerDBRepository.read(15).getPassword());
        teamManagerDBRepository.delete(15);
        assertNull(teamManagerDBRepository.read(15));
    }

    @Test
    public void CRUDTeamSponsor(){
        TeamSponsorDBRepository teamSponsorDBRepository = new TeamSponsorDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        TeamSponsor teamSponsor = new TeamSponsor(15, 0, 0, 15);
        teamSponsorDBRepository.create(teamSponsor);
        assertEquals(15, teamSponsorDBRepository.read(15).getId());
        assertEquals(15, teamSponsorDBRepository.read(15).getInvestmentAmount());
        teamSponsorDBRepository.update(new TeamSponsor(15, 0, 0, 20));
        assertEquals(20, teamSponsorDBRepository.read(15).getInvestmentAmount());
        teamSponsorDBRepository.delete(15);
        assertNull(teamSponsorDBRepository.read(15));
    }

    @Test
    public void CRUDTeam(){
        TeamDBRepository teamDBRepository = new TeamDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        Team team = new Team(15, "Test", 123);
        teamDBRepository.create(team);
        assertEquals(123, teamDBRepository.read(15).getBudget());
        assertEquals("Test", teamDBRepository.read(15).getTeamName());
        teamDBRepository.update(new Team(15, "Update", 222));
        assertEquals("Update", teamDBRepository.read(15).getTeamName());
        assertEquals(222, teamDBRepository.read(15).getBudget());
        teamDBRepository.delete(15);
        assertNull(teamDBRepository.read(15));
    }
}
