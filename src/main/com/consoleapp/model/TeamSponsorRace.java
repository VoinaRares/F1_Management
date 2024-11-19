package main.com.consoleapp.model;

public class TeamSponsorRace extends Entity {
    int TeamSponsorId;
    int RaceId;

    public TeamSponsorRace(int id, int teamSponsorId, int raceId) {
        super(id);
        this.TeamSponsorId = teamSponsorId;
        this.RaceId = raceId;
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
}
