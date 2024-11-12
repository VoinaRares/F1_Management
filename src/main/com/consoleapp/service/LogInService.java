package main.com.consoleapp.service;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.IRepository;
import main.com.consoleapp.repository.InMemoryRepository;
import java.util.List;

public class LogInService {

    public LogInService()
    {
        TeamManager teamManager=new TeamManager(0,"Toto Wolff",50,10,2500, 0, "EX1","y");
        Driver  driver= new Driver(1,"Charles Leclerc", 25, 6, 1200000, 16,2,"EX2","y" );
        Engineer engineer=new Engineer(2, "Adrian Newey", 65, 22, 122555, "Aerodynamics",
                5, 0, "EX3","y");
        F1Admin adminho= new F1Admin(3,"Adminho",25, 3,2000,"EX4","y");
    }
    InMemoryRepository<Person>repository=new InMemoryRepository<Person>();


    public String login(String username, String password) {
        List<Person> persons = repository.getAll();
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
