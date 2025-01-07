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
    private IRepository<TeamSponsor> repositoryTeamSponsor;

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
            this.repositoryTeamSponsor = InMemoryRepository.getInstance(TeamSponsor.class);
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
        F1Admin adminho= new F1Admin(3,"Adminho",25, 3,2000,"00","y");
        TeamManager teamManager1 =new TeamManager(1,"Toto Wolff",50,10,2500, 1,
                "1","y");
        TeamManager teamManager2 = new TeamManager(2, "Fred Vasseur", 60, 20, 5000,
                2, "2", "y");
        TeamManager teamManager3 = new TeamManager(3, "Christian Horner", 60, 20,
                8000, 3, "3","y");
        TeamManager teamManager4 = new TeamManager(4, "Andrea Stella", 60, 20, 1500,
                4, "4","y");
        TeamManager teamManager5 = new TeamManager(5, "Laurent Mekies", 60, 20, 1500,
                5, "5","y");
        Team team1 = new Team(1,"Mercedes", 150000000);
        Team team2= new Team(2, "Ferrari", 150000000);
        Team team3= new Team(3, "Red Bull", 150000000);
        Team team4 = new Team(4, "McLaren", 150000000);
        Team team5 = new Team(5, "Toro Rosso", 150000000);
        Location location1= new Location(120,"Italy","Europe",500,800);
        Race race1=new Race(50,location1);
        Location location2= new Location(121,"France","Europe",400,900);
        Race race2=new Race(51,location2);
        Location location3= new Location(122,"UAE","Asia",800,200);
        Race race3=new Race(52,location3);
        Location location4= new Location(123,"Japan","Asia",3000,1000);
        Race race4=new Race(53,location4);
        Location location5 = new Location(124, "Brazil", "South America", -2000, -500);
        Race race5=new Race(54, location5);
        Location location6 = new Location(125, "Bahrain", "Asia", 790, 190);
        Race race6=new Race(55, location6);
        Location location7 = new Location(126, "Belgium", "Europe", 450, 1300);
        Race race7=new Race(56, location7);
        Location location8 = new Location(127, "USA", "North America", -2000, 700);
        Race race8=new Race(57, location8);
        Location location9 = new Location(128, "Canada", "North America", -1800, 1000);
        Race race9 = new Race(58, location9);
        Location location10 = new Location(129, "Australia", "Australia", 2000, -1000);
        Race race10 = new Race(59, location10);


        Engineer engineer1 = new Engineer(1, "James Alisson", 50, 15, 2000, "Engine",
                5, 1, "a", "y");

        Engineer engineer2 = new Engineer(2, "Papa Giovanni", 50, 15, 2000, "Engine",
                2, 2, "a", "y");

        Engineer engineer3 = new Engineer(3, "Adrian Newey", 55, 20, 12000, "Aerodynamics",
                2, 3, "a", "y");

        Engineer engineer4 = new Engineer(4, "Valentino Chiesa", 50, 15, 2000, "Engine",
                 7, 2, "a", "y");

        Sponsor sponsor1 = new Sponsor(1, "Peroni", "Italy");
        Sponsor sponsor2 = new Sponsor(2, "Louis Vuitton", "France");
        Sponsor sponsor3 = new Sponsor(3, "Pepsi", "USA");
        Sponsor sponsor4 = new Sponsor(4, "Red Bull", "Austria");
        Sponsor sponsor5 = new Sponsor(5, "NTT Data", "Japan");
        Sponsor sponsor6 = new Sponsor(6, "Shell", "UK");
        Sponsor sponsor7 = new Sponsor(7, "Santander", "Spain");



        TeamSponsor teamSponsor1 = new TeamSponsor(1, 1, 2, 2500);
        TeamSponsor teamSponsor2 = new TeamSponsor(2, 2, 6, 7000);
        TeamSponsor teamSponsor3 = new TeamSponsor(3, 2, 7, 10000);
        TeamSponsor teamSponsor4 = new TeamSponsor(4, 5, 3, 3000);
        TeamSponsor teamSponsor5 = new TeamSponsor(5, 2, 1, 21000);
        TeamSponsor teamSponsor6 = new TeamSponsor(6, 3, 4, 25000);
        TeamSponsor teamSponsor7 = new TeamSponsor(7, 5, 4, 4000);
        TeamSponsor teamSponsor8 = new TeamSponsor(8, 4, 5, 11000);
        repositoryF1Admin.create(adminho);
        repositoryTeamManager.create(teamManager1);
        repositoryTeamManager.create(teamManager2);
        repositoryTeamManager.create(teamManager3);
        repositoryTeamManager.create(teamManager4);
        repositoryTeamManager.create(teamManager5);
        repositoryTeam.create(team1);
        repositoryTeam.create(team2);
        repositoryTeam.create(team3);
        repositoryTeam.create(team4);
        repositoryTeam.create(team5);
        repositoryRace.create(race1);
        repositoryRace.create(race2);
        repositoryRace.create(race3);
        repositoryRace.create(race4);
        repositoryRace.create(race5);
        repositoryRace.create(race6);
        repositoryRace.create(race7);
        repositoryRace.create(race8);
        repositoryRace.create(race9);
        repositoryRace.create(race10);
        repositorySponsor.create(sponsor1);
        repositorySponsor.create(sponsor2);
        repositorySponsor.create(sponsor3);
        repositorySponsor.create(sponsor4);
        repositorySponsor.create(sponsor5);
        repositorySponsor.create(sponsor6);
        repositorySponsor.create(sponsor7);
        repositoryEngineer.create(engineer1);
        repositoryEngineer.create(engineer2);
        repositoryEngineer.create(engineer3);
        repositoryEngineer.create(engineer4);
        repositoryTeamSponsor.create(teamSponsor1);
        repositoryTeamSponsor.create(teamSponsor2);
        repositoryTeamSponsor.create(teamSponsor3);
        repositoryTeamSponsor.create(teamSponsor4);
        repositoryTeamSponsor.create(teamSponsor5);
        repositoryTeamSponsor.create(teamSponsor6);
        repositoryTeamSponsor.create(teamSponsor7);
        repositoryTeamSponsor.create(teamSponsor8);

        ;
    }

}
