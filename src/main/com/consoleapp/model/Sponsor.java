package main.com.consoleapp.model;

public class Sponsor {
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

    public Sponsor(String sponsorName, int investmentAmount, String country) {
        this.sponsorName = sponsorName;
        this.investmentAmount = investmentAmount;
        this.country = country;
    }
}
