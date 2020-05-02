package edu.gatech.mkeohane.dash;

public class ReminderInfo {
    public String name;
    public String id;

    public ReminderInfo() {

    }

    public ReminderInfo(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}
