package main.com.consoleapp.model;

/**
 * Represents the F1 pilot
 */
public class Driver extends Person {
    private int number;
    private int teamId;

    public Driver(int id, String name, int age, int experience, float salary, int number,
                  int teamId, String username, String password) {
        super(id, name, age, experience, salary, username, password);
        this.number = number;
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
