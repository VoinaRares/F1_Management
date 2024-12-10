package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;
import main.com.consoleapp.model.*;
import main.com.consoleapp.repository.InFileRepository;
import main.com.consoleapp.model.Exceptions.ValidationException;
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
            for(Person person : teamManagerController.getAllSortedBySalary(currentUserTeamId)){
                System.out.println(person + " Salary: " + person.getSalary());
            }
        }
        else if(choice == 2){
            for(Person person : teamManagerController.getAllSortedByAge(currentUserTeamId)){
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
            isF1Admin =false;
            currentUserTeamId = logInController.getTeamId(username, password);
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

        while(true){
            try {
                choice = validateChoice(System.console().readLine(), 12);
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
                country=System.console().readLine();
                System.out.println("Enter continent: ");
                continent=System.console().readLine();
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
                //Not sure if this is actually a good addition. Or at least it doesn't do anything
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
                endCountry=System.console().readLine();
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
                while(true){
                    try{
                        addInvestmentAmount=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
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
                while (true){
                    try{
                        budget=validateInt(System.console().readLine());
                        break;
                    }catch(ValidationException e){
                        System.out.println(e.getMessage());
                    }
                }
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
                username=System.console().readLine();
                System.out.println("Enter password: ");
                password=System.console().readLine();
                f1AdminController.addTeamManager(name,age,experience,salary,teamId,username,password);
            case 10:
                List<TeamManager> teamManagers=f1AdminController.showTeamManagers();
                for (TeamManager teamManager: teamManagers){
                    System.out.println(teamManager);
                }
            case 11:
                int id;

            case 12:
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
            name=System.console().readLine();
            System.out.println("salary: ");
            salary=validateFloat(System.console().readLine());
            System.out.println("userName: ");
            userName=System.console().readLine();
            System.out.println("password: ");
            password=System.console().readLine();
            if(choice == 1){
                String specialty;
                int yearsWithCurrentTeam = 0;
                System.out.println("specialty: ");
                specialty=System.console().readLine();
                teamManagerController.addEngineer(id, age, experience, name, salary, specialty,
                        yearsWithCurrentTeam, currentUserTeamId, userName, password);
            }
            else if(choice == 2){
                int driverNumber;
                driverNumber = readVariable("driver number: ");
                teamManagerController.addDriver(id, age, experience, name, salary, driverNumber,currentUserTeamId, userName, password);
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
        choice = validateChoice(System.console().readLine(), 8);
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
                teamManagerController.addTeamSponsor(teamSponsorId, sponsorId, currentUserTeamId, investmentAmount);
                break;
            case 4:
                int removeId = readVariable("Enter Team Sponsor ID to remove: ");
                teamManagerController.removeTeamSponsor(removeId, currentUserTeamId);
                break;
            case 5:
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
                choice2 = validateChoice(System.console().readLine(), 3);
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
//        InFileRepository<Person> repo=new InFileRepository<>("personRepo.txt");
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
        return Float.parseFloat(number);
    }

    private void removePerson(){
        int choice;
        System.out.println("1. Driver");
        System.out.println("2. Engineer");
        System.out.println("Pick your choice: ");
        choice=validateInt(System.console().readLine());
        int id;
        switch (choice){
            case 1:
                id = chooseId();
                teamManagerController.removeDriver(id, currentUserTeamId);
                break;
            case 2:
                id = chooseId();
                teamManagerController.removeEngineer(id, currentUserTeamId);
                break;
            default:
                break;

        }

    }

}
