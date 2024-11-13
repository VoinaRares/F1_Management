package main.com.consoleapp.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a race at a certain location and a certain date.
 */
public class Race extends Entity {

    private Location location;
    private Date date;
    private List<TeamSponsor> teamSponsors;

    public Race(int id,Location location, Date date) {
        super(id);
        this.location = location;
        this.date = date;
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

    public List<TeamSponsor> getTeamSponsors() {
        return teamSponsors;
    }

    public void setTeamSponsors(List<TeamSponsor> teamSponsors) {
        this.teamSponsors = teamSponsors;
    }

    public void addTeamSponsor(TeamSponsor teamSponsor) {
        this.teamSponsors.add(teamSponsor);
    }
}
