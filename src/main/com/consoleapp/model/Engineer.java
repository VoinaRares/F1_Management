package main.com.consoleapp.model;


/**
 * Represents a F1 Engineer
 */
public class Engineer extends TeamMember {

    private String specialty;
    private int yearsWithCurrentTeam;

    public Engineer(int id, String name, int age, int experience, float salary, String specialty,
                    int yearsWithCurrentTeam, int teamId, String username, String password) {
        super(id, name, age, experience, salary,username, password, teamId);
        this.specialty = specialty;
        this.yearsWithCurrentTeam = yearsWithCurrentTeam;
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

    public String toString()
    {
        return super.toString() + "Team ID: " + getTeamId() + ", Specialty: " + getSpecialty();
    }
}
