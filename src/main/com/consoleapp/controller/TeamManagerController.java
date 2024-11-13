package main.com.consoleapp.controller;

import main.com.consoleapp.model.Person;
import main.com.consoleapp.service.TeamManagerService;

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
     * Validates the data entered and calls remove from the service
     * @param id of the object to be removed
     */
    public void removePerson(int id){
        teamManagerService.removePerson(id);
    }

    public void addTeamSponsor(int id,int sponsorId, int teamId, int investmentAmount){
        teamManagerService.addTeamSponsor(id, sponsorId, teamId, investmentAmount);
    }

    public void removeTeamSponsor(int id){
        teamManagerService.removeTeamSponsor(id);
    }

    public List<Person> getAllPersons(){
        return teamManagerService.getAllPersons();
    }

}
