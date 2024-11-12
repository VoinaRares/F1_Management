package main.com.consoleapp.presentation;
import main.com.consoleapp.controller.LogInController;
import main.com.consoleapp.controller.TeamManagerController;

public class Console {
    private boolean isTeamManager = false;
    private boolean isF1Admin = false;
    private TeamManagerController teamManagerController;
    private LogInController logInController;

    public Console() {
        teamManagerController = new TeamManagerController();
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

        showOptions();
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
                break;
            case 3:
                //Team Admin
                break;
            case 4:
                //Driver
                break;
            case 5:
                //Team Manager
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
