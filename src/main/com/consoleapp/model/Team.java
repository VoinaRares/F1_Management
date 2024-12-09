package main.com.consoleapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team competing in F1
 */
public class Team extends Entity{
    private String teamName;
    private int budget;

    public Team(int id,String teamName, int budget) {
        super(id);
        this.teamName = teamName;
        this.budget = budget;
    }


    public Team(int id, String teamName, int budget, List<Sponsor> sponsors, List<Person> staff) {
        super(id);
        this.teamName = teamName;
        this.budget = budget;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return super.toString() + " Team Name: " + teamName + " Budget: " + budget;
    }
}
