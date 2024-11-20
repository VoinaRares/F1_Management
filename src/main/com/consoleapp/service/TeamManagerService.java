package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.IRepository;
import main.com.consoleapp.repository.InFileRepository;
import main.com.consoleapp.repository.InMemoryRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides connection between Repository and logic for the attributes that Team Manager can perform
 */
public class TeamManagerService {

    private final IRepository<Person> personRepo;
    private final IRepository<TeamSponsor> teamSponsorRepo;

    //Might be used for data validation in the Controller
    private final IRepository<Sponsor> sponsorRepo;
    private final IRepository<Team> teamRepo;

    public TeamManagerService() {
       this.personRepo = InFileRepository.getInstance(Person.class, "personRepo.txt");
       this.sponsorRepo = InFileRepository.getInstance(Sponsor.class, "sponsorRepo.txt");
       this.teamRepo = InFileRepository.getInstance(Team.class, "teamRepo.txt");

       this.teamSponsorRepo = InFileRepository.getInstance(TeamSponsor.class, "teamSponsorRepo.txt");

    }

    /**
     * Creates a new F1 Admin with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){
        F1Admin person = new F1Admin(id, name, age, experience, salary, userName, password );
        personRepo.create(person);
        return true;
    }


    /**
     * Creates a new Engineer with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addEngineer(int id, int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){

        Engineer person = new Engineer(id, name, age, experience, salary,
                specialty, yearsWithCurrentTeam, TeamId, userName, password );
        personRepo.create(person);
        return true;
    }

    /**
     * Creates a new Driver with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addDriver(int id, int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){

        Driver person = new Driver(id, name, age, experience, salary, driverNumber, teamId, userName, password );
        personRepo.create(person);
        return true;
    }


    /**
     * Creates a new Team Manager with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addTeamManager(int id, int age, int experience, String name,
                                  float salary, int teamId, String userName, String password){

        TeamManager person = new TeamManager(id, name, age, experience, salary, teamId, userName, password );
        personRepo.create(person);
        return true;
    }

    /**
     * Deletes an Entity from the repository
     * @param id of the Entity to be deleted
     */
    public void removePerson(int id){
        personRepo.delete(id);
    }


    /**
     * Creates a new Team Sponsor with the given Parameters and tries to add it to the Repository
     */
    public void addTeamSponsor(int id,int sponsorId, int teamId, int investmentAmount){
        TeamSponsor teamSponsor = new TeamSponsor(id, teamId, sponsorId, investmentAmount);
        teamSponsorRepo.create(teamSponsor);
    }

    /**
     * Deletes a Team Sponsor
     * @param id of the deleted Team Sponsor
     */
    public void removeTeamSponsor(int id){
        teamSponsorRepo.delete(id);
    }

    /**
     * @return A List of all Entities in the Repository
     */
    public List<Person> getAllPersons(){
        return personRepo.getAll();
    }

    public List<Engineer> getAllEngineers(){
        List<Person> persons = personRepo.getAll();
        List<Engineer> engineers = new ArrayList<>();

        for(Person person : persons ){
            if(person instanceof Engineer){
                engineers.add((Engineer) person);
            }
        }
        return engineers;
    }

    public List<Driver> getAllDrivers(){
        List<Person> persons = personRepo.getAll();
        List<Driver> drivers = new ArrayList<>();
        for(Person person : persons ){
            if(person instanceof Driver){
                drivers.add((Driver) person);
            }
        }
        return drivers;
    }


    public List<Engineer> getEngineersBySpecialty(String specialty){
        List<Person> persons = personRepo.getAll();
        List<Engineer> engineers = new ArrayList<>();
        for(Person person : persons ){
            if(person instanceof Engineer && ((Engineer) person).getSpecialty().equals(specialty)){
                engineers.add((Engineer) person);
            }
        }
        return engineers;
    }
}
