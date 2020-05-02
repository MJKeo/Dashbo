package edu.gatech.mkeohane.dash;

public class UserInformation {
    public String id;
    public String task;
    public String time;

    public UserInformation() {

    }

    public UserInformation(String id, String task, String time) {
        this.id = id;
        this.task = task;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
