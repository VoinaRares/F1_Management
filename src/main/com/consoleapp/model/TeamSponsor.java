package main.com.consoleapp.model;

public class TeamSponsor extends Entity{

    private Team team;
    private Sponsor sponsor;
    private int investment_amount;

    TeamSponsor(int id,Team team, Sponsor sponsor, int investment_amount) {
        super(id);
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
