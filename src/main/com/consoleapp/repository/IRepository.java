package main.com.consoleapp.repository;

import java.util.List;

public interface IRepository<T> {

    public void create();

    public T read(int id);

    public void update();

    public void delete(int id);

    public List<T> getAll();
}
