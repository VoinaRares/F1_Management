package main.com.consoleapp.repository;

import main.com.consoleapp.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T extends Entity> implements IRepository<T>{

    private List<T> data;

    /**
     * Represents a Map that stores the class and then an instance of InMemoryRepo.
     * Used to keep the same instance over all Services and Controllers throughout the App
     */
    private static final Map<Class<?>, InMemoryRepository<?>> instances = new HashMap<>();

    private InMemoryRepository() {
        data = new ArrayList<T>();
    }

    @Override
    public void create(T obj) {
        data.add(obj);
    }

    @Override
    public T read(int id) {
        for (T obj : data) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    @Override
    public void update(T obj) {
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getId() == obj.getId()){
                data.set(i,obj);
            }
        }
    }

    @Override
    public void delete(int id) {
        for(T obj : data){
            if(obj.getId() == id){
                data.remove(obj);
            }
        }
        //data.removeIf(obj -> obj.getId() == id);
        //Might be able to do the same thing using remove and the equals operator inside the class.
    }

    @Override
    public List<T> getAll() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * Used for keeping the same instance of InMemoryRepo
     * @param type data type of the Repo
     * @return instance of InMemoryRepo that uses type parameter
     * @param <T> class that inherits Entity
     * @see Entity
     */
    @SuppressWarnings("unchecked")
    public static synchronized <T extends Entity> InMemoryRepository<T> getInstance(Class<T> type) {

        //Checks if an instance of this type of Class exists
        if (!instances.containsKey(type)) {
            instances.put(type, new InMemoryRepository<>());
        }

        return (InMemoryRepository<T>) instances.get(type);
    }
}
