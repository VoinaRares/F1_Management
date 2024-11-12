package main.com.consoleapp.model;

import java.util.ArrayList;
import java.util.List;

public class Team extends Entity{
    private String teamName;
    private int budget;
    private List<Sponsor> sponsors;
    private List<Person> staff;

    public Team(int id,String teamName, int budget) {
        super(id);
        this.teamName = teamName;
        this.budget = budget;
        sponsors = new ArrayList<Sponsor>();
        staff = new ArrayList<Person>();
    }

    public Team(int id,String teamName, int budget, List<Sponsor> sponsors, List<Person> staff) {
        super(id);
        this.teamName = teamName;
        this.budget = budget;
        this.sponsors = sponsors;
        this.staff = staff;
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

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    public List<Person> getStaff() {
        return staff;
    }

    public void setStaff(List<Person> staff) {
        this.staff = staff;
    }

    public void addSponsor(Sponsor sponsor) {
        sponsors.add(sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsors.remove(sponsor);
    }

    public void addStaff(Person member) {
        staff.add(member);
    }

    public void removeStaff(Person member) {
        staff.remove(member);
    }
}
