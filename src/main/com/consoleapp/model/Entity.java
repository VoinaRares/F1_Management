package main.com.consoleapp.model;

public abstract class Entity {
    int id;
    Entity(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
