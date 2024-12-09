package main.com.consoleapp.service;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Provides connection between Repository and logic for the attributes that F1 Admin can perform
 */
public class F1AdminService {

    //InMemoryRepository<Race> repository = new InMemoryRepository<Race>();
    private final IRepository<Race> raceRepository;
    private final IRepository<Team> teamRepository;
    private final IRepository<Sponsor> sponsorRepository;
    private final IRepository<TeamSponsor> teamSponsorRepository;
    public F1AdminService() {
        //Should probably be added with addRace
//        this.raceRepository = InFileRepository.getInstance(Race.class, "raceRepo.txt");
//        this.teamRepository = InFileRepository.getInstance(Team.class, "teamRepo.txt");
//        this.sponsorRepository = InFileRepository.getInstance(Sponsor.class, "sponsorRepo.txt");
//        this.teamSponsorRepository = InFileRepository.getInstance(TeamSponsor.class, "teamSponsorRepo.txt");
        this.raceRepository = new RaceDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.teamRepository = new TeamDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.sponsorRepository = new SponsorDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");
        this.teamSponsorRepository = new TeamSponsorDBRepository("jdbc:mysql://localhost:3306/f1management",
                "root", "parola123");

//        Location location1= new Location(120,"Italy","Europe",500,1000);
//        Race race1=new Race(50,location1);
//        Location location2= new Location(121,"France","Europe",400,1200);
//        Race race2=new Race(51,location2);
//        Location location3= new Location(122,"UAE","Asia",800,200);
//        Race race3=new Race(52,location3);
//        Location location4= new Location(123,"Japan","Asia",3000,1000);
//        Race race4=new Race(53,location4);
//
//        repository.create(race1);
//        repository.create(race2);
//        repository.create(race3);
//        repository.create(race4);
    }

    /**
     * Adds a Race
     * @param country of the Race
     * @param continent of the Race
     * @param coordinateX of the Race
     * @param coordinateY of the Race
     */
    public void addRace(String country, String continent, int coordinateX, int coordinateY)
    {
        int id=100;
        //Adds a random id. Will be changed in the future
        Random random = new Random();
        int rand_id = random.nextInt(9999999);
        Location location = new Location(rand_id,country,continent,coordinateX,coordinateY);
        rand_id=random.nextInt(9999999);
        Race race = new Race(rand_id, location);
        raceRepository.create(race);

    }

    /**
     * Generates the calendar for the season
     * @param start_country starting country
     * @param end_country last country
     * @param day day of the first Race
     * @param month Month of the first Race
     * @param year Year of the first Race
     * @return A list of all Races in order
     */
    public List<Race> generateCalendar(String start_country, String end_country,int day, int month, int year)
    {
        //List<Race>races=repository.getAll();
        ArrayList<Race> races = new ArrayList<>(raceRepository.getAll());
        int numberOfRaces=races.size();
        List<Race> calendar=new ArrayList<>();
        Race finalRace=null;
        Race firstRace=null;
        for(Race race:races) {
            //Validate races exist

            if (race.getLocation().getCountry().equals(start_country)) {
                Date date=new Date(year,month,day);
                race.setDate(date);
                calendar.add(race);
                firstRace=race;
            }
            if(race.getLocation().getCountry().equals(end_country)) {
                finalRace=race;

            }
        }

        races.remove(firstRace);
        races.remove(finalRace);

        Race newRace=null;
        while(calendar.size()<numberOfRaces-1)
        {

            newRace=getNextRace(calendar.getLast(), races);
            Date date= setNextRaceDate(calendar.getLast(),newRace);
            newRace.setDate(date);
            calendar.add(newRace);
            races.remove(newRace);
        }

        Date date=setNextRaceDate(calendar.getLast(),finalRace);
        finalRace.setDate(date);
        calendar.add(finalRace);

        return calendar;


    }

    /**
     * Sets the next Race, and it's Date and adds it to the Calendar
     * @param lastRace
     * @param races
     * @return Race
     */
    public Race getNextRace(Race lastRace, List<Race> races)
    {
        Race nextRace=null;
        Float min_distance= 10000000F;
        int earliestRaceDay;
        int earliestRaceMonth=lastRace.getDate().getMonth();
        if(lastRace.getLocation().getContinent().equals("Europe"))
        {
            earliestRaceDay=lastRace.getDate().getDay()+7;
        }
        else
        {
            earliestRaceDay=lastRace.getDate().getDay()+14;
        }

        if(earliestRaceDay>30)
        {
            earliestRaceDay=earliestRaceDay%30;
            earliestRaceMonth++;
        }

        if(earliestRaceMonth>=4 && earliestRaceMonth<=9)
        {
            for(Race race:races)
            {
                Float distance=getDistance(lastRace.getLocation().getCoordinateX(),
                        lastRace.getLocation().getCoordinateY(),
                        race.getLocation().getCoordinateX(),race.getLocation().getCoordinateY());
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
                if(!race.getLocation().getContinent().equals("Europe"))
                {
                    Float distance = getDistance(lastRace.getLocation().getCoordinateX(),
                            lastRace.getLocation().getCoordinateY(), race.getLocation().getCoordinateX(),
                            race.getLocation().getCoordinateY());
                    if (distance < min_distance)
                    {
                        min_distance = distance;
                        nextRace = race;
                    }
                }
            }
        }
        return nextRace;

    }

