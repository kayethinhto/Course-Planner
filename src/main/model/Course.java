package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//represents a course in the course planner, each course can have specific assignments
public class Course {
    private String courseName;
    private List<Task> tasks;

    //Constructor
    public Course(String courseName) {
        this.courseName = courseName;
        tasks = new ArrayList<>();
    }

    //EFFECTS: adds a new task to the course
    public void addTask(Task t) {
        tasks.add(t);
    }

    //REQUIRES: list of tasks > 0
    //EFFECTS: returns a list of tasks names (string) assigned to that course
    public List getTaskNames() {
        List taskList = new ArrayList();
        for (Task t : tasks) {
            taskList.add(t.getTaskName());
        }
        return taskList;
    }

    //Getter
    //EFFECTS: returns the course name
    public String getCourseName() {
        return courseName;
    }

    //MODIFIES: deletes a Task from the tasks list
    //EFFECTS: removes a given task
    public void removeTask(Task t) {
        tasks.remove(t);
    }




}