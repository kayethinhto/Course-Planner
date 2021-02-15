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

    //MODIFIES: this
    //EFFECTS: adds a new task to the course
    public void addTask(Task t) {
        tasks.add(t);
    }

    //REQUIRES: list of tasks > 0
    //EFFECTS: returns a list of tasks names (string) assigned to that course
    public List<String> getTaskNames() {
        List<String> taskList = new ArrayList<>();
        for (Task t : tasks) {
            taskList.add(t.getTaskName());
        }
        return taskList;
    }

    //EFFECTS: returns the course name
    public String getCourseName() {
        return courseName;
    }

    //MODIFIES: this
    //EFFECTS: removes a given task from the task list
    public void removeTask(Task t) {
        tasks.remove(t);
    }

    //EFFECTS: returns the task object corresponding to it's name
    public Task getTaskObj(String name) {
        for (Task t : tasks) {
            if (t.getTaskName().equals(name)) {
                return t;
            }
        }
        return null;
    }

}