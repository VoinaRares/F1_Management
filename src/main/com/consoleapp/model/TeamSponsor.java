package main.com.consoleapp.model;

public class TeamSponsor {
    private Team team;
    private Sponsor sponsor;
    private int investment_amount;

    TeamSponsor(Team team, Sponsor sponsor, int investment_amount) {
        this.team = team;
        this.sponsor = sponsor;
        this.investment_amount=investment_amount;
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

    public int getInvestment_amount() {
        return investment_amount;
    }

    public void setInvestment_amount(int investment_amount) {
        this.investment_amount = investment_amount;
    }
}
