package main.com.consoleapp.model;

public class TeamSponsor {
    private Team team;
    private Sponsor sponsor;

    TeamSponsor(Team team, Sponsor sponsor) {
        this.team = team;
        this.sponsor = sponsor;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
}
