package model;

import org.junit.Before;
import org.junit.Test;
import ui.CoursePlanner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CoursePlannerTest {
    private CoursePlanner cp;

    private Course c1;
    private Course c2;
    private Course c3;
    private Task t1;
    private Task t2;
    private Task t3;

    @Before
    public void setUp() {
        this.cp = new CoursePlanner();

        this.c1 = new Course("CPSC 210");
        this.c2 = new Course("CPSC 110");
        this.c3 = new Course("CPSC 121");

        this.t1 = new Task("Midterm", new Date(2021, 02, 24, 07, 30), 20);
        this.t2 = new Task("Assignment1", new Date(2021, 03, 24, 07, 30),
                10);
        this.t3 = new Task("Exam", new Date(2021, 03, 24, 07, 30), 40);

        c1.addTask(t1);
        c2.addTask(t2);
        c3.addTask(t3);

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

}
