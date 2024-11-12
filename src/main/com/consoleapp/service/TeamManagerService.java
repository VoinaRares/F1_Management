package main.com.consoleapp.service;

import main.com.consoleapp.model.Person;
import main.com.consoleapp.repository.InMemoryRepository;

public class TeamManagerService {

    private InMemoryRepository<Person> personRepo;

    public boolean addMember(){
        return true;
    }

}
