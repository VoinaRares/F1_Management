package main.com.consoleapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.com.consoleapp.model.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InFileRepository<T extends Entity> implements IRepository<T> {

    private final String filePath;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Represents a Map that stores the class and then an instance of InMemoryRepo.
     * Used to keep the same instance over all Services and Controllers throughout the App
     */
    private static final Map<Class<?>, InFileRepository<?>> instances = new HashMap<>();

    /***
     * Sets the filepath for the repository
     * @param filePath to the file that the data should be stored in
     */
    public InFileRepository(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Adds an object to the file
     * @param obj to be added
     */
    @Override
    public void create(T obj) {
        List<T> items = getAll();
        items.add(obj);
        try {
            mapper.writeValue(new File(filePath), items);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches for an object in the repository
     * @param id of the searched object
     * @return the found object, or null if there is no object found
     */
    @Override
    public T read(int id) {
        List<T> items = getAll();
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    /**
     * Updates the data of an object
     * @param obj the new obj that will be saved
     */
    @Override
    public void update(T obj) {
        List<T> items = getAll();
        for (T item : items) {
            if (item.getId() == obj.getId()) {
                items.set(items.indexOf(obj), obj);
            }
        }
        try {
            mapper.writeValue(new File(filePath), items);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes data from the repository
     * @param id of the object to be removed
     */
    @Override
    public void delete(int id) {
        List<T> items = getAll();
        for (T item : items) {
            if (item.getId() == id) {
                items.remove(item);
            }
        }
        try {
            mapper.writeValue(new File(filePath), items);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return All the data found in the repository
     */
    @Override
    public List<T> getAll() {
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<T>>() {});
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ArrayList<T>();
        }
    }

    /**
     * Used for keeping the same instance of InMemoryRepo
     * @param type data type of the Repo
     * @return instance of InMemoryRepo that uses type parameter
     * @param <T> class that inherits Entity
     * @see Entity
     */
    @SuppressWarnings("unchecked")
    public static synchronized <T extends Entity> InFileRepository<T> getInstance(Class<T> type, String filePath) {

        //Checks if an instance of this type of Class exists
        if (!instances.containsKey(type)) {
            instances.put(type, new InFileRepository<>(filePath));
        }
        return (InFileRepository<T>) instances.get(type);
    }
}
