package main.com.consoleapp.model;

import java.util.Map;

public class Location extends Entity{

    private String country;
    private String continent;
    /**
     * Uses a map to simulate a tuple of coordinates (x, y)
     */
    private int coordinate_x;
    private int coordinate_y;

    public Location(int id,String country, String continent, int coordinate_x, int coordinate_y) {
        super(id);
        this.country = country;
        this.continent = continent;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
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

    public Map<Float, Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<Float, Float> coordinates) {
        this.coordinates = coordinates;
    }
}
