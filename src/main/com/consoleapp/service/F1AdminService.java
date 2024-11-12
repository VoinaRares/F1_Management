package main.com.consoleapp.service;
import main.com.consoleapp.model.Location;
import main.com.consoleapp.model.Race;
import main.com.consoleapp.repository.InMemoryRepository;

import java.util.Map;

public class F1AdminService {

    //InMemoryRepository<Race> repository = new InMemoryRepository<Race>();
    private final InMemoryRepository<Race> repository;

    public F1AdminService() {
        this.repository = InMemoryRepository.getInstance(Race.class);
    }

    public void addRace(String country, String continent, int coordinateX, int coordinateY)
    {
        int id=100;
        Location location = new Location(100,country,continent,coordinateX,coordinateY);
        repository.create(new Race(id,location));
    }
}
