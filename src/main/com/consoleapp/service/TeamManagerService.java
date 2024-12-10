package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides connection between Repository and logic for the attributes that Team Manager can perform
 */
public class TeamManagerService {

//    private final IRepository<Person> personRepository;
    private final IRepository<Driver> driverRepository;
    private final IRepository<Engineer> engineerRepository;
    private final IRepository<TeamSponsor> teamSponsorRepository;
    private final IRepository<F1Admin> f1AdminRepository;
    private final IRepository<TeamManager> teamManagerRepository;

    //Might be used for data validation in the Controller
    private final IRepository<Sponsor> sponsorRepo;
    private final IRepository<Team> teamRepo;

    public TeamManagerService() {
//       this.personRepository = InFileRepository.getInstance(Person.class, "personRepo.txt");
//       this.sponsorRepo = InFileRepository.getInstance(Sponsor.class, "sponsorRepo.txt");
//       this.teamRepo = InFileRepository.getInstance(Team.class, "teamRepo.txt");
//       this.teamSponsorRepository = InFileRepository.getInstance(TeamSponsor.class, "teamSponsorRepo.txt");
//       this.driverRepository=InFileRepository.getInstance(Driver.class, "driverRepo.txt");
//       this.engineerRepository=InFileRepository.getInstance(Engineer.class, "engineerRepo.txt");

        this.sponsorRepo = new SponsorDBRepository("jdbc:mysql://localhost:3306/f1management", "root",
                "parola123");
        this.teamRepo = new TeamDBRepository("jdbc:mysql://localhost:3306/f1management", "root",
                "parola123");
        this.teamSponsorRepository = new TeamSponsorDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.driverRepository = new DriverDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.engineerRepository = new EngineerDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.f1AdminRepository = new F1AdminDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.teamManagerRepository = new TeamManagerDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

    }

    /**
     * Creates a new F1 Admin with the given Parameters and tries to add it to the Repository
     * @return true if correctly created or false if else
     */
    public boolean addF1Admin(int id, int age, int experience, String name,
                              float salary, String userName, String password){
        F1Admin person = new F1Admin(id, name, age, experience, salary, userName, password );
        f1AdminRepository.create(person);
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
        teamManagerRepository.create(person);
        return true;
    }


    //NEEDS TO BE REDONE TO FIT INTO THE DATABASE THAT DOES NOT STORE PERSON
    /**
     * Deletes an Entity from the repository
     * @param id of the Entity to be deleted
     */
    public void removePerson(int id){
        //personRepository.delete(id);
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
        ArrayList<Person> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());
        return persons;
    }

    /**
     * Sorts all Person entities by their salary
     * @return sorted List
     */
    public List<Person> getAllSortedBySalary(){
        ArrayList<Person> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());
        persons.addAll(f1AdminRepository.getAll());
        persons.sort((p1, p2) -> Float.compare(p1.getSalary(), p2.getSalary()));
        return persons;
    }

    /**
     * Sorts all Person entities by their age
     * @return sorted List
     */
    public List<Person> getAllSortedByAge(){
        ArrayList<Person> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());
        persons.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        return persons;
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
