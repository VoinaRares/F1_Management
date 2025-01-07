package main.com.consoleapp.repository;

import main.com.consoleapp.model.Entity;

import java.util.List;

public interface IRepository<T extends Entity> {

    public void create(T obj);

    public T read(int id);

    public void update(T obj);

    public void delete(int id);

    public List<T> getAll();

    public int getNextId();
}
