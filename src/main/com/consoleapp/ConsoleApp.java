package main.com.consoleapp;
import main.com.consoleapp.model.Exceptions.ValidationException;
import main.com.consoleapp.presentation.Console;
public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("Choose a repository type: ");
        System.out.println("1 - In Memory");
        System.out.println("2 - In File");
        System.out.println("3 - Database");
        int choice;
        while(true) {
            try {
                choice = Integer.parseInt(System.console().readLine());
                if (choice > 0 && choice <= 3) {
                    break;
                } else {
                    throw new ValidationException("Invalid choice");
                }
            }catch (ValidationException | NumberFormatException e ) {
                System.out.println("Invalid choice");
            }

        }

        Console console = new Console(choice);
        console.run();

    }
}
