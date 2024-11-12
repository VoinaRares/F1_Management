package main.com.consoleapp.presentation;

public class Console {

    public void show_login_menu()
    {
        System.out.println("\tF1 MANAGEMENT");
        System.out.println("1.Login");
        System.out.println("2.Sign up");
        System.out.println("3.Exit");
    }

    public void run()
    {
        while(true)
        {
            show_login_menu();
            int choice;
            choice=Integer.valueOf(System.console().readLine());
            if(choice==1)
            {
                //
            }
            if(choice==2)
            {
                //
            }
            if(choice==3)
                break;
        }
    }

}
