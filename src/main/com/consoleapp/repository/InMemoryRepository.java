package main.com.consoleapp.repository;

import main.com.consoleapp.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T extends Entity> implements IRepository<T>{

    private List<T> data;

    public InMemoryRepository() {
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
}
