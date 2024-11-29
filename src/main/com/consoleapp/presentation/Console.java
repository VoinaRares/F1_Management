package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.InFileRepository;

import java.util.List;

public class Console {

    private boolean isTeamManager = false;
    private boolean isF1Admin = false;
    private final TeamManagerController teamManagerController;
    private final LogInController logInController;
    private final F1AdminController f1AdminController;
    private boolean isLoggedIn = false;
    private int currentUserTeamId;

    public Console() {
        teamManagerController = new TeamManagerController();
        logInController = new LogInController();
        f1AdminController = new F1AdminController();
    }

    /**
     * Shows the menu of a not logged in person
     */
    public void showMenu()
    {
        System.out.println("\tF1 MANAGEMENT");
        System.out.println("1.Login");
        System.out.println("2.Exit");
    }


    private void showSortingOptions(){
        System.out.println("Sorting Options");
        System.out.println("1. Sort by salary");
        System.out.println("2. Sort by age");
        System.out.println("3. Exit");
        int choice = Integer.parseInt(System.console().readLine());
        if(choice == 1){
            for(Person person : teamManagerController.getAllSortedBySalary()){
                System.out.println(person + " Salary: " + person.getSalary());
            }
        }
        else if(choice == 2){
            for(Person person : teamManagerController.getAllSortedByAge()){
                System.out.println(person + " Age: " + person.getAge());
            }
        }
    }

    /**
     * Shows the logInMenu and sets up parameters of currentUserTeamId, isLoggedIn, and variables depending on the job
     * they have
     */
    public void showLoginMenu()
    {
        isTeamManager = false;
        isF1Admin = false;
        String username, password;
        System.out.println("LOGIN");
        System.out.println("Enter your Username: ");
        username=System.console().readLine();
        System.out.println("Enter your Password: ");
        password=System.console().readLine();
        String personJob=logInController.validateCredentials(username, password);

        if(personJob.equals("TeamManager")) {
            isTeamManager = true;
            isLoggedIn = true;
            currentUserTeamId = logInController.getTeamId(username, password);
        }

        if(personJob.equals("F1Admin")) {
            isF1Admin = true;
            isLoggedIn = true;
        }

        if(personJob.equals("Driver")) {
            isLoggedIn = true;
            currentUserTeamId = logInController.getTeamId(username, password);
        }

        if(personJob.equals("Engineer")) {
            isLoggedIn = true;
            currentUserTeamId = logInController.getTeamId(username, password);
        }

        if(isF1Admin)
            showF1AdminMenu();
        if(personJob.equals("false"))
        {
            System.out.println("Invalid username or password");
            showLoginMenu();
        }
        if(isTeamManager)
            showTeamManagerOptions();
    }


