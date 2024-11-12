package main.com.consoleapp.model;

public class TeamSponsor extends Entity{

    private Team team;
    private Sponsor sponsor;
    private int investmentAmount;

    TeamSponsor(int id,Team team, Sponsor sponsor, int investmentAmount) {
        super(id);
        this.team = team;
        this.sponsor = sponsor;
        this.investmentAmount = investmentAmount;
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

    public int getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(int investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
}
