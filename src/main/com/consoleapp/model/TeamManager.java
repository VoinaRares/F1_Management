package main.com.consoleapp.model;

/**
 * Represents the manager of a team
 */
public class TeamManager extends TeamMember {


    public TeamManager(int id, String name, int age, int experience, float salary, int teamId,
                       String username, String password)
    {
        super(id,name,age,experience,salary,username,password, teamId);
    }

}