    /**
     * Menu shown if the User is an F1 Admin
     */
    public void showF1AdminMenu()
    {
        int choice;
        System.out.println("You are logged in as a F1 Admin");
        System.out.println("Options:");
        System.out.println("1.Add Race");
        System.out.println("2.Generate Calendar");
        System.out.println("3.Show Sponsor investments in races");
        System.out.println("4.Add Sponsor");
        System.out.println("5.Show all Sponsors");
        System.out.println("6.Show all Races");
        System.out.println("7.Add Team");
        System.out.println("8.Show all Teams");
        System.out.println("9.Exit");
        choice=Integer.parseInt(System.console().readLine());
        switch(choice)
        {
            case 1:
                int coordinate_x,coordinate_y;
                String country, continent;
                System.out.println("Enter country: ");
                country=System.console().readLine();
                System.out.println("Enter continent: ");
                continent=System.console().readLine();
                System.out.println("Enter coordinate1: ");
                coordinate_x= Integer.parseInt(System.console().readLine());
                System.out.println("Enter coordinate2: ");
                coordinate_y=Integer.parseInt(System.console().readLine());
                boolean added=f1AdminController.addRace(country,continent,coordinate_x,coordinate_y);
                if(added)
                {
                    System.out.println("Race added successfully");
                    showF1AdminMenu();
                }
                break;
            case 2:
                String startCountry, endCountry;
                int day,month,year;
                System.out.println("Enter starting country: ");
                startCountry=System.console().readLine();
                System.out.println("Enter starting date of the season: ");
                System.out.println("Enter starting day: ");
                day=Integer.parseInt(System.console().readLine());
                System.out.println("Enter starting month: ");
                month=Integer.parseInt(System.console().readLine());
                System.out.println("Enter starting year: ");
                year=Integer.parseInt(System.console().readLine());
                System.out.println("Enter ending country: ");
                endCountry=System.console().readLine();
                List<Race> calendar=f1AdminController.generateCalendar(startCountry,endCountry,day,month,year);
                for(Race race:calendar)
                {
                    System.out.println(race);
                }
                showF1AdminMenu();
                break;
            case 3:
                List<TeamSponsorRace> teamSponsorRaces= f1AdminController.showSponsorMoneyPerRace();

                for(TeamSponsorRace teamSponsorRace: teamSponsorRaces){
                    int teamId = f1AdminController.getTeamSponsorById(teamSponsorRace.getTeamSponsorId()).getTeamId();
                    int sponsorId = f1AdminController.getTeamSponsorById(teamSponsorRace.getTeamSponsorId()).getSponsorId();
                    System.out.print(f1AdminController.getRaceById(teamSponsorRace.getRaceId()).getLocation().getCountry() + " ");
                    System.out.print(f1AdminController.getTeamById(teamId).getTeamName() + " ");
                    System.out.print(f1AdminController.getSponsorById(sponsorId).getSponsorName() + " ");
                    System.out.println("Budget: " + teamSponsorRace.getInvestment());
                }

                break;

            case 4:
                String addSponsorName,sponsorCountry;
                int addInvestmentAmount;
                System.out.println("Enter sponsor name: ");
                addSponsorName =System.console().readLine();
                System.out.println("Enter sponsor investment amount: ");
                addInvestmentAmount =Integer.parseInt(System.console().readLine());
                System.out.println("Enter sponsor country: ");
                sponsorCountry=System.console().readLine();
                f1AdminController.addSponsor(addSponsorName, addInvestmentAmount,sponsorCountry);
                break;

            case 5:
                List<Sponsor> getSponsors =f1AdminController.getAllSponsors();
                for(Sponsor sponsor: getSponsors)
                {
                    System.out.println(sponsor);
                }
                break;
            case 6:
                List<Race> getRaces =f1AdminController.getAllRaces();
                for(Race race: getRaces)
                {
                    System.out.println(race);
                }
                break;

            case 7:
                String addTeamName;
                int budget;
                System.out.println("Enter team name: ");
                addTeamName =System.console().readLine();
                System.out.println("Enter budget: ");
                budget=Integer.parseInt(System.console().readLine());
                f1AdminController.addTeam(addTeamName,budget);
                break;
            case 8:
                List<Team> getTeams =f1AdminController.getAllTeams();
                for(Team team: getTeams)
                {
                    System.out.println(team);
                }
                break;


            case 9:
                break;
        }


    }

    /**
     * Helper function to standardize getting the id from the console
     * @return the user-chosen id
     */
    private int chooseId(){
        int choice;
        System.out.println("Enter ID: ");
        choice = Integer.parseInt(System.console().readLine());

        return choice;
    }

    /**
     * Standardizes the reading of a numerical variable
     * @param message the message that will be displayed before reading the variable
     * @return the user-chosen variable value
     */
    private int readVariable(String message){
        int choice;
        System.out.println(message);
        choice = Integer.parseInt(System.console().readLine());

        return choice;
    }

    /**
     * Menu used when a new Person is to be created.
     */
    public void chooseUserType(){
        System.out.println("Choose your User Type: ");
        System.out.println("1. F1 Admin");
        System.out.println("2. Engineer");
        System.out.println("3. Driver");
        System.out.println("4. Team Manager");
        System.out.println("5. Exit");
        int choice;
        choice = Integer.parseInt(System.console().readLine());

        int id, age, experience;
        String name, userName, password;
        float salary;

        if(choice != 5){
            System.out.println("id: ");
            id=Integer.parseInt(System.console().readLine());
            System.out.println("age: ");
            age=Integer.parseInt(System.console().readLine());
            System.out.println("experience: ");
            experience=Integer.parseInt(System.console().readLine());
            System.out.println("name: ");
            name=System.console().readLine();
            System.out.println("salary: ");
            salary=Float.parseFloat(System.console().readLine());
            System.out.println("userName: ");
            userName=System.console().readLine();
            System.out.println("password: ");
            password=System.console().readLine();
            if(choice == 1){
                teamManagerController.addF1Admin(id, age, experience, name, salary, userName, password);
            }
            else if(choice == 2){
                String specialty;
                int yearsWithCurrentTeam = 0;
                System.out.println("specialty: ");
                specialty=System.console().readLine();
                teamManagerController.addEngineer(id, age, experience, name, salary, specialty,
                        yearsWithCurrentTeam, currentUserTeamId, userName, password);
            }
            else if(choice == 3){
                int driverNumber;
                System.out.println("driverNumber: ");
                driverNumber=Integer.parseInt(System.console().readLine());
                teamManagerController.addDriver(id, age, experience, name, salary, driverNumber,currentUserTeamId, userName, password);
            }
            else if(choice == 4){
                teamManagerController.addTeamManager(id, age, experience, name, salary,currentUserTeamId, userName, password);
            }
        }
    }

