package main.com.consoleapp.model;

public class Location extends Entity{

    private String country;
    private String continent;

    private int coordinateX;
    private int coordinateY;

    public Location(int id, String country, String continent, int coordinateX, int coordinateY) {
        super(id);
        this.country = country;
        this.continent = continent;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
}
