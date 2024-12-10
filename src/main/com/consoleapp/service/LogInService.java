package main.com.consoleapp.service;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.*;

import java.util.ArrayList;
import java.util.List;

public class LogInService {



    private final TeamManagerDBRepository repositoryTeamManager;
    private final F1AdminDBRepository repositoryF1Admin;

    public LogInService()
    {
//        TeamManager teamManager=new TeamManager(0,"Toto Wolff",50,10,2500, 0, "1","y");
//        Driver  driver= new Driver(1,"Charles Leclerc", 25, 6, 1200000, 16,2,"2","y" );
//        Engineer engineer=new Engineer(2, "Adrian Newey", 65, 22, 122555, "Aerodynamics",
//                5, 0, "3","y");
//        F1Admin adminho= new F1Admin(3,"Adminho",25, 3,2000,"4","y");


//        this.repositoryTeamManager = InFileRepository.getInstance(TeamManager.class, "teamManagerRepo.txt");
//        this.repositoryF1Admin = InFileRepository.getInstance(F1Admin.class, "f1AdminRepo.txt");

        this.repositoryTeamManager = new TeamManagerDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.repositoryF1Admin = new F1AdminDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

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

}
