package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;
import main.com.consoleapp.model.Person;
import main.com.consoleapp.model.Race;

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

    public void showMenu()
    {
        System.out.println("\tF1 MANAGEMENT");
        System.out.println("1.Login");
        System.out.println("2.Exit");
    }

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


    public void showF1AdminMenu()
    {
        int choice;
        System.out.println("You are logged in as a F1 Admin");
        System.out.println("Options:");
        System.out.println("1.Add Race");
        System.out.println("2.Generate Calendar");
        System.out.println("3.Exit");
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
            default:
                break;
        }


    }


    private int chooseId(){
        int choice;
        System.out.println("Enter ID: ");
        choice = Integer.parseInt(System.console().readLine());

        return choice;
    }

    private int readVariable(String message){
        int choice;
        System.out.println(message);
        choice = Integer.parseInt(System.console().readLine());

        return choice;
    }



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
            case 6:
                break;
            case 7:
                showTeamManagerFilterOptions();
                break;
            default:
                break;
        }
//            if(choice == 3){
//                break;
//            }

    }

    public void showTeamManagerFilterOptions()
    {
        int choice;
        System.out.println("1. Show All Engineers");
        System.out.println("2. Show All Drivers");
        choice=Integer.parseInt(System.console().readLine());
        switch (choice)
        {
            case 1:
                //teamManagerController.showAllEngineers
                break;
            case 2:
                //teamManagerController.showAllDrivers
                break;
        }
    }

    public void run()
    {
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
