package com.consoleapp;

import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.model.Exceptions.BusinessLogicException;
import main.com.consoleapp.model.Exceptions.DatabaseException;
import main.com.consoleapp.repository.*;
import main.com.consoleapp.model.*;
import main.com.consoleapp.service.F1AdminService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private int repositoryChoice = 3;

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

    @Test
    public void Test1Calendar()
    {
        //assertThrows()

        RaceDBRepository raceDBRepository = new RaceDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

        LocationDBRepository locationDBRepository = new LocationDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

        F1AdminService f1AdminService = new F1AdminService(repositoryChoice);

        List<Race> races = raceDBRepository.getAll();
        List<Location> locations = locationDBRepository.getAll();

        for(Race race: races){
            raceDBRepository.delete(race.getId());
        }
        for(Location location: locations){
            locationDBRepository.delete(location.getId());
        }

        Race race1 = new Race(105, new Location(105, "Italy", "Europe", 500, 400));
        Race race2 = new Race(106, new Location(106, "France", "Europe", 400, 500));
        Race race3 = new Race(107, new Location(107, "UAE", "Asia", 1000, 100));
        Race race4 = new Race(108, new Location(108, "Japan", "Asia", 3000, 500));
        Race race5 = new Race(109, new Location(109, "Portugal", "Europe", 200, 200));
        raceDBRepository.create(race1);
        raceDBRepository.create(race2);
        raceDBRepository.create(race3);
        raceDBRepository.create(race4);
        raceDBRepository.create(race5);
        List<Race> calendar= new ArrayList<>();
        calendar= f1AdminService.generateCalendar("Italy","Japan", 15, 5, 2024);
        assertEquals("Italy",calendar.getFirst().getLocation().getCountry());
        assertEquals(15,calendar.getFirst().getDate().getDay());
        assertEquals(5,calendar.getFirst().getDate().getMonth());
        assertEquals(2024,calendar.getFirst().getDate().getYear());
        assertEquals("Portugal",calendar.get(2).getLocation().getCountry());
        assertEquals(29,calendar.get(2).getDate().getDay());
        assertEquals(5,calendar.get(2).getDate().getMonth());
        assertEquals(2024,calendar.get(2).getDate().getYear());
        assertEquals("Japan",calendar.getLast().getLocation().getCountry());
        assertEquals(20,calendar.getLast().getDate().getDay());
        assertEquals(6,calendar.getLast().getDate().getMonth());
        assertEquals(2024,calendar.getLast().getDate().getYear());

        raceDBRepository.delete(105);
        raceDBRepository.delete(106);
        raceDBRepository.delete(107);
        raceDBRepository.delete(108);
        raceDBRepository.delete(109);

        locationDBRepository.delete(105);
        locationDBRepository.delete(106);
        locationDBRepository.delete(107);
        locationDBRepository.delete(108);
        locationDBRepository.delete(109);

        for(Race race: races){
            raceDBRepository.create(race);
        }
    }

    @Test
    public void Test2Calendar()
    {
        RaceDBRepository raceDBRepository = new RaceDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        LocationDBRepository locationDBRepository = new LocationDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

        F1AdminService f1AdminService = new F1AdminService(repositoryChoice);

        Race race1 = new Race(1005, new Location(1005, "Italy", "Europe", 500, 400));
        Race race2 = new Race(1006, new Location(1006, "France", "Europe", 400, 500));
        Race race3 = new Race(1007, new Location(1007, "UAE", "Asia", 1000, 100));
        Race race4 = new Race(1008, new Location(1008, "Japan", "Asia", 3000, 500));
        Race race5 = new Race(1009, new Location(1009, "Portugal", "Europe", 200, 200));
        raceDBRepository.create(race1);
        raceDBRepository.create(race2);
        raceDBRepository.create(race3);
        raceDBRepository.create(race4);
        raceDBRepository.create(race5);

        assertThrows(BusinessLogicException.class, () -> {f1AdminService.generateCalendar(
                "Romania","Japan", 15, 5, 2024);});
        assertThrows(BusinessLogicException.class, () -> {f1AdminService.generateCalendar(
                "Italy", "Italy", 15, 5, 2024);});

        raceDBRepository.delete(1005);
        raceDBRepository.delete(1006);
        raceDBRepository.delete(1007);
        raceDBRepository.delete(1008);
        raceDBRepository.delete(1009);

        locationDBRepository.delete(1005);
        locationDBRepository.delete(1006);
        locationDBRepository.delete(1007);
        locationDBRepository.delete(1008);
        locationDBRepository.delete(1009);

    }

    @Test
    public void DriverBusinessLogic(){
        Driver d1 = new Driver(89, "Test", 16, 0, 12341, 10,
                1, "213", "y");
        TeamManagerController teamManagerController = new TeamManagerController(repositoryChoice);
        assertThrows(BusinessLogicException.class, () -> {teamManagerController.addDriver(89,
                16, 0, "Test", 12341, 10,
                1, "213", "y");});

    }

}
