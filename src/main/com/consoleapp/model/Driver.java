package main.com.consoleapp.model;

public class Driver extends Person {
    private int number;
    private int teamID;

    public Driver(int id, String name, int age, int experience, float salary, int number, int teamID, String username, String password) {
        super(id, name, age, experience, salary, username, password);
        this.number = number;
        this.teamID = teamID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
