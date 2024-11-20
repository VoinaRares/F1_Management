package main.com.consoleapp.controller;
import main.com.consoleapp.service.LogInService;

public class LogInController {

    LogInService logInService = new LogInService();

    /**
     * Validates the data for Login
     * @param username user-entered username
     * @param password user-entered password
     * @return class name that user is part of, if combination of username and password exist, 'false' otherwise
     */
    public String validateCredentials(String username, String password)
    {
        //validation of input in Console
        return logInService.logIn(username,password);
    }

    /**
     * Gets the team id of the representing user
     * @param username of the user
     * @param password of the user
     * @return team id, if user has one, else -1 or if user does not exist.
     */
    public int getTeamId(String username, String password){
        return logInService.findTeamId(username,password);
    }


}
