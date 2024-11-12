package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.InMemoryRepository;

public class TeamManagerService {

    private final InMemoryRepository<Person> personRepo;

    public TeamManagerService() {
        personRepo = new InMemoryRepository<>();
    }

    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){
        F1Admin person = new F1Admin(id, name, age, experience, salary, userName, password );
        personRepo.create(person);
        return true;
    }

    public boolean addEngineer(int id, int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){

        Engineer person = new Engineer(id, name, age, experience, salary,
                specialty, yearsWithCurrentTeam, TeamId, userName, password );
        personRepo.create(person);
        return true;
    }

    public boolean addDriver(int id, int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){

        Driver person = new Driver(id, name, age, experience, salary, driverNumber, teamId, userName, password );
        personRepo.create(person);
        return true;
    }

    public boolean addTeamManager(int id, int age, int experience, String name,
                                  float salary, int teamId, String userName, String password){

        TeamManager person = new TeamManager(id, name, age, experience, salary, teamId, userName, password );
        personRepo.create(person);
        return true;
    }

    public void removePerson(int id){
        personRepo.delete(id);
    }

}
