package main.com.consoleapp.controller;

import main.com.consoleapp.service.TeamManagerService;

public class TeamManagerController {

    private TeamManagerService teamManagerService;

    public TeamManagerController() {
        teamManagerService = new TeamManagerService();
    }

    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){
        teamManagerService.addF1Admin(id, age, experience, name, salary, userName, password);
        return true;
    }

    public boolean addEngineer(int id, int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){
        teamManagerService.addEngineer(id, age, experience, name, salary, specialty,
                yearsWithCurrentTeam, TeamId, userName, password);
        return true;
    }

    public boolean addDriver(int id, int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){
        teamManagerService.addDriver(id, age, experience, name, salary, driverNumber, teamId, userName, password);
        return true;
    }

    public boolean addTeamManager(int id, int age, int experience, String name,
                                  float salary, int teamId, String userName, String password){
        teamManagerService.addTeamManager(id, age, experience, name, salary, teamId, userName, password);
        return true;
    }

    public void removePerson(int id){
        teamManagerService.removePerson(id);
    }


}
