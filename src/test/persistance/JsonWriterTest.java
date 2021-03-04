package persistance;

// This code is based on the sample repo provided
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

import model.Course;
import model.CoursePlanner;
import model.Date;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            CoursePlanner cp = new CoursePlanner();
            cp.setCoursePlannerName("CoursePlanner");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            String coursePlannerSetName = "EmptyCoursePlannerTest";
            String directory = "./data/" + coursePlannerSetName + ".json";

            CoursePlanner cp = new CoursePlanner();
            cp.setCoursePlannerName(coursePlannerSetName);
            JsonWriter writer = new JsonWriter(directory);
            writer.open();
            writer.write(cp);
            writer.close();

            JsonReader reader = new JsonReader(directory);
            cp = reader.read();

            assertEquals(coursePlannerSetName, cp.getCoursePlannerName());
            assertEquals(0, cp.getCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            String coursePlannerSetName = "FullCoursePlannerTest";
            String directory = "./data/" + coursePlannerSetName + ".json";

            CoursePlanner cp = new CoursePlanner();
            cp.setCoursePlannerName(coursePlannerSetName);

            Course c1 = new Course("CPSC210");
            c1.addTask("ITE1", new Date(2021, 02, 24), 20);

            Course c2 = new Course("CPSC221");
            c2.addTask("Final", new Date(2021, 04, 21), 40);

            cp.addCourse(c1);
            cp.addCourse(c2);

            JsonWriter writer = new JsonWriter(directory);
            writer.open();
            writer.write(cp);
            writer.close();

            JsonReader reader = new JsonReader(directory);
            cp = reader.read();
            assertEquals(coursePlannerSetName, cp.getCoursePlannerName());
            List<String> courses = cp.getCourses();
            assertEquals(2, courses.size());

            assertTrue(checkTaskInJsonObj("ITE1", "CPSC210", cp));
            assertTrue(checkTaskInJsonObj("Final", "CPSC221", cp));
            assertFalse(checkTaskInJsonObj("ITE1", "CPSC221", cp));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
