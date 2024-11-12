package main.com.consoleapp.model;

import java.util.Map;

public class Race {

    private Location location;
    private Date date;
    private Map<Team, Sponsor> mainTeamSponsors;

    public Race(Location location, Date date, Map<Team, Sponsor> mainTeamSponsors) {
        this.location = location;
        this.date = date;
        this.mainTeamSponsors = mainTeamSponsors;
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
}
