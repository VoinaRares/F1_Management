package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.IRepository;
import main.com.consoleapp.repository.InFileRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides connection between Repository and logic for the attributes that Team Manager can perform
 */
public class TeamManagerService {

    private final IRepository<Person> personRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Engineer> engineerRepository;
    private final IRepository<TeamSponsor> teamSponsorRepository;;

    //Might be used for data validation in the Controller
    private final IRepository<Sponsor> sponsorRepo;
    private final IRepository<Team> teamRepo;

    public TeamManagerService() {
       this.personRepository = InFileRepository.getInstance(Person.class, "personRepo.txt");
       this.sponsorRepo = InFileRepository.getInstance(Sponsor.class, "sponsorRepo.txt");
       this.teamRepo = InFileRepository.getInstance(Team.class, "teamRepo.txt");
       this.teamSponsorRepository = InFileRepository.getInstance(TeamSponsor.class, "teamSponsorRepo.txt");
       this.driverRepository=InFileRepository.getInstance(Driver.class, "driverRepo.txt");
       this.engineerRepository=InFileRepository.getInstance(Engineer.class, "engineerRepo.txt");
    }

    /**
     * Creates a new F1 Admin with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){
        F1Admin person = new F1Admin(id, name, age, experience, salary, userName, password );
        personRepository.create(person);
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
        personRepository.create(person);
        engineerRepository.create(person);
        return true;
    }

    /**
     * Creates a new Driver with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addDriver(int id, int age, int experience, String name, float salary,
                             int driverNumber, int teamId, String userName, String password){

        Driver person = new Driver(id, name, age, experience, salary, driverNumber, teamId, userName, password );
        personRepository.create(person);
        driverRepository.create(person);
        return true;
    }


    /**
     * Creates a new Team Manager with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addTeamManager(int id, int age, int experience, String name,
                                  float salary, int teamId, String userName, String password){

        TeamManager person = new TeamManager(id, name, age, experience, salary, teamId, userName, password );
        personRepository.create(person);
        return true;
    }

    /**
     * Deletes an Entity from the repository
     * @param id of the Entity to be deleted
     */
    public void removePerson(int id){
        personRepository.delete(id);
    }


    /**
     * Creates a new Team Sponsor with the given Parameters and tries to add it to the Repository
     */
    public void addTeamSponsor(int id,int sponsorId, int teamId, int investmentAmount){
        TeamSponsor teamSponsor = new TeamSponsor(id, teamId, sponsorId, investmentAmount);
        teamSponsorRepository.create(teamSponsor);
    }

    /**
     * Deletes a Team Sponsor
     * @param id of the deleted Team Sponsor
     */
    public void removeTeamSponsor(int id){
        teamSponsorRepository.delete(id);
    }

    /**
     * @return A List of all Entities in the Repository
     */
    public List<Person> getAllPersons(){
        return personRepository.getAll();
    }

    /**
     * Sorts all Person entities by their salary
     * @return sorted List
     */
    public List<Person> getAllSortedBySalary(){
        ArrayList<Person> personList = new ArrayList<>(personRepository.getAll());
        personList.sort((p1, p2) -> Float.compare(p1.getSalary(), p2.getSalary()));
        return personList;
    }

    /**
     * Sorts all Person entities by their age
     * @return sorted List
     */
    public List<Person> getAllSortedByAge(){
        List<Person> personList = new ArrayList<>(personRepository.getAll());
        personList.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        return personList;
    }


    /**
     * @return all Engineer Entities
     */
    public List<Engineer> getAllEngineers(){
        return engineerRepository.getAll();

    }

    /**
     * @return all Driver Entities
     */
    public List<Driver> getAllDrivers(){
        return driverRepository.getAll();
    }


    /**
     * Filters Engineers by a given specialty
     * @param specialty filterSpecialty
     * @return List of all appropriate Engineer
     */
    public List<Engineer> getEngineersBySpecialty(String specialty){
        List<Engineer> engineers=engineerRepository.getAll();
        List<Engineer> specialtyEngineers=new ArrayList<>();
        for(Engineer engineer:engineers){
            if(engineer.getSpecialty().equals(specialty))
            {
                specialtyEngineers.add(engineer);
            }
        }
        return specialtyEngineers;
    }

}
