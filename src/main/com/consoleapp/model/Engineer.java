package main.com.consoleapp.model;

public class Engineer extends Person {

    private String specialty;
    private int  yearsWithCurrentTeam;
    // private Team team;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYearsWithCurrentTeam() {
        return yearsWithCurrentTeam;
    }

    public void setYearsWithCurrentTeam(int yearsWithCurrentTeam) {
        this.yearsWithCurrentTeam = yearsWithCurrentTeam;
    }
}
