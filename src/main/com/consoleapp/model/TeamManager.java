package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the manager of a team
 */
public class TeamManager extends TeamMember {

    @JsonProperty("@type")
    private final String type = "teamManager";

    public TeamManager(int id, String name, int age, int experience, float salary, int teamId,
                       String username, String password)
    {
        super(id,name,age,experience,salary,username,password, teamId);
    }

    public TeamManager(){}

    @Override
    public String toString() {
        return super.toString() + " Team ID:" + getTeamId();
    }
}
