package model;
import model.Course;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CourseTest {
    Course c1;
    Task t1;
    Task t2;
    Task t3;
    ArrayList<String> actual;

    @BeforeEach
    public void setUp() {
        c1 = new Course("CPSC 210");
        this.t1 = new Task("Midterm",
                new Date(2021, 02, 24, 07, 30), 20);
        this.t2 = new Task("Midterm2",
                new Date(2021, 03, 24, 07, 30), 20);
        this.t3 = new Task("Exam", new Date(2021, 03, 24, 07, 30), 40);

        c1.addTask(t1);
        c1.addTask(t2);
        c1.addTask(t3);

        this.actual = new ArrayList<>();

        actual.add(t1.getTaskName());
        actual.add(t2.getTaskName());
        actual.add(t3.getTaskName());

    }

    @Test
    public void testAddTask() {

        assertEquals(actual, c1.getTaskNames());
    }

    @Test
    public void testRemoveTaskEmpty() {

        assertEquals(actual, c1.getTaskNames());

        actual.remove(t1.getTaskName());
        actual.remove(t2.getTaskName());
        actual.remove(t3.getTaskName());

        c1.removeTask(t1);
        c1.removeTask(t2);
        c1.removeTask(t3);

        assertEquals(actual, c1.getTaskNames());
        assertTrue(c1.getTaskNames().isEmpty());
    }

    @Test
    public void testRemoveTaskExisting() {

        assertEquals(actual, c1.getTaskNames());

        c1.removeTask(t1);
        actual.remove(t1.getTaskName());

        assertEquals(actual, c1.getTaskNames());
    }

    @Test
    public void testGetCourseName() {
        assertEquals("CPSC 210", c1.getCourseName());
    }

    @Test
    public void testGetTaskObjNull() {
        assertEquals(null, c1.getTaskObj("STAT 200"));
    }

    @Test
    public void testGetTaskObj() {
        assertEquals(t1, c1.getTaskObj("Midterm"));
    }


}
