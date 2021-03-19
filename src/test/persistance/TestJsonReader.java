package persistance;

import model.Course;
import model.CoursePlanner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/FileDoesNotExist.json");
        try {
            CoursePlanner cp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/EmptyCoursePlanner.json");
        try {
            CoursePlanner cp = reader.read();
            assertEquals("EmptyCoursePlanner", cp.getCoursePlannerName());
            assertEquals(0, cp.getCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/CoursePlanner.json");
        try {
            CoursePlanner cp = reader.read();
            assertEquals("CoursePlanner", cp.getCoursePlannerName());
            List<String> courseList = cp.getCourses();
            assertEquals(4, courseList.size());

            //check courses
            assertEquals("CPSC210", courseList.get(0));
            assertEquals("STAT200", courseList.get(1));
            assertEquals("CPSC221", courseList.get(2));

            //check tasks
            assertTrue(checkTaskInJsonObj("ITE1", "CPSC210", cp));
            assertTrue(checkTaskInJsonObj("Final", "CPSC210", cp));
            assertFalse(checkTaskInJsonObj("ITE1", "STAT200", cp));
            assertFalse(checkTaskInJsonObj("Midterm", "CPSC221", cp));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}


