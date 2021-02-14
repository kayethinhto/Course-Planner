package ui;

import model.Course;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class CoursePlanner {
    private List<Course> courses;

    //Constructor
    public CoursePlanner() {
        courses = new ArrayList<>();
    }

    //EFFECTS: adds a new course to the course planner
    public void addCourse(Course c) {
        courses.add(c);
    }

    //REQUIRES: list of courses > 0
    //EFFECTS: returns a list of all the course names in the course planner
    public List getCourses() {
        List courseNames = new ArrayList();
        for (Course c : courses) {
            courseNames.add(c.getCourseName());
        }

        return courseNames;
    }

}