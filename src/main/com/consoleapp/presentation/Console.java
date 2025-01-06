package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;
import main.com.consoleapp.model.*;
import main.com.consoleapp.model.Exceptions.BusinessLogicException;
import main.com.consoleapp.model.Exceptions.DatabaseException;
import main.com.consoleapp.model.Exceptions.EntityNotFoundException;
import main.com.consoleapp.repository.InFileRepository;
import main.com.consoleapp.model.Exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class Console {

    private boolean isTeamManager = false;
    private boolean isF1Admin = false;
    private TeamManagerController teamManagerController;
    private LogInController logInController;
    private F1AdminController f1AdminController;
    private boolean isLoggedIn = false;
    private int currentUserTeamId;

    public Console(int repositoryChoice) {
        try {
            teamManagerController = new TeamManagerController(repositoryChoice);
            logInController = new LogInController(repositoryChoice);
            f1AdminController = new F1AdminController(repositoryChoice);
        }catch(DatabaseException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
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
        int choice;
        while(true){
            try{
                choice = validateChoice(System.console().readLine(), 3);
                break;
            } catch(ValidationException e){
                System.out.println(e.getMessage());
            }
        }

        if(choice == 1){
            try {
                for (Person person : teamManagerController.getAllSortedBySalary(currentUserTeamId)) {
                    System.out.println(person + " Salary: " + person.getSalary());
                }
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
            }
        }
        else if(choice == 2){
            try {
                for (Person person : teamManagerController.getAllSortedByAge(currentUserTeamId)) {
                    System.out.println(person + " Age: " + person.getAge());
                }
            }catch(DatabaseException e){
                System.out.println(e.getMessage());
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
        String personJob;
        try {
            personJob = logInController.validateCredentials(username, password);
        }catch (DatabaseException e){
            System.out.println(e.getMessage());
            personJob = "false";
        }

        if(personJob.equals("TeamManager")) {
            isTeamManager = true;
            isLoggedIn = true;
            isF1Admin =false;
            try {
                currentUserTeamId = logInController.getTeamId(username, password);
            }catch(DatabaseException e){
                currentUserTeamId = -1;
                System.out.println(e.getMessage());
            }
        }

        if(personJob.equals("F1Admin")) {
            isF1Admin = true;
            isLoggedIn = true;
            isTeamManager = false;
        }

        if(isF1Admin) {
            showF1AdminMenu();
        }
        if(isTeamManager) {
            showTeamManagerOptions();
        }
        if(personJob.equals("false"))
        {
            System.out.println("Invalid username or password");
            showLoginMenu();
        }

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
        System.out.println("9.Add Team Manager");
        System.out.println("10.Show all Team Managers");
        System.out.println("11.Delete Team Manager");
        System.out.println("12.Delete Race");
        System.out.println("13.Delete Sponsor");
        System.out.println("14.Delete Team");
        System.out.println("15.Exit");

        while(true){
            try {
                choice = validateChoice(System.console().readLine(), 15);
                break;
            }catch(ValidationException e){
                System.out.println(e.getMessage());
            }
        }
        switch(choice)
        {
            case 1:
                int coordinate_x,coordinate_y;
                String country, continent;
                System.out.println("Enter country: ");
                while(true){
                    try{
                        country=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter continent: ");
                while(true){
                    try {
                        continent=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter coordinate1: ");
                while(true){
                    try {
                        coordinate_x=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter coordinate2: ");
                while(true){
                    try{
                        coordinate_y=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    f1AdminController.addRace(country, continent, coordinate_x, coordinate_y);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                showF1AdminMenu();
                break;
            case 2:
                String startCountry, endCountry;
                int day,month,year;
                System.out.println("Enter starting country: ");
                while(true){
                    try{
                        startCountry=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter starting date of the season: ");
                System.out.println("Enter starting day: ");
                while(true){
                    try{
                        day=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter starting month: ");
                while(true){
                    try{
                        month=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter starting year: ");
                while(true){
                    try {
                        year=validateInt(System.console().readLine());
                        break;
                    } catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter ending country: ");
                while(true){
                    try{
                        endCountry=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    List<Race> calendar=f1AdminController.generateCalendar(startCountry,endCountry,day,month,year);
                    if (!calendar.isEmpty())
                    {
                        for (Race race : calendar)
                        {
                            System.out.println(race);
                        }
                    }
                    else
                    {
                        System.out.println("Can't generate calendar");
                    }
                }
                catch(BusinessLogicException e)
                {
                    System.out.println(e.getMessage());
                }

                showF1AdminMenu();
                break;
            case 3:
                try {
                    List<TeamSponsorRace> teamSponsorRaces = f1AdminController.showSponsorMoneyPerRace();

                    for (TeamSponsorRace teamSponsorRace : teamSponsorRaces) {
                        try {
                            int teamId = f1AdminController.getTeamSponsorById(teamSponsorRace.getTeamSponsorId())
                                    .getTeamId();
                            int sponsorId = f1AdminController.getTeamSponsorById(teamSponsorRace.getTeamSponsorId())
                                    .getSponsorId();
                            System.out.print(f1AdminController.getRaceById(teamSponsorRace.getRaceId()).getLocation()
                                    .getCountry() + " ");
                            System.out.print(f1AdminController.getTeamById(teamId).getTeamName() + " ");
                            System.out.print(f1AdminController.getSponsorById(sponsorId).getSponsorName() + " ");
                            System.out.println("Budget: " + teamSponsorRace.getInvestment());
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }catch (DatabaseException e){
                    System.out.println(e.getMessage());
                }

                break;

            case 4:
                String addSponsorName,sponsorCountry;

                System.out.println("Enter sponsor name: ");
                while(true){
                    try {
                        addSponsorName=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }

                System.out.println("Enter sponsor country: ");
                while(true){
                    try {
                        sponsorCountry=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    f1AdminController.addSponsor(addSponsorName, sponsorCountry);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;

            case 5:
                try {
                    List<Sponsor> getSponsors = f1AdminController.getAllSponsors();

                    for (Sponsor sponsor : getSponsors) {
                        System.out.println(sponsor);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                try {
                    List<Race> getRaces = f1AdminController.getAllRaces();
                    for (Race race : getRaces) {
                        System.out.println(race);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;

            case 7:
                String addTeamName;
                int budget;
                System.out.println("Enter team name: ");
                while(true){
                    try {
                        addTeamName=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter budget: ");
                while (true){
                    try{
                        budget=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    f1AdminController.addTeam(addTeamName, budget);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                try {
                    List<Team> getTeams = f1AdminController.getAllTeams();
                    for (Team team : getTeams) {
                        System.out.println(team);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 9:
                String name,username,password;
                int age, experience, teamId;
                float salary;
                System.out.println("Enter name: ");
                name=System.console().readLine();
                System.out.println("Enter age: ");
                while (true){
                    try{
                        age=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter experience: ");
                while (true){
                    try{
                        experience=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter salary: ");
                while (true){
                    try{
                        salary=validateFloat(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }

                System.out.println("Enter team id: ");
                while (true){
                    try{
                        teamId=validateInt(System.console().readLine());
                        break;
                    }
                    catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter username: ");

                while(true){
                    try {
                        username = validateString(System.console().readLine());
                        if(!f1AdminController.usernameIsUnique(username)){
                            throw new ValidationException("Username is already taken");
                        }
                        break;
                    } catch (ValidationException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Enter password: ");
                while (true){
                    try{
                        password=validateString(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                f1AdminController.addTeamManager(name,age,experience,salary,teamId,username,password);
                break;
            case 10:
                try {
                    List<TeamManager> teamManagers = f1AdminController.showTeamManagers();
                    for (TeamManager teamManager : teamManagers) {
                        System.out.println(teamManager);
                    }
                }catch (DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 11:
                int teamManagerId;
                System.out.println("Enter id: ");
                while (true){
                    try{
                        teamManagerId=validateId(System.console().readLine());
                        break;
                    }
                    catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    if (f1AdminController.deleteTeamManager(teamManagerId))
                        System.out.println("Entry deleted successfully");
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 12:
                int raceId;
                System.out.println("Enter id: ");
                while (true){
                    try{
                        raceId=validateInt(System.console().readLine());
                        break;
                    }
                    catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    if (f1AdminController.deleteRace(raceId))
                        System.out.println("Entry deleted successfully");
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;

            case 13:
                int sponsorId;
                System.out.println("Enter id: ");
                while (true){
                    try{
                        sponsorId=validateInt(System.console().readLine());
                        break;
                    }
                    catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    if (f1AdminController.deleteSponsor(sponsorId))
                        System.out.println("Entry deleted successfully");
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;


            case 14:
                int team_Id;
                System.out.println("Enter id: ");
                while (true)
                {
                    try{
                        team_Id=validateInt(System.console().readLine());
                        break;
                    }
                    catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    if (f1AdminController.deleteTeam(team_Id))
                        System.out.println("Entry deleted successfully");
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;

            case 15:
                isLoggedIn = false;
                isF1Admin = false;
                isTeamManager = false;
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
        while(true){
            try{
                choice=validateId(System.console().readLine());
                break;
            }catch(ValidationException e){
                System.out.println(e.getMessage());
            }
        }

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
        while(true){
            try{
                choice=validateInt(System.console().readLine());
                break;
            }catch(ValidationException e){
                System.out.println(e.getMessage());
            }
        }

        return choice;
    }

    /**
     * Menu used when a new Person is to be created.
     */
    public void chooseUserType(){
        System.out.println("Choose your User Type: ");
        System.out.println("1. Engineer");
        System.out.println("2. Driver");
        System.out.println("3. Exit");
        int choice;
        choice = validateChoice(System.console().readLine(), 3);

        int id, age, experience;
        String name, userName, password;
        float salary;

        if(choice != 3){
            id=chooseId();
            age = readVariable("age: ");
            experience = readVariable("experience: ");
            System.out.println("name: ");
            while(true){
                try {
                    name=validateString(System.console().readLine());
                    break;
                }catch(ValidationException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("salary: ");
            while(true){
                try{
                    salary=validateFloat(System.console().readLine());
                    break;
                }catch (ValidationException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("userName: ");
            while(true){
                try{
                    userName=validateString(System.console().readLine());
                    if(!teamManagerController.usernameIsUnique(userName)){
                        throw new ValidationException("Username is already taken");
                    }
                    break;
                }catch (ValidationException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("password: ");
            while(true){
                try {
                    password=validateString(System.console().readLine());
                    break;
                }catch (ValidationException e){
                    System.out.println(e.getMessage());
                }
            }
            if(choice == 1){
                String specialty;
                int yearsWithCurrentTeam = 0;
                System.out.println("specialty: ");
                while(true){
                    try{
                        specialty=validateString(System.console().readLine());
                        break;
                    }catch (ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    teamManagerController.addEngineer(id, age, experience, name, salary, specialty,
                            yearsWithCurrentTeam, currentUserTeamId, userName, password);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(choice == 2) {
                int driverNumber;
                driverNumber = readVariable("driver number: ");
                try {
                    teamManagerController.addDriver(id, age, experience, name, salary,
                            driverNumber, currentUserTeamId, userName, password);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
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
        System.out.println("8. Show all Sponsors for this Team");
        System.out.println("9. Show all Sponsors");
        System.out.println("10. Show TeamSponsors for this Team");
        System.out.println("11. Exit");
        while(true){
            try{
                choice = validateChoice(System.console().readLine(), 11);
                break;
            }catch (ValidationException e){
                System.out.println(e.getMessage());
            }
        }
        switch (choice) {
            case 1:
                chooseUserType();
                break;
            case 2:
                removePerson();
                break;
            case 3:
                int teamSponsorId = chooseId();
                int  sponsorId, investmentAmount;
                sponsorId = readVariable("Enter Sponsor ID: ");
                investmentAmount = readVariable("Enter Investment Amount: ");
                try {
                    teamManagerController.addTeamSponsor(teamSponsorId, sponsorId,
                            currentUserTeamId, investmentAmount);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                int removeId = readVariable("Enter Team Sponsor ID to remove: ");
                try {
                    teamManagerController.removeTeamSponsor(removeId, currentUserTeamId);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                try {
                    for (Person person : teamManagerController.getAllPersons()) {
                        System.out.println(person);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                showSortingOptions();
                break;
            case 7:
                showTeamManagerFilterOptions();
                break;

            case 8:
                List<Sponsor> sponsorsForTeam = teamManagerController.showTeamSponsors(currentUserTeamId);
                for(Sponsor sponsor:sponsorsForTeam)
                {
                    System.out.println(sponsor);
                }
                break;

            case 9:
                List<Sponsor> sponsors = teamManagerController.showSponsors();
                for(Sponsor sponsor:sponsors)
                {
                    System.out.println(sponsor);
                }
                break;

            case 10:
                List<TeamSponsor> teamSponsors = teamManagerController.showTeamSponsorsId(currentUserTeamId);
                for(TeamSponsor teamSponsor:teamSponsors)
                {
                    System.out.println(teamSponsor);
                }
            default:
                isTeamManager=false;
                isF1Admin=false;
                isLoggedIn=false;
                break;
        }


    }

    public void showTeamManagerFilterOptions()
    {
        int choice;

        System.out.println("1. Show All Engineers");
        System.out.println("2. Show All Drivers");
        System.out.println("3. Filter Engineers by Specialty:");
        choice=validateChoice(System.console().readLine(), 3);
        switch (choice)
        {
            case 1:
                try {
                    List<Engineer> engineers = teamManagerController.getAllEngineers();
                    for (Engineer engineer : engineers) {
                        System.out.println(engineer);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    List<Driver> drivers = teamManagerController.getAllDrivers();
                    for (Driver driver : drivers) {
                        System.out.println(driver);
                    }
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                int choice2;
                System.out.println("Choose Specialty: ");
                System.out.println("1. Aerodynamics");
                System.out.println("2. Chassis");
                System.out.println("3. Engine");
                choice2 = validateChoice(System.console().readLine(), 3);
                switch (choice2) {
                    case 1:
                        try {
                            List<Engineer> specialtyEngineersAerodynamics = teamManagerController.getEngineersBySpecialty("Aerodynamics");
                            for (Engineer engineer : specialtyEngineersAerodynamics) {
                                System.out.println(engineer);
                            }
                        } catch (DatabaseException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            List<Engineer> specialtyEngineersChassis = teamManagerController.getEngineersBySpecialty("Chassis");
                            for (Engineer engineer : specialtyEngineersChassis) {
                                System.out.println(engineer);
                            }
                        } catch (DatabaseException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            List<Engineer> specialtyEngineersEngine = teamManagerController.getEngineersBySpecialty("Engine");
                            for (Engineer engineer : specialtyEngineersEngine) {
                                System.out.println(engineer);
                            }
                        } catch (DatabaseException e) {
                            System.out.println(e.getMessage());
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
//        InFileRepository<Person> repo=new InFileRepository<>("teamManagerRepo.txt");
//        TeamManager teamManager=new TeamManager(0,"Toto Wolff",50,10,2500, 0, "1","y");
//        repo.create(teamManager);
        while(true)
        {
            if(isLoggedIn){
                if(isTeamManager){
                    showTeamManagerOptions();
                }
                if(isF1Admin){
                    showF1AdminMenu();
                }
            }
            else {
                showMenu();
                int choice;
                choice = Integer.parseInt(System.console().readLine());
                if (choice == 1) {
                    showLoginMenu();
                }
                if (choice == 2) {
                    break;
                }
            }
        }
    }

    private int validateInt(String number) {
        try{
            return Integer.parseInt(number);
        }catch (NumberFormatException e){
            throw new ValidationException("Enter a valid number");
        }
    }

    private int validateId(String id){
        int new_id;
        try{
            new_id = validateInt(id);
            if(new_id < 0){
                throw new ValidationException("Enter a valid id");
            }
            return new_id;
        }catch (ValidationException e){
            throw new ValidationException("Enter a valid id");
        }
    }

    private int validateChoice(String choice, int maxChoice){
        int choice_number;
        try{
            choice_number = validateInt(choice);
            if(choice_number > maxChoice || choice_number <= 0){
                throw new ValidationException("Enter a valid number");
            }
            return choice_number;
        }catch (ValidationException e){
            throw new ValidationException(e.getMessage());
        }
    }

    private float validateFloat(String number) {
        float res;
        try{
            res = Float.parseFloat(number);
            if(res < 0){
                throw new ValidationException("Enter a valid number");
            }
            return res;
        }catch (NumberFormatException e){
            throw new ValidationException("Enter a valid number");
        }
    }

    private String validateString(String str) {
        if(str.isBlank() || str.isEmpty()){
            throw new ValidationException("Enter a valid input");
        }
        return str;
    }

    private void removePerson(){
        int choice;
        System.out.println("1. Driver");
        System.out.println("2. Engineer");
        System.out.println("Choose: ");
        choice=validateInt(System.console().readLine());
        int id;
        switch (choice){
            case 1:
                id = chooseId();
                try {
                    teamManagerController.removeDriver(id, currentUserTeamId);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                id = chooseId();
                try {
                    teamManagerController.removeEngineer(id, currentUserTeamId);
                }catch(DatabaseException e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                break;

        }

    }

}
