package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.InMemoryRepository;

import java.util.List;

/**
 * Provides connection between Repository and logic for the attributes that Team Manager can perform
 */
public class TeamManagerService {

    private final InMemoryRepository<Person> personRepo;
    private final InMemoryRepository<TeamSponsor> teamSponsorRepo;

    //Might be used for data validation in the Controller
    private final InMemoryRepository<Sponsor> sponsorRepo;
    private final InMemoryRepository<Team> teamRepo;

    public TeamManagerService() {
       this.personRepo = InMemoryRepository.getInstance(Person.class);
       this.sponsorRepo = InMemoryRepository.getInstance(Sponsor.class);
       this.teamRepo = InMemoryRepository.getInstance(Team.class);

       this.teamSponsorRepo = InMemoryRepository.getInstance(TeamSponsor.class);

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

    public void addTeamSponsor(int id,int sponsorId, int teamId, int investmentAmount){
        TeamSponsor teamSponsor = new TeamSponsor(id, sponsorId, teamId, investmentAmount);
        teamSponsorRepo.create(teamSponsor);
    }

    public void removeTeamSponsor(int id){
        teamSponsorRepo.delete(id);
    }

    public List<Person> getAllPersons(){
        return personRepo.getAll();
    }

}
