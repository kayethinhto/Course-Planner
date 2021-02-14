package model;

//Represents a task with a name, due date, and grade weighting, can be assigned to a specific course
public class Task {
    private String taskName;
    private Date date;
    private int weight;
    private boolean status;

    //EFFECTS: assigns a name, due date and grade weighting to a task
    //Constructor
    public Task(String taskName, Date date, int weight) {
        this.taskName = taskName;
        this.date = date;
        this.weight = weight;
        this.status = false;

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

    //EFFECTS: returns time as properly formatted string
    public String getTaskTime() {
        return date.returnTime();
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

    public Boolean getCompletedStatus() {
        return status;
    }

    //EFFECTS: marks a task as completed by changing boolean value to true
    //MODIFIES: changed isComplete to True
    public void completeTask() {
        status = true;
    }

    //EFFECTS: returns the value of isComplete (true for completed, false for incomplete)
    public Boolean isTaskComplete() {
        return status;
    }



}
