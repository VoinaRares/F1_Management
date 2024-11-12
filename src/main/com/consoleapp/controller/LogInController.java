package main.com.consoleapp.controller;
import main.com.consoleapp.service.LogInService;
import main.com.consoleapp.service.Service;
public class LogInController {

    LogInService logInService = new LogInService();
    public String validate_credentials(String Username, String Password)
    {
        //validation of input in Console
        return logInService.logIn(Username,Password);
    }


}
