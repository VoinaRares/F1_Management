package main.com.consoleapp.repository;

import main.com.consoleapp.model.Entity;
import main.com.consoleapp.model.Exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBRepository<T extends Entity> implements IRepository<T>, AutoCloseable {
    protected final Connection connection;

    public DBRepository(String dbUrl, String dbUser, String dbPassword) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new DatabaseException("Database error");
        }
    }
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public void create(T obj) {

    }

    @Override
    public T read(int id) {
        return null;
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<T> getAll() {
        return List.of();
    }

    @Override
    public int getNextId(){
        int id = 0;
        for( Entity entity : getAll()){
            if(entity.getId() > id){
                id = entity.getId();
            }
        }
        return id + 1;

    }
}
