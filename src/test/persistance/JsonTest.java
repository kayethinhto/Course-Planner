package persistance;

import model.Course;
import model.CoursePlanner;
import model.Task;

// This code is based on the sample repo provided
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    public boolean checkTaskInJsonObj(String taskName, String courseName, CoursePlanner cp) {
        return cp.getCourseObj(courseName).getTaskNames().contains(taskName);
    }
}
