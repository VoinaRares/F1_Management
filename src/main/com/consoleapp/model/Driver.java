package main.com.consoleapp.model;

public class Driver extends Person {
    private int number;
    private Team team;

    public Driver(int id, String name, int age, int experience, float salary, int number, Team team) {
        super(id, name, age, experience, salary);
        this.number = number;
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
