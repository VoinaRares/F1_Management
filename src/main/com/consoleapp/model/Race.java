package main.com.consoleapp.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a race at a certain location and a certain date.
 */
public class Race extends Entity {

    private Location location;
    private Date date;
    private Map<Team, Sponsor> mainTeamSponsors;
    private List<TeamSponsor> teamSponsors;

    public Race(int id,Location location, Date date, Map<Team, Sponsor> mainTeamSponsors) {
        super(id);
        this.location = location;
        this.date = date;
        this.mainTeamSponsors = mainTeamSponsors;
    }

    public Race(int id, Location location) {
        super(id);
        this.location = location;
    }

    @Override
    public String toString() {
        return "Race{" +
                "date=" + date +
                ", location=" + location +
                '}';
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Team, Sponsor> getMainTeamSponsors() {
        return mainTeamSponsors;
    }

    public void setMainTeamSponsors(Map<Team, Sponsor> mainTeamSponsors) {
        this.mainTeamSponsors = mainTeamSponsors;
    }

    public List<TeamSponsor> getTeamSponsors() {
        return teamSponsors;
    }

    public void setTeamSponsors(List<TeamSponsor> teamSponsors) {
        this.teamSponsors = teamSponsors;
    }
}
