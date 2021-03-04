package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Midterm",
                new Date(2021, 02, 24), 20);
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







}