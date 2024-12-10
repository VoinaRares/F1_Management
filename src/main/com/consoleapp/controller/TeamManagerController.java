package main.com.consoleapp.controller;

import main.com.consoleapp.model.*;
import main.com.consoleapp.service.TeamManagerService;

import java.util.ArrayList;
import java.util.List;

public class TeamManagerController {

    private TeamManagerService teamManagerService;

    public TeamManagerController() {
        teamManagerService = new TeamManagerService();
    }

    /**
     * Validates the data entered the user calls add from the service
     * @return true, if added successfully, false otherwise
     */
    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){

        teamManagerService.addF1Admin(id, age, experience, name, salary, userName, password);
        return true;
    }

    /**
     *Validates the data entered the user calls add from the service
     * @return true, if added successfully, false otherwise
     */
    public boolean addEngineer(int id, int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){
        teamManagerService.addEngineer(id, age, experience, name, salary, specialty,
                yearsWithCurrentTeam, TeamId, userName, password);
        return true;
    }

    /**
     * Validates the data entered the user calls add from the service
     * @return true, if added successfully, false otherwise
     */
    public boolean addDriver(int id, int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){
        teamManagerService.addDriver(id, age, experience, name, salary, driverNumber, teamId, userName, password);
        return true;
    }

    /**
     * Validates the data entered by the user calls add from the service
     * @return true, if added successfully, false otherwise
     */
    public boolean addTeamManager(int id, int age, int experience, String name,
                                  float salary, int teamId, String userName, String password){
        teamManagerService.addTeamManager(id, age, experience, name, salary, teamId, userName, password);
        return true;
    }


    /**
     * Adds a team sponsor
     */
    public void addTeamSponsor(int id,int sponsorId, int teamId, int investmentAmount){
        teamManagerService.addTeamSponsor(id, sponsorId, teamId, investmentAmount);
    }

    /**
     * Removes a team sponsor by id
     * @param id of team sponsor
     */
    public void removeTeamSponsor(int id, int teamId){
        teamManagerService.removeTeamSponsor(id, teamId);
    }

    /**
     * Gets all the persons
     * @return all the persons
     */
    public List<Person> getAllPersons(){
        return teamManagerService.getAllPersons();
    }

    /**
     * Sorts the List of Person by Salary
     * @return List of Persons sorted by salary
     */
    public List<TeamMember> getAllSortedBySalary(int teamId){
        return teamManagerService.getAllSortedBySalary(teamId);
    }

    /**
     * Sorts the List of Persons by Age
     * @return List of Persons sorted by their age
     */
    public List<TeamMember> getAllSortedByAge(int teamId){
        return teamManagerService.getAllSortedByAge(teamId);
    }

    /**
     * Filters all Persons to be an instance of Engineer
     * @return List of all Engineers
     */
    public List<Engineer> getAllEngineers(){
        return teamManagerService.getAllEngineers();
    }

    /**
     * Filters all Persons to be an instance of Driver
     * @return List of all Drivers
     */
    public List<Driver> getAllDrivers(){return teamManagerService.getAllDrivers();}

    /**
     * Filters Engineers by their specialty
     * @param specialty filter
     * @return List of Engineers of a certain specialty
     */
    public List<Engineer> getEngineersBySpecialty(String specialty){
        return teamManagerService.getEngineersBySpecialty(specialty);
    }

    public void removeDriver(int id, int teamId){
        teamManagerService.removeDriver(id, teamId);
    }

    public void removeEngineer(int id, int teamId){
        teamManagerService.removeEngineer(id, teamId);
    }

    public List<Sponsor> showTeamSponsors(int teamId) {
        return teamManagerService.showTeamSponsors(teamId);

    }

    public List<Sponsor> showSponsors() {
        return null;
    }
}
