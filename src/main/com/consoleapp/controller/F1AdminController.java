package main.com.consoleapp.controller;

import main.com.consoleapp.service.F1AdminService;
import java.util.Map;

public class F1AdminController {

    F1AdminService f1AdminService = new F1AdminService();
    public boolean addRace(String country, String continent, int coordinate_x, int coordinate_y)
    {
        //validation
        f1AdminService.addRace(country, continent, coordinate_x, coordinate_y);
        return true;
    }
}
