package main.com.consoleapp.controller;

import main.com.consoleapp.model.*;
import main.com.consoleapp.service.F1AdminService;

import java.util.List;
import java.util.Map;

public class F1AdminController {

    F1AdminService f1AdminService = new F1AdminService();

    /**
     * Performs validation of data and calls addRace from the Admin Service
     * @param country country of the race
     * @param continent continent where that country is located
     * @param coordinateX the X coordinate of the circuit
     * @param coordinateY the Y coordinate of the circuit
     * @return true, if service added the race successfully, false otherwise
     */
    public boolean addRace(String country, String continent, int coordinateX, int coordinateY)
    {
        //validation
        f1AdminService.addRace(country, continent, coordinateX, coordinateY);
        return true;
    }

    /**
     * Generates the calendar of the F1 season
     * @param start_country starting country
     * @param end_country final country race
     * @param day starting day
     * @param month starting month
     * @param year starting year
     * @return the list of all races in order
     */
    public List<Race> generateCalendar(String start_country, String end_country, int day, int month, int year)
    {
        //validation
        return f1AdminService.generateCalendar(start_country,end_country,day,month,year);
    }


    /**
     * gets List of all the Sponsor budget for every Race
     * @return List of all the Sponsor budget for every Race
     */
    public List<TeamSponsorRace> showSponsorMoneyPerRace(){return f1AdminService.showSponsorMoneyPerRace();}


    /**
     * adds a Sponsor
     * @param name SponsorName
     * @param investmentAmount SponsorInvestmentAmount
     * @param country SponsorCountry
     */
    public void addSponsor(String name, int investmentAmount, String country){f1AdminService.addSponsor(name,investmentAmount,country);}


    /**
     * gets List of all the Sponsors
     * @return List of all Sponsors
     */
    public List<Sponsor> getAllSponsors() {
        return f1AdminService.getAllSponsors();
    }


    /**
     * gets List of all Races
     * @return List of all Races
     */
    public List<Race> getAllRaces() {
        return f1AdminService.getAllRaces();
    }

    /**
     * gets List of all TeamSponsors
     * @return List of all TeamSponsors
     */
    public List<TeamSponsor> getAllTeamSponsors() {
        return f1AdminService.getAllTeamSponsors();
    }


    /**
     * adds a new Team to the List
     * @param teamName Team.TeamName
     * @param budget Team.Budget
     */
    public void addTeam(String teamName, int budget) {
        f1AdminService.addTeam(teamName,budget);
    }


    /**
     * gets List of all Teams
     * @return list of all Teams
     */
    public List<Team> getAllTeams() {
        return f1AdminService.getAllTeams();
    }

    /**
     * gets Team by id
     * @param id id of Team
     * @return Team if found, else null
     */
    public Team getTeamById(int id){
        return f1AdminService.getTeam(id);
    }


    /**
     * gets TeamSponsor by id
     * @param id id of TeamSponsor
     * @return TeamSponsor if found, else null
     */
    public TeamSponsor getTeamSponsorById(int id){
        return f1AdminService.getTeamSponsor(id);
    }


    /**
     * gets Sponsor by id
     * @param id id of Sponsor
     * @return Sponsor if found, else null
     */
    public Sponsor getSponsorById(int id){
        return f1AdminService.getSponsor(id);
    }


    /**
     * gets Race by id
     * @param id id of Race
     * @return Race if found, else null
     */
    public Race getRaceById(int id){
        return f1AdminService.getRace(id);
    }


    public boolean addTeamManager(String name, int age, int experience, float salary, int teamId,
                                   String username, String password){
        return f1AdminService.addTeamManager(name,age,experience,salary,teamId,username,password);
    }

    public List<TeamManager> showTeamManagers() {
        return f1AdminService.showTeamManagers();
    }

    public boolean deleteTeamManager(int id) {
        return f1AdminService.deleteTeamManager(id);
    }
}
