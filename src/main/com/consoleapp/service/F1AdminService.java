package main.com.consoleapp.service;
import main.com.consoleapp.model.Location;
import main.com.consoleapp.model.Race;
import main.com.consoleapp.repository.InMemoryRepository;

import java.util.Map;

public class F1AdminService {

    InMemoryRepository<Race> repository = new InMemoryRepository<Race>();
    public void addRace(String country, String continent, int coordinate_x, int coordinate_y)
    {
        int id=100;
        Location location = new Location(100,country,continent,coordinate_x,coordinate_y);
        repository.create(new Race(id,location));
    }
}
