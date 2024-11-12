package main.com.consoleapp.model;

public class TeamManager extends Person {

    private int teamId;

    public TeamManager(int id, String name, int age, int experience, float salary, int teamId, String username, String password)
    {
        super(id,name,age,experience,salary,username,password);
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
