package main.com.consoleapp.model;

/**
 * Represents the F1 pilot
 */
public class Driver extends TeamMember {
    private int number;

    public Driver(int id, String name, int age, int experience, float salary, int number,
                  int teamId, String username, String password) {
        super(id, name, age, experience, salary, username, password, teamId);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
