package model;

import com.sun.corba.se.spi.ior.Writeable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA_2_3.portable.OutputStream;
import persistance.Writable;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//Represents a course in the course planner, each course can have specific assignments
public class Course implements Writable {
    private String courseName;
    private List<Task> tasks;
    private JFrame jframe;

    //Constructor
    public Course(String courseName) {
        this.courseName = courseName;
        tasks = new ArrayList<>();
        jframe = null;
    }

    //MODIFIES: this
    //EFFECTS: assigns a course a specific JFrame
    public void setJframe(JFrame j) {
        jframe = j;
    }

    //MODIFIES: this
    //EFFECTS: returns the JFrame
    public JFrame getJframe() {
        return jframe;
    }

    //MODIFIES: this
    //EFFECTS: adds a new task to the course
    public void addTask(String taskName, Date date, int taskWeight) { //parameter should be a string

        Task thisTask = new Task(taskName, date, taskWeight);
        tasks.add(thisTask);
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

    public List<Task> getTaskList() {
        return tasks;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course", courseName);
        json.put("tasks", tasksToJson());
        return json;
    }

    //EFFECTS: returns tasks in this course as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Task task : tasks) {
            jsonArray.put(task.toJson());
        }
        return jsonArray;
    }


}