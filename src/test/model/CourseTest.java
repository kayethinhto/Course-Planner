package model;
import model.Course;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CourseTest {
    Course c1;

    /*
    Task t1;
    Task t2;
    Task t3;
    ArrayList<Task> actual;
     */

    @BeforeEach
    public void setUp() {
        /*
        c1 = new Course("CPSC 210");

        t1 = new Task("Midterm",
                new Date(2021, 02, 24), 20);
        t2 = new Task("Midterm2",
                new Date(2021, 03, 24), 20);
        t3 = new Task("Exam",
                new Date(2021, 03, 24), 40);

        c1.addTask("Midterm",
                new Date(2021, 02, 24), 20 );
        c1.addTask("Midterm2",
                new Date(2021, 03, 24), 20);
        c1.addTask("Exam",
                new Date(2021, 03, 24), 40);

        this.actual = new ArrayList<>();

        actual.add(t1);
        actual.add(t2);
        actual.add(t3);

         */

        c1 = new Course("CPSC 210");
    }

    @Test
    public void testAddTask() {
        c1.addTask("Midterm",
                new Date(2021, 02, 24), 20);

        assertEquals("Midterm", c1.getTaskNames().get(0));
    }

    @Test
    public void testRemoveTaskNoLeftover() {

        c1.addTask("Midterm",
                new Date(2021, 02, 24), 20);

        assertEquals("Midterm", c1.getTaskNames().get(0));

        c1.removeTask(c1.getTaskObj("Midterm"));

        assertTrue(c1.getTaskNames().isEmpty());
    }

    @Test
    public void testRemoveTaskLeftover() {

        c1.addTask("Midterm",
                new Date(2021, 02, 24), 20);

        c1.addTask("Midterm2",
                new Date(2021, 03, 24), 20);

        assertEquals("Midterm", c1.getTaskNames().get(0));
        assertEquals("Midterm2", c1.getTaskNames().get(1));

        c1.removeTask(c1.getTaskObj("Midterm"));

        assertFalse(c1.getTaskNames().isEmpty());
        assertEquals("Midterm2", c1.getTaskNames().get(0));
    }

    @Test
    public void testGetCourseName() {
        assertEquals("CPSC 210", c1.getCourseName());
    }

    @Test
    public void testGetTaskObjNull() {
        c1.addTask("Midterm2",
                new Date(2021, 03, 24), 20);
        c1.addTask("Final",
                new Date(2021, 04, 22), 40);

        assertEquals(null, c1.getTaskObj("STAT 200"));
    }

    @Test
    public void testGetTaskObj() {

        c1.addTask("Midterm",
                new Date(2021, 03, 24), 40);

        assertEquals("Midterm", c1.getTaskObj("Midterm").getTaskName());
    }

}
