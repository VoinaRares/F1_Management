package main.com.consoleapp.model;

public class F1Admin extends Person {

    private Calendar calendar;

    public F1Admin(int id, String name, int age, int experience, float salary,String username, String password) {
        super(id, name, age, experience, salary, username, password);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void organizeRaces(Race startPoint, Race endPoint) {

    }

    public void addRace(){

    }
}
