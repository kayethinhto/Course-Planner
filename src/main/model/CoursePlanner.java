package model;

import model.Course;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

public class CoursePlanner implements Writable {
    private List<Course> courses;
    private String coursePlannerName;

    //Constructor
    public CoursePlanner() {
        courses = new ArrayList<Course>();
    }

    //MODIFIES: this
    //EFFECTS: adds a new course object to the course planner
    public void addCourse(Course c) {
        courses.add(c);
    }

    //REQUIRES: list of courses > 0
    //EFFECTS: returns a list of all the course names in the course planner
    public List<String> getCourses() {
        List<String> courseNames = new ArrayList();
        for (Course c : courses) {
            courseNames.add(c.getCourseName());
        }
        return courseNames;
    }

    //EFFECTS: returns a course
    public Course getCourseObj(String name) {
        for (Course c : courses) {
            if (c.getCourseName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: sets the name of the course planner
    public void setCoursePlannerName(String name) {
        coursePlannerName = name;
    }

    public String getCoursePlannerName() {
        return coursePlannerName;
    }

    //EFFECTS: returns CoursePlanner as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", coursePlannerName);
        json.put("courses", listToJson());
        return json;
    }

    //EFFECTS: creates an array for each course
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Course course: courses) {
            jsonArray.put(course.toJson());
        }
        return jsonArray;
    }


}