package main.com.consoleapp.model;

import java.util.Map;

public class Calendar {
    /**
     * A Pair for (Race, cost)
     * cost represents the cost of transporting a team to that certain location
     */
    private Map<Race, Float> raceCosts;

    public Calendar(Map<Race, Float> raceCosts) {
        this.raceCosts = raceCosts;
    }

    public Map<Race, Float> getRaceCosts() {
        return raceCosts;
    }

    public void setRaceCosts(Map<Race, Float> raceCosts) {
        this.raceCosts = raceCosts;
    }

    /**
     * Used for ordering the races added in the calendar for the smallest costs.
     */
    public void manageRaces(){

    }

}