    /**
     * Calculates the distance between 2 locations using the coordinates
     * @return the Distance
     */
    public float getDistance(float coordinate1_x, float coordinate1_y,
                             float coordinate2_x, float coordinate2_y)
    {
        return (float) sqrt(pow(coordinate1_x - coordinate2_x,2)+pow(coordinate1_y - coordinate2_y,2));
    }

    /**
     * Calculates the date of the next Race in the calendar
     * @param lastRace
     * @param newRace
     * @return Date of the next available Race
     */
    public Date setNextRaceDate(Race lastRace, Race newRace)
    {
        int day=lastRace.getDate().getDay();
        int month=lastRace.getDate().getMonth();
        int year=lastRace.getDate().getYear();
        if(newRace.getLocation().getContinent().equals(lastRace.getLocation().getContinent()))
        {
            day=day+7;
        }
        else
        {
            day=day+14;
        }
        if(day>30)
        {
            month++;
            day=day%30;
        }
        Date date=new Date(year, month, day);
        return date;
    }

    /**
     * Calculates the investment amounts of the TeamSponsors per Race
     * @return List of TeamSponsorRace with calculated investments in every Race
     */
    public List<TeamSponsorRace> showSponsorMoneyPerRace()
    {
        List<Team> teams = teamRepository.getAll();
        List<Sponsor> sponsors = sponsorRepository.getAll();
        List<TeamSponsor> teamSponsors = teamSponsorRepository.getAll();
        Random random=new Random();
        int rand_id=random.nextInt(9999999);
        List<TeamSponsorRace> teamSponsorRaces=new ArrayList<>();
        List<Race> races = raceRepository.getAll();
        for(Race race1: races)
        {
            for (TeamSponsor teamSponsor : teamSponsors)
            {

                if(race1.getLocation().getCountry().equals(sponsorRepository.read(teamSponsor.getSponsorId()).getCountry()))
                {
                    TeamSponsorRace teamSponsorRace = new TeamSponsorRace(rand_id, teamSponsor.getId(), race1.getId(),
                            teamSponsor.getInvestmentAmount()*2);
                    teamSponsorRaces.add(teamSponsorRace);
                }
                else
                {
                    TeamSponsorRace teamSponsorRace = new TeamSponsorRace(rand_id, teamSponsor.getId(), race1.getId(),
                            teamSponsor.getInvestmentAmount());
                    teamSponsorRaces.add(teamSponsorRace);
                }

            }
        }
        return teamSponsorRaces;
    }

    /**
     * Adds a new Sponsor to the Repository
     * @param name of the sponsor
     * @param investmentAmount of the Sponsor
     * @param country of the Sponsor
     */
    public void addSponsor(String name, int investmentAmount,String country)
    {
        Random random=new Random();
        int rand_id=random.nextInt(9999999);
        Sponsor sponsor= new Sponsor(rand_id, name, investmentAmount, country);
        sponsorRepository.create(sponsor);
    }

    /**
     * @return all the Sponsor entities
     */
    public List<Sponsor> getAllSponsors()
    {
        return sponsorRepository.getAll();
    }

    /**
     * @return all the Race entities
     */
    public List<Race> getAllRaces() {
        return raceRepository.getAll();
    }

    /**
     * @return all the TeamSponsor entities
     */
    public List<TeamSponsor> getAllTeamSponsors() {
        return teamSponsorRepository.getAll();
    }

    /**
     * Calls to add a team
     * @param teamName name of the team
     * @param budget of the team
     */
    public void addTeam(String teamName, int budget) {
        Random random=new Random();
        int rand_id=random.nextInt(9999999);
        Team team= new Team(rand_id, teamName, budget);
        teamRepository.create(team);
    }

    /**
     * @return all Team entities
     */
    public List<Team> getAllTeams() {
        return teamRepository.getAll();
    }


    /**
     * Gets Team by id
     * @param id of the Team
     * @return Team if found, else null
     */
    public Team getTeam(int id){
        return teamRepository.read(id);
    }

    /**
     * Gets TeamSponsor by id
     * @param id of the TeamSponsor
     * @return TeamSponsor if found, else null
     */
    public TeamSponsor getTeamSponsor(int id){
        return teamSponsorRepository.read(id);
    }

    /**
     * Gets Sponsor by id
     * @param id of the sponsor
     * @return Sponsor if found, else null
     */
    public Sponsor getSponsor(int id){
        return sponsorRepository.read(id);
    }

    /**
     * Gets Race by id
     * @param id of the race
     * @return race if found, else null
     */
    public Race getRace(int id){
        return raceRepository.read(id);
    }
}


