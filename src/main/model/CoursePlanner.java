package model;

import model.Course;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class CoursePlanner {
    private List<Course> courses;

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
    //add a method get Task

}