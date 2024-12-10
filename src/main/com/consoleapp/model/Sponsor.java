package main.com.consoleapp.model;

/**
 * Represents a sponsor for races or teams
 */
public class Sponsor extends Entity{
    private String sponsorName;
    private int investmentAmount;
    private String country;

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public int getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(int investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Sponsor(int id,String sponsorName, int investmentAmount, String country) {
        super(id);
        this.sponsorName = sponsorName;
        this.investmentAmount = investmentAmount;
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
