package main.com.consoleapp.repository;

import main.com.consoleapp.model.Entity;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class InFileRepository<T extends Entity> implements IRepository<T> {

    private final String filePath;

    /**
     * Represents a Map that stores the class and then an instance of InMemoryRepo.
     * Used to keep the same instance over all Services and Controllers throughout the App
     */
    private static final Map<Class<?>, InFileRepository<?>> instances = new HashMap<>();

    public InFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void create(T obj) {
        doInFile(data -> data.putIfAbsent(obj.getId(), obj));
    }

    @Override
    public T read(int id) {
        return readDataFromFile().get(id);
    }

    @Override
    public void update(T obj) {
        doInFile(data -> data.replace(obj.getId(), obj));
    }

    @Override
    public void delete(int id) {
        doInFile(data -> data.remove(id));
    }

    @Override
    public List<T> getAll() {
        return readDataFromFile().values().stream().toList();
    }


    private void doInFile(Consumer<Map<Integer, T>> function) {
        Map<Integer, T> data = readDataFromFile();
        function.accept(data);
        writeDataToFile(data);
    }

    private Map<Integer, T> readDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Map<Integer, T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }


    private void writeDataToFile(Map<Integer, T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
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
