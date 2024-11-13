package main.com.consoleapp.service;
import main.com.consoleapp.model.Date;
import main.com.consoleapp.model.Location;
import main.com.consoleapp.model.Race;
import main.com.consoleapp.repository.InMemoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Provides connection between Repository and logic for the attributes that F1 Admin can perform
 */
public class F1AdminService {

    //InMemoryRepository<Race> repository = new InMemoryRepository<Race>();
    private final InMemoryRepository<Race> repository;

    public F1AdminService() {

        this.repository = InMemoryRepository.getInstance(Race.class);
        Location location1= new Location(120,"Italy","Europe",500,1000);
        Race race1=new Race(50,location1);
        Location location2= new Location(121,"France","Europe",400,1200);
        Race race2=new Race(51,location2);
        Location location3= new Location(122,"UAE","Asia",800,200);
        Race race3=new Race(52,location3);
        Location location4= new Location(123,"Japan","Asia",3000,1000);
        Race race4=new Race(53,location4);

        repository.create(race1);
        repository.create(race2);
        repository.create(race3);
        repository.create(race4);
    }

    public void addRace(String country, String continent, int coordinateX, int coordinateY)
    {
        int id=100;
        Location location = new Location(100,country,continent,coordinateX,coordinateY);
        repository.create(new Race(id,location));
    }

    public void generateCalendar(String start_country, String end_country,int day, int month, int year)
    {
        List<Race>races=repository.getAll();
        int numberOfRaces=races.size();
        List<Race> calendar=new ArrayList<>();
        Race finalRace=null;
        for(Race race:races) {
            if (race.getLocation().getCountry().equals(start_country)) {
                Date date=new Date(day,month,year);
                race.setDate(date);
                calendar.add(race);
                races.remove(race);
            }
            if(race.getLocation().getCountry().equals(end_country)) {
                finalRace=race;
                races.remove(race);
            }
        }

        Race newRace=null;
        while(calendar.size()<numberOfRaces-1)
        {

            newRace=getNextRace(calendar.getLast(), races);
            calendar.add(newRace);
            races.remove(newRace);
        }

    }

    public Race getNextRace(Race lastRace, List<Race> races)
    {
        Race nextRace=null;
        Float min_distance= 10000000F;
        if(lastRace.getDate().getMonth()>=4 && lastRace.getDate().getMonth()<=9)
        {
            for(Race race:races)
            {
                Float distance=getDistance(lastRace.getLocation().getCoordinateX(),
                        lastRace.getLocation().getCoordinateY(),race.getLocation().getCoordinateX(),race.getLocation().getCoordinateY());
                if(distance<min_distance)
                {
                    min_distance=distance;
                    nextRace=race;
                }
            }
        }
        else
        {
            for(Race race:races)
            {

                Float distance=getDistance(lastRace.getLocation().getCoordinateX(),
                        lastRace.getLocation().getCoordinateY(),race.getLocation().getCoordinateX(),race.getLocation().getCoordinateY());
                if(distance<min_distance)
                {
                    min_distance=distance;
                    nextRace=race;
                }
            }
        }
        return nextRace;

    }

    public float getDistance(float coordinate1_x, float coordinate1_y, float coordinate2_x, float coordinate2_y)
    {
        return (float) sqrt(pow(coordinate1_x - coordinate2_x,2)+pow(coordinate1_y - coordinate2_y,2));
    }
}
