package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;
import main.com.consoleapp.controller.F1AdminController;

import java.util.HashMap;
import java.util.Map;

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
    public void show_menu()
    {
        System.out.println("\tF1 MANAGEMENT");
        System.out.println("1.Login");
        System.out.println("2.Sign up");
        System.out.println("3.Exit");
    }

    public void show_login_menu()
    {
        isTeamManager = false;
        isF1Admin = false;
        String Username, Password;
        System.out.println("LOGIN");
        System.out.println("Enter your Username: ");
        Username=System.console().readLine();
        System.out.println("Enter your Password: ");
        Password=System.console().readLine();
        String person_job=logInController.validate_credentials(Username, Password);
        if(person_job.equals("TeamManager"))
            isTeamManager=true;
        if(person_job.equals("F1Admin"))
            isF1Admin=true;

        if(isF1Admin)
            show_F1_Admin_menu();
        if(person_job.equals("false"))
        {
            System.out.println("Invalid username or password");
            show_login_menu();
        }
        if(isTeamManager)
            showOptions();
    }


    public void show_F1_Admin_menu()
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
                boolean x=f1AdminController.addRace(country,continent,coordinate_x,coordinate_y);
        }

    }

    public void show_sign_up_menu()
    {
        String Username, Password;
        System.out.println("SIGN UP");
        System.out.println("Enter your Username: ");
        Username=System.console().readLine();
        System.out.println("Enter your Password: ");
        Password=System.console().readLine();
        //send by controller

    }



    public void chooseUserType(){
        System.out.println("Choose your User Type: ");
        System.out.println("1. F1 Admin");
        System.out.println("2. Engineer");
        System.out.println("3. Team Admin");
        System.out.println("4. Driver");
        System.out.println("5. Team Manager");
        System.out.println("6. Exit");
        int choice;
        choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                // F1 Admin
                teamManagerController.addF1Admin();
                break;
            case 2:
                //Engineer
                teamManagerController.addEngineer();
                break;
            case 3:
                //Team Admin
                teamManagerController.addTeamAdmin();
                break;
            case 4:
                //Driver
                teamManagerController.addDriver();
                break;
            case 5:
                //Team Manager
                teamManagerController.addTeamManager();
                break;
            default:
                break;
        }

    }

    public void showOptions(){
        int choice;
        while(true){
            //Options for Team Manager
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Swap Members");
            System.out.println("4. Exit");
            choice=Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    //call to controller for Add
                    chooseUserType();
                    break;
                case 2:
                    //call to controller for remove
                    break;
                case 3:
                    //call to controller for swap
                    break;
                default:
                    break;
            }
//            if(choice == 4){
//                break;
//            }
        }
    }

    public void run()
    {
        while(true)
        {
            show_menu();
            int choice;
            choice=Integer.valueOf(System.console().readLine());
            if(choice==1)
            {
                show_login_menu();
            }
            if(choice==2)
            {
                show_sign_up_menu();
            }
            if(choice==3)
                break;
        }
    }

}
