package main.com.consoleapp.service;

import main.com.consoleapp.model.*;
import main.com.consoleapp.model.Exceptions.BusinessLogicException;
import main.com.consoleapp.model.Exceptions.EntityNotFoundException;
import main.com.consoleapp.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides connection between Repository and logic for the attributes that Team Manager can perform
 */
public class TeamManagerService {

//    private final IRepository<Person> personRepository;
    private  IRepository<Driver> driverRepository;
    private  IRepository<Engineer> engineerRepository;
    private  IRepository<F1Admin> f1AdminRepository;
    private  IRepository<TeamSponsor> teamSponsorRepository;;
    private  IRepository<TeamManager> teamManagerRepository;

    //Might be used for data validation in the Controller
    private  IRepository<Sponsor> sponsorRepo;
    private  IRepository<Team> teamRepo;

    public TeamManagerService(int repositoryChoice) {


        if(repositoryChoice == 1) {
            this.sponsorRepo = InMemoryRepository.getInstance(Sponsor.class);
            this.teamRepo = InMemoryRepository.getInstance(Team.class);
            this.teamSponsorRepository = InMemoryRepository.getInstance(TeamSponsor.class);
            this.driverRepository = InMemoryRepository.getInstance(Driver.class);
            this.engineerRepository = InMemoryRepository.getInstance(Engineer.class);
            this.f1AdminRepository = InMemoryRepository.getInstance(F1Admin.class);
            this.teamManagerRepository = InMemoryRepository.getInstance(TeamManager.class);
        }
        if(repositoryChoice == 2) {
            this.sponsorRepo = InFileRepository.getInstance(Sponsor.class, "sponsorRepo.txt");
            this.teamRepo = InFileRepository.getInstance(Team.class, "teamRepo.txt");
            this.teamSponsorRepository = InFileRepository.getInstance(TeamSponsor.class, "teamSponsorRepo.txt");
            this.driverRepository=InFileRepository.getInstance(Driver.class, "driverRepo.txt");
            this.engineerRepository=InFileRepository.getInstance(Engineer.class, "engineerRepo.txt");
            this.f1AdminRepository=InFileRepository.getInstance(F1Admin.class, "f1AdminRepo.txt");
            this.teamManagerRepository=InFileRepository.getInstance(TeamManager.class, "teamManagerRepo.txt");
        }
        if(repositoryChoice == 3) {
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
    public boolean addEngineer(int age, int experience, String name,
                               float salary, String specialty, int yearsWithCurrentTeam,
                               int TeamId, String userName, String password){

        int id = engineerRepository.getNextId();
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

    /**
     * Creates a new Team Sponsor with the given Parameters and tries to add it to the Repository
     */
    public void addTeamSponsor(int id,String sponsorName, int teamId, int investmentAmount){
        List<Sponsor> sponsors = sponsorRepo.getAll();
        for(Sponsor sponsor:sponsors){
            if(sponsor.getSponsorName().equals(sponsorName)){
                TeamSponsor teamSponsor = new TeamSponsor(id, teamId, sponsor.getId(), investmentAmount);
                teamSponsorRepository.create(teamSponsor);
                return;
            }
        }
        throw new EntityNotFoundException("Sponsor does not exist");
    }

    /**
     * Deletes a Team Sponsor
     * @param id of the deleted Team Sponsor
     */
    public void removeTeamSponsor(int id, int teamId){
        if(teamSponsorRepository.read(id).getTeamId() == teamId){
            teamSponsorRepository.delete(id);

        }
    }

    /**
     * @return A List of all Entities in the Repository
     */

    public List<Person> getAllPersons(){
        List<Person> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());

        return persons;
    }

    /**
     * Sorts all Person entities by their salary
     * @return sorted List
     */
    public List<TeamMember> getAllSortedBySalary(int teamId){
        List<TeamMember> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());
        persons = persons.stream().filter(person -> person.getTeamId() == teamId).collect(Collectors.toList());
        persons.sort((p1, p2) -> Float.compare(p1.getSalary(), p2.getSalary()));
        return persons;
    }

    /**
     * Sorts all Person entities by their age
     * @return sorted List
     */
    public List<TeamMember> getAllSortedByAge(int teamId){
        List<TeamMember> persons = new ArrayList<>();
        persons.addAll(driverRepository.getAll());
        persons.addAll(engineerRepository.getAll());
        persons.addAll(teamManagerRepository.getAll());
        persons = persons.stream().filter(person -> person.getTeamId() == teamId).collect(Collectors.toList());
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

    /**
     * Removes Drivers by a given id
     * @param id of the removed Driver
     * @param teamId of the currently logged-in user. Is used to verify that a team Manager
     *               cannot remove a Driver from a different team
     */
    public void removeDriver(int id, int teamId){
        if(driverRepository.read(id).getTeamId() == teamId){
            driverRepository.delete(id);
        }
        else{
            throw new EntityNotFoundException("Driver does not exist or is not part of your team");
        }

    }

    /**
     * Removes Engineer by a given id
     * @param id of the removed Engineer
     * @param teamId of the currently logged-in user. Is used to verify that a team Manager
     *               cannot remove a Driver from a different team
     */
    public void removeEngineer(int id, int teamId){
        if(engineerRepository.read(id).getTeamId() == teamId){
            engineerRepository.delete(id);
        }
        else{
            throw new EntityNotFoundException("Engineer does not exist or is not part of your team");
        }

    }


    /**
     * Shows all Sponsors for one team
     * @param teamId of the Team of the currently logged-in user
     * @return List of Sponsors that support the team
     */
    public List<Sponsor> showTeamSponsors(int teamId) {

        List<Sponsor> sponsorForTeam=new ArrayList<>();
        List<TeamSponsor> teamSponsors=teamSponsorRepository.getAll();
        List<Integer> ids=new ArrayList<>();
        for(TeamSponsor teamSponsor:teamSponsors){
            if(teamSponsor.getTeamId() == teamId){
                ids.add(teamSponsor.getSponsorId());
            }
        }

        for(Integer id:ids){
            sponsorForTeam.add(sponsorRepo.read(id));
        }
        return sponsorForTeam;
    }

    public List<Sponsor> showSponsors() {
        return sponsorRepo.getAll();
    }

    public List<TeamSponsor> showTeamSponsorsId(int teamId) {
        List<TeamSponsor> teamSponsors=teamSponsorRepository.getAll();
        List<TeamSponsor> newTeamSponsors=new ArrayList<>();
        for(TeamSponsor teamSponsor:teamSponsors){
            if(teamSponsor.getTeamId() == teamId){
                newTeamSponsors.add(teamSponsor);
            }
        }
        return newTeamSponsors;
    }

    public boolean usernameISUnique(String username) {
        List<Person> personList = new ArrayList<>();
        personList.addAll(driverRepository.getAll());
        personList.addAll(engineerRepository.getAll());
        personList.addAll(teamManagerRepository.getAll());
        personList.addAll(f1AdminRepository.getAll());

        for(Person person:personList){
            if(person.getUsername().equals(username)){
                return false;
            }
        }

        return true;
    }
}
