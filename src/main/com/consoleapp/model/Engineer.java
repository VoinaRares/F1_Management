package main.com.consoleapp.model;

public class Engineer extends Person {

    private String specialty;
    private int yearsWithCurrentTeam;
    private final Team team;

    public Engineer(int id, String name, int age, int experience, float salary, String specialty,
                    int yearsWithCurrentTeam, Team team) {
        super(id, name, age, experience, salary);
        this.specialty = specialty;
        this.yearsWithCurrentTeam = yearsWithCurrentTeam;
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

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
