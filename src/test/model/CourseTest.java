package model;
import model.Course;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CourseTest {
    Course c1;

    @BeforeEach
    public void setUp() {

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

    @Test
    public void testGetAndSetJFrame() {
        JFrame jframe = new JFrame("new frame");
        JFrame j = c1.getJframe();
        assertEquals(j, null);
        c1.setJframe(jframe);
        assertEquals(c1.getJframe(), jframe);
    }

    @Test
    public void testGetTaskList() {
        List<Task> tasks = new ArrayList<>();
        Task t1 = new Task("Midterm2",
                new Date(2021, 03, 24), 20);
        tasks.add(t1);
        c1.addTask("Midterm2",
                new Date(2021, 03, 24), 20);

        assertEquals(c1.getTaskList().get(0).getTaskName(), tasks.get(0).getTaskName());
    }


}
