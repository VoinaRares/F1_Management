package main.com.consoleapp.model;

/**
 * Base class for all classes to include the id
 */
public abstract class Entity {
    private int id;

    public Entity(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Id: " + id;
    }
}
