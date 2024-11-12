package main.com.consoleapp.presentation;

public class Console {

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
        //send by controller

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
