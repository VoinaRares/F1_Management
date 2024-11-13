package main.com.consoleapp.controller;

import main.com.consoleapp.service.F1AdminService;
import java.util.Map;

public class F1AdminController {

    F1AdminService f1AdminService = new F1AdminService();

    /**
     * Performs validation of data and calls addRace from the Admin Service
     * @param country country of the race
     * @param continent continent where that country is located
     * @param coordinateX the X coordinate of the circuit
     * @param coordinateY the Y coordinate of the circuit
     * @return true, if service added the race successfully, false otherwise
     */
    public boolean addRace(String country, String continent, int coordinateX, int coordinateY)
    {
        //validation
        f1AdminService.addRace(country, continent, coordinateX, coordinateY);
        return true;
    }
}
