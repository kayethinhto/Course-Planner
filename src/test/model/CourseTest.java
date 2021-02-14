package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourseTest {
    private Course c1;
    private Task t1;
    private Task t2;
    private Task t3;

    @Before
    public void setUp() {

        this.c1 = new Course("CPSC 210");
        this.t1 = new Task("Midterm",
                new Date(2021, 02, 24, 07, 30), 20);
        this.t2 = new Task("Midterm2",
                new Date(2021, 03, 24, 07, 30), 20);
        this.t3 = new Task("Exam", new Date(2021, 03, 24, 07, 30), 40);

        c1.addTask(t1);
        c1.addTask(t2);
        c1.addTask(t3);

    }

    @Test
    public void testAddTask() {

        List actual = new ArrayList();
        actual.add(t1.getTaskName());
        actual.add(t2.getTaskName());
        actual.add(t3.getTaskName());

        assertEquals(actual, c1.getTaskNames());
    }

    @Test
    public void testRemoveTaskEmpty() {
        List initial = new ArrayList();
        initial.add(t1.getTaskName());
        initial.add(t2.getTaskName());
        initial.add(t3.getTaskName());

        assertEquals(initial, c1.getTaskNames());

        c1.removeTask(t1);
        c1.removeTask(t2);
        c1.removeTask(t3);

        List actual = new ArrayList();

        assertEquals(actual, c1.getTaskNames());
        assertTrue(c1.getTaskNames().isEmpty());
    }

    @Test
    public void testRemoveTaskExisting() {
        List initial = new ArrayList();
        initial.add(t1.getTaskName());
        initial.add(t2.getTaskName());
        initial.add(t3.getTaskName());

        assertEquals(initial, c1.getTaskNames());

        c1.removeTask(t1);

        List actual = new ArrayList();
        actual.add(t2.getTaskName());
        actual.add(t3.getTaskName());

        assertEquals(actual, c1.getTaskNames());
    }

}
