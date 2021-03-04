package model;

import org.json.JSONObject;
import persistance.Writable;

//Represents a task with a name, due date, and grade weighting, can be assigned to a specific course
public class Task implements Writable {
    private String taskName;
    private Date date;
    private int weight;


    //EFFECTS: assigns a name, due date and grade weighting to a task
    //Constructor
    public Task(String taskName, Date date, int weight) {
        this.taskName = taskName;
        this.date = date;
        this.weight = weight;

    }

    //getters
    public String getTaskName() {
        return taskName;
    }

    public int getTaskGradeWeight() {
        return weight;
    }

    //EFFECTS: returns date as properly formatted string
    public String getTaskDueDate() {
        return date.returnDate();
    }

    public int getYear() {
        return date.getYear();
    }

    public int getMonth() {
        return date.getMonth();
    }

    public int getDay() {
        return date.getDay();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("task name", taskName);
        json.put("weight", weight);

        json.put("year", getYear());
        json.put("month", getMonth());
        json.put("day", getDay());

        return json;
    }

}
