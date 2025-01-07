package main.com.consoleapp.controller;

import main.com.consoleapp.model.*;
import main.com.consoleapp.model.Exceptions.BusinessLogicException;
import main.com.consoleapp.service.TeamManagerService;

import java.util.ArrayList;
import java.util.List;

public class TeamManagerController {

    private TeamManagerService teamManagerService;

    public TeamManagerController(int repositoryChoice) {
        teamManagerService = new TeamManagerService(repositoryChoice);
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
    public boolean addEngineer(int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){
        teamManagerService.addEngineer(age, experience, name, salary, specialty,
                yearsWithCurrentTeam, TeamId, userName, password);
        return true;
    }

    /**
     * Validates the data entered the user calls add from the service
     * @return true, if added successfully, false otherwise
     */
    public boolean addDriver(int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){
        if(age < 18){
            throw new BusinessLogicException("Driver does not meet the required age");
        }
        teamManagerService.addDriver(age, experience, name, salary, driverNumber, teamId, userName, password);
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
    public void addTeamSponsor(String sponsorName, int teamId, int investmentAmount){
        teamManagerService.addTeamSponsor(sponsorName, teamId, investmentAmount);
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

    /**
     * Deletes a driver
     * @param id of the driver to be deleted
     * @param teamId of the currently logged in Team Manager
     */
    public void removeDriver(int id, int teamId){
        teamManagerService.removeDriver(id, teamId);
    }

    /**
     * Deletes an engineer
     * @param id of the engineer to be deleted
     * @param teamId of the currently logged in Team Manager
     */
    public void removeEngineer(int id, int teamId){
        teamManagerService.removeEngineer(id, teamId);
    }

    /**
     * Gets all Sponsors of a certain team
     * @param teamId of the team to get all the Sponsors from
     * @return a List of Sponsors
     */
    public List<Sponsor> showTeamSponsors(int teamId) {
        return teamManagerService.showTeamSponsors(teamId);

    }

    /**
     * Gets all possible Sponsors
     * @return a list of Sponsors
     */
    public List<Sponsor> showSponsors() {
        return teamManagerService.showSponsors();
    }

    /**
     * Gets all Team Sponsors for a team
     * @param teamId of the team
     * @return a list of Team Sponsors
     */
    public List<TeamSponsor> showTeamSponsorsId(int teamId) {
        return teamManagerService.showTeamSponsorsId(teamId);
    }

    /**
     * Checks if the username is unique
     * @param username that needs to be checked
     * @return true, if username is unique, false otherwise
     */
    public boolean usernameIsUnique(String username) {
        return teamManagerService.usernameISUnique(username);
    }
}
