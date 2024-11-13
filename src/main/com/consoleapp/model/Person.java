package main.com.consoleapp.model;

/**
 * Base class for all People
 */
public abstract class Person extends Entity{
    private String name;
    private String username;
    private String password;
    private int age;
    private int experience;
    private float salary;


    public Person(int id,String name, int age, int experience, float salary, String username, String password) {
        super(id);
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
