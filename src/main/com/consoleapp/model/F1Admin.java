package main.com.consoleapp.model;

public class F1Admin extends Person {

    private Calendar calendar;

    public F1Admin(int id, String name, int age, int experience, float salary, Calendar calendar) {
        super(id, name, age, experience, salary);
        this.calendar = calendar;
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
