package main.com.consoleapp.model;

public class TeamSponsorRace{
    int TeamSponsorId;
    int RaceId;
    int investment;
    public TeamSponsorRace(int teamSponsorId, int raceId, int investment) {
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
