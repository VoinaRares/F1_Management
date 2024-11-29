package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the F1 pilot
 */
public class Driver extends TeamMember {
    private int number;
    @JsonProperty("@type")
    private final String type = "driver";

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

    public String toString()
    {
        return super.toString() + " Team ID: " + getTeamId() + ", Number: " + getNumber();
    }

}
