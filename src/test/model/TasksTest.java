package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    private Task task;

    @Before
    public void setUp() {
        task = new Task("Midterm", new Date(2021, 02, 24, 07, 30), 20);
    }

    @Test
    public void testGetters() {
        assertEquals("Midterm", task.getTaskName());
        assertEquals(20, task.getTaskGradeWeight());
        assertEquals(2021, task.getYear());
        assertEquals(02, task.getMonth());
        assertEquals(24, task.getDay());

    }

    @Test
    public void testGetTaskDueDate() {
        assertEquals("2021 / 02 / 24", task.getTaskDueDate());
    }

    @Test
    public void testGetTaskTime() {
        assertEquals("07 : 30", task.getTaskTime());
    }

    @Test
    public void testCompleteTask() {
        assertFalse(task.getCompletedStatus());
        task.completeTask();
        assertTrue(task.getCompletedStatus());

    }

    @Test
    public void testIsTaskComplete() {
        assertEquals(false, task.isTaskComplete());
        task.completeTask();
        assertEquals(true, task.isTaskComplete());
    }






}