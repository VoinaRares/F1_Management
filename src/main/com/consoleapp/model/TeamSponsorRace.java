package main.com.consoleapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamSponsorRace extends Entity {
    int TeamSponsorId;
    int RaceId;
    int investment;
    @JsonProperty("@type")
    private final String type = "teamSponsorRace";

    public TeamSponsorRace(int id, int teamSponsorId, int raceId, int investment) {
        super(id);
        this.TeamSponsorId = teamSponsorId;
        this.RaceId = raceId;
        this.investment = investment;
    }

    public int getRaceId() {
        return RaceId;
    }

    public void setRaceId(int raceId) {
        RaceId = raceId;
    }

    public int getTeamSponsorId() {
        return TeamSponsorId;
    }

    public void setTeamSponsorId(int teamSponsorId) {
        TeamSponsorId = teamSponsorId;
    }

    @Override
    public String toString() {
        return "TeamSponsorRace{" +
                "TeamSponsorId=" + TeamSponsorId +
                ", RaceId=" + RaceId +
                ", investment=" + investment +
                '}';
    }

    public int getInvestment() {
        return investment;
    }

    public void setInvestment(int investment) {
        this.investment = investment;
    }
}