    /**
     * Presents all the Options of a Team Manager
     */
    public void showTeamManagerOptions(){
        int choice;

        //Options for Team Manager
        System.out.println("1. Add Member");
        System.out.println("2. Remove Member");
        System.out.println("3. Add Team Sponsor");
        System.out.println("4. Remove Team Sponsor");
        System.out.println("5. Show all Members");
        System.out.println("6. Sorting Operations");
        System.out.println("7. Filter Operations");
        System.out.println("8. Exit");
        choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                //call to controller for Add
                chooseUserType();
                break;
            case 2:
                //call to controller for remove
                int id = chooseId();
                teamManagerController.removePerson(id);
                break;
            case 3:
                int teamSponsorId = chooseId();
                int teamId, sponsorId, investmentAmount;
                teamId = readVariable("Enter Team ID: ");
                sponsorId = readVariable("Enter Sponsor ID: ");
                investmentAmount = readVariable("Enter Investment Amount: ");
                teamManagerController.addTeamSponsor(teamSponsorId, sponsorId, teamId, investmentAmount);
                break;
            case 4:
                int removeId = readVariable("Enter Team Sponsor ID to remove: ");
                teamManagerController.removeTeamSponsor(removeId);
                break;
            case 5:
                //Might be a good idea to receive a List of Strings to not depend on the Person class
                for(Person person : teamManagerController.getAllPersons()){
                    System.out.println(person);
                }
                break;
            case 6:
                showSortingOptions();
                break;
            case 7:
                showTeamManagerFilterOptions();
                break;
            default:
                break;
        }


    }

    public void showTeamManagerFilterOptions()
    {
        int choice;

        System.out.println("1. Show All Engineers");
        System.out.println("2. Show All Drivers");
        System.out.println("3. Filter Engineers by Specialty:");
        choice=Integer.parseInt(System.console().readLine());
        switch (choice)
        {
            case 1:
                List<Engineer> engineers= teamManagerController.getAllEngineers();
                for(Engineer engineer : engineers){
                    System.out.println(engineer);
                }
                break;
            case 2:
                List<Driver> drivers= teamManagerController.getAllDrivers();
                for(Driver driver : drivers){
                    System.out.println(driver);
                }
                break;
            case 3:
                int choice2;
                System.out.println("Choose Specialty: ");
                System.out.println("1. Aerodynamics");
                System.out.println("2. Chassis");
                System.out.println("3. Engine");
                choice2 = Integer.parseInt(System.console().readLine());
                switch (choice2){
                    case 1:
                        List<Engineer> specialtyEngineersAerodynamics = teamManagerController.getEngineersBySpecialty("Aerodynamics");
                        for(Engineer engineer : specialtyEngineersAerodynamics){
                            System.out.println(engineer);
                        }
                        break;
                    case 2:
                        List<Engineer> specialtyEngineersChassis = teamManagerController.getEngineersBySpecialty("Chassis");
                        for(Engineer engineer : specialtyEngineersChassis){
                            System.out.println(engineer);
                        }
                        break;
                    case 3:
                        List<Engineer> specialtyEngineersEngine= teamManagerController.getEngineersBySpecialty("Engine");
                        for(Engineer engineer : specialtyEngineersEngine){
                            System.out.println(engineer);
                        }
                        break;
                }

                break;
        }
    }
  
   /**
    * Calls the Menu and reads the input
    */
    public void run()
    {
//        InFileRepository<Person> repo=new InFileRepository<>("personRepo.json");
//        TeamManager teamManager=new TeamManager(0,"Toto Wolff",50,10,2500, 0, "1","y");
//        repo.create(teamManager);
        while(true)
        {
            showMenu();
            int choice;
            choice=Integer.parseInt(System.console().readLine());
            if(choice==1)
            {
                showLoginMenu();
            }
            if(choice==2)
                break;
        }
    }

}
