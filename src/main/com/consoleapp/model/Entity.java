package main.com.consoleapp.model;

/**
 * Base class for all classes to include the id
 */
public abstract class Entity {
    private int id;

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
