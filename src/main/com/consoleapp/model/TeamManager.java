package main.com.consoleapp.model;

public class TeamManager extends Person {

    private Team team;

    TeamManager(int id,String name, int age, int experience, float salary,Team team)
    {
        super(id,name,age,experience,salary);
        this.team = team;
    }


    public Team getTeam()
    {
        return team;
    }

    public void setTeam(Team team)
    {
        this.team=team;
    }

    public void addMember(int id)
    {

    }

    public void removeMember(int id)
    {

    }

    public void swapMembers(int current_id,int new_id)
    {

    }
}
