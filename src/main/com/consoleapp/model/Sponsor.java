package main.com.consoleapp.model;

/**
 * Represents a sponsor for races or teams
 */
public class Sponsor extends Entity{
    private String sponsorName;

    private String country;

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Sponsor(int id,String sponsorName, String country) {
        super(id);
        this.sponsorName = sponsorName;

        this.country = country;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", Sponsor Name='" + sponsorName + '\'' +
                ", Country='" + country + '\'' +
                '}'  ;
    }
}
