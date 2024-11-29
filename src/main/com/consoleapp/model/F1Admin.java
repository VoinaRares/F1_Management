package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a F1 Admin.
 * Handles all the races and the organization
 */
public class F1Admin extends Person {

    private Calendar calendar;
    @JsonProperty("@type")
    private final String type = "f1Admin";

    public F1Admin(int id, String name, int age, int experience, float salary,String username, String password) {
        super(id, name, age, experience, salary, username, password);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Organizes the races stored in the calendar
     * @param startPoint Starting Race
     * @param endPoint Ending Race
     */
    public void organizeRaces(Race startPoint, Race endPoint) {

    }

    /**
     * Adds a race to the Calendar
     */
    public void addRace(){

    }
}
