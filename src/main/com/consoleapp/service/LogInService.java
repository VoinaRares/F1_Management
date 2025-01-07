package main.com.consoleapp.service;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.*;

import java.util.ArrayList;
import java.util.List;

public class LogInService {

    private  IRepository<TeamManager> repositoryTeamManager;
    private  IRepository<F1Admin> repositoryF1Admin;
    private IRepository<Team> repositoryTeam;
    private IRepository<Engineer> repositoryEngineer;
    private IRepository<Race> repositoryRace;
    private IRepository<Sponsor> repositorySponsor;

    public LogInService(int repositoryChoice)
    {
        if(repositoryChoice == 1)
        {
            this.repositoryTeamManager = InMemoryRepository.getInstance(TeamManager.class);
            this.repositoryF1Admin = InMemoryRepository.getInstance(F1Admin.class);
            this.repositoryTeam = InMemoryRepository.getInstance(Team.class);
            this.repositoryEngineer = InMemoryRepository.getInstance(Engineer.class);
            this.repositoryRace = InMemoryRepository.getInstance(Race.class);
            this.repositorySponsor = InMemoryRepository.getInstance(Sponsor.class);
            populateInMemory();
        }
        if(repositoryChoice == 2)
        {
            this.repositoryTeamManager = InFileRepository.getInstance(TeamManager.class, "teamManagerRepo.txt");
            this.repositoryF1Admin = InFileRepository.getInstance(F1Admin.class, "f1AdminRepo.txt");
        }
        if(repositoryChoice == 3){
            this.repositoryTeamManager = new TeamManagerDBRepository("jdbc:mysql://localhost:3306/f1management",
                    "root", "parola123");
            this.repositoryF1Admin = new F1AdminDBRepository("jdbc:mysql://localhost:3306/f1management",
                    "root", "parola123");
        }
//        repository.create(teamManager);
//        repository.create(driver);
//        repository.create(engineer);
//        repository.create(adminho);
    }

    /**
     * Searches for the teamId of a given Team Member class member
     * Potential improvement: Search by currently logged in id
     * @param username of the currently logged in Person
     * @param password of the currently logged in Person
     * @return team id if the logged in Person is a Team Member, -1 if else
     */
    public int findTeamId(String username, String password){
        List<TeamManager> teamManagers = repositoryTeamManager.getAll();
        List<F1Admin> f1Admins = repositoryF1Admin.getAll();
        List<Person> persons = new ArrayList<Person>();
        persons.addAll(teamManagers);
        persons.addAll(f1Admins);

        for(Person p : persons){
            if(p.getUsername().equals(username) && p.getPassword().equals(password)){
                if(p instanceof TeamMember){
                    return ((TeamMember)p).getTeamId();
                }
            }
        }

        return -1;
    }

    /**
     * Searches for a person in the repo to match the username and the password
     * @param username of the person that is trying to log in
     * @param password of the person that is trying to log in
     * @return the job title of the found Person, "false" if there was no job with special privileges found
     */
    public String logIn(String username, String password) {
        List<TeamManager> teamManagers = repositoryTeamManager.getAll();
        List<F1Admin> f1Admins = repositoryF1Admin.getAll();
        List<Person> persons = new ArrayList<>();
        persons.addAll(teamManagers);
        persons.addAll(f1Admins);

        for(Person person:persons) {
            if ((username.equals(person.getUsername())))
            {
                if (password.equals(person.getPassword()))
                {

                    //found correct user and password
                    if(person instanceof TeamManager)
                    {
                        return "TeamManager";
                    }

                    if(person instanceof Engineer)
                    {
                        return "Engineer";
                    }
                    if(person instanceof Driver)
                    {
                        return "Driver";
                    }
                    if(person instanceof F1Admin)
                    {
                        return "F1Admin";
                    }
                }
                else
                {
                    return "false";
                }
            }
        }
        return "false";
    }

    private void populateInMemory(){
        TeamManager teamManager=new TeamManager(1,"Toto Wolff",50,10,2500, 0, "1","y");
        F1Admin adminho= new F1Admin(3,"Adminho",25, 3,2000,"4","y");
        Team team1 = new Team(1,"Mercedes", 150000);
        Location location1= new Location(120,"Italy","Europe",500,1000);
        Race race1=new Race(50,location1);
        Location location2= new Location(121,"France","Europe",400,1200);
        Race race2=new Race(51,location2);
        Location location3= new Location(122,"UAE","Asia",800,200);
        Race race3=new Race(52,location3);
        Location location4= new Location(123,"Japan","Asia",3000,1000);
        Race race4=new Race(53,location4);
        Sponsor sponsor1 = new Sponsor(1, "Peroni", "Italy");


        repositoryF1Admin.create(adminho);
        repositoryTeamManager.create(teamManager);
        repositoryTeam.create(team1);
        repositoryRace.create(race1);
        repositoryRace.create(race2);
        repositoryRace.create(race3);
        repositoryRace.create(race4);
        repositorySponsor.create(sponsor1);

    }

}
