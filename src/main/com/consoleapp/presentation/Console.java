package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;

public class Console {

    private boolean isTeamManager = false;
    private boolean isF1Admin = false;
    private TeamManagerController teamManagerController;
    private LogInController logInController;
    private F1AdminController f1AdminController;

    public Console() {
        teamManagerController = new TeamManagerController();
        logInController = new LogInController();
        f1AdminController = new F1AdminController();
    }

    public void showMenu()
    {
        System.out.println("\tF1 MANAGEMENT");
        System.out.println("1.Login");
        System.out.println("2.Sign up");
        System.out.println("3.Exit");
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
        if(personJob.equals("TeamManager"))
            isTeamManager=true;
        if(personJob.equals("F1Admin"))
            isF1Admin=true;

        if(isF1Admin)
            showF1AdminMenu();
        if(personJob.equals("false"))
        {
            System.out.println("Invalid username or password");
            showLoginMenu();
        }
        if(isTeamManager)
            showOptions();
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
            case 2:
                f1AdminController.generateCalendar();

        }


    }

    public void showSignUpMenu()
    {
        String Username, Password;
        System.out.println("SIGN UP");
        System.out.println("Enter your Username: ");
        Username=System.console().readLine();
        System.out.println("Enter your Password: ");
        Password=System.console().readLine();
        //send by controller

    }

    private int chooseUserId(){
        int choice;
        System.out.println("Enter User ID: ");
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

        int id, age, experience, teamId;
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
                System.out.println("teamId: ");
                teamId=Integer.parseInt(System.console().readLine());
                teamManagerController.addEngineer(id, age, experience, name, salary, specialty,
                        yearsWithCurrentTeam, teamId, userName, password);
            }
            else if(choice == 3){
                int driverNumber;
                System.out.println("driverNumber: ");
                driverNumber=Integer.parseInt(System.console().readLine());
                System.out.println("teamId: ");
                teamId=Integer.parseInt(System.console().readLine());
                teamManagerController.addDriver(id, age, experience, name, salary, driverNumber,teamId, userName, password);
            }
            else if(choice == 4){
                System.out.println("teamId: ");
                teamId=Integer.parseInt(System.console().readLine());
                teamManagerController.addTeamManager(id, age, experience, name, salary,teamId, userName, password);
            }
        }
    }

    public void showOptions(){
        int choice;
        while(true){
            //Options for Team Manager
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Exit");
            choice=Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    //call to controller for Add
                    chooseUserType();
                    break;
                case 2:
                    //call to controller for remove
                    int id = chooseUserId();
                    teamManagerController.removePerson(id);
                    break;
                default:
                    break;
            }
//            if(choice == 3){
//                break;
//            }
        }
    }

    public void run()
    {
        while(true)
        {
            showMenu();
            int choice;
            choice=Integer.valueOf(System.console().readLine());
            if(choice==1)
            {
                showLoginMenu();
            }
            if(choice==2)
            {
                showSignUpMenu();
            }
            if(choice==3)
                break;
        }
    }

}
