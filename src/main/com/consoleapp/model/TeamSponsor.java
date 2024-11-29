package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to represent the many-to-many relationship between the Sponsors and the Teams
 */
public class TeamSponsor extends Entity{

    private int teamId;
    private int sponsorId;
    private int investmentAmount;

    @JsonProperty("@type")
    private final String type = "teamSponsor";

    public TeamSponsor(int id,int teamId, int sponsorId, int investmentAmount) {
        super(id);
        this.teamId = teamId;
        this.sponsorId = sponsorId;
        this.investmentAmount = investmentAmount;
    }

    public TeamSponsor(int id, int teamId, int sponsorId) {
        super(id);
        this.teamId = teamId;
        this.sponsorId = sponsorId;
        this.investmentAmount = 0;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public int getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(int investmentAmount) {
        this.investmentAmount = investmentAmount;
    }
}
