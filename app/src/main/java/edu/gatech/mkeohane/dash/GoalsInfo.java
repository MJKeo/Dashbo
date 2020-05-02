package edu.gatech.mkeohane.dash;

public class GoalsInfo {
    public String goal;
    public String id;

    public GoalsInfo(){

    }

    public GoalsInfo(String goal, String id){
        this.goal = goal;
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}
}
