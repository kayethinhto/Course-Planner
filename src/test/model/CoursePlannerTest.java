package model;


//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CoursePlannerTest {
    private CoursePlanner cp;

    private Course c1;
    private Course c2;
    private Course c3;

    @BeforeEach
    public void setUp() {
        this.cp = new CoursePlanner();

        this.c1 = new Course("CPSC 210");
        this.c2 = new Course("CPSC 110");
        this.c3 = new Course("CPSC 121");

        c1.addTask("Midterm", new Date(2021, 02, 24), 20);
        c2.addTask("Assignment1", new Date(2021, 03, 24), 15);
        c3.addTask("Exam", new Date(2021, 03, 24), 40);

        cp.addCourse(c1);
        cp.addCourse(c2);
        cp.addCourse(c3);

    }

    @Test
    public void testAddCourse() {

        List actual = new ArrayList();
        actual.add(c1.getCourseName());
        actual.add(c2.getCourseName());
        actual.add(c3.getCourseName());

        assertEquals(actual, cp.getCourses());
    }

    @Test
    public void testGetCourseObj() {
        assertEquals(c1, cp.getCourseObj("CPSC 210"));
    }

    @Test
    public void testGetCourseObjNull() {
        assertEquals(null, cp.getCourseObj("STAT 200"));
    }

}
