package main.com.consoleapp.model;

public class TeamSponsorRace extends Entity {
    int TeamSponsorId;
    int RaceId;

    public TeamSponsorRace(int id, int teamSponsorId, int raceId) {
        super(id);
        this.TeamSponsorId = teamSponsorId;
        this.RaceId = raceId;
    }
}
