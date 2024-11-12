package main.com.consoleapp.model;

public class Engineer extends Person {

    private String specialty;
    private int yearsWithCurrentTeam;
    private int teamId;



    public Engineer(int id, String name, int age, int experience, float salary, String specialty,
                    int yearsWithCurrentTeam, int teamId, String username, String password) {
        super(id, name, age, experience, salary,username, password);
        this.specialty = specialty;
        this.yearsWithCurrentTeam = yearsWithCurrentTeam;
        this.teamId = teamId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
