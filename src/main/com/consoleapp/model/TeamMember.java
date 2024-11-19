package main.com.consoleapp.model;

public class TeamMember extends Person {

    private int teamId;
    public TeamMember(int id, String name, int age, int experience, float salary, String username,
                      String password, int teamId) {
        super(id, name, age, experience, salary, username, password);
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
