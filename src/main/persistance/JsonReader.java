package persistance;

import model.Course;
import model.CoursePlanner;
import model.Date;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This code is based on the sample repo provided
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

//Represents a reader that reads a CoursePlanner from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CoursePlanner from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CoursePlanner read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCoursePlanner(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CoursePlanner from JSON object and returns it
    private CoursePlanner parseCoursePlanner(JSONObject jsonObject) {
        CoursePlanner cp = new CoursePlanner();
        String name = jsonObject.getString("name");
        cp.setCoursePlannerName(name);
        addCourses(cp, jsonObject);
        return cp;
    }

    // MODIFIES: cp
    // EFFECTS: parses courses from JSON object and adds them to CoursePlanner
    private void addCourses(CoursePlanner cp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");

        for (Object jsonobj : jsonArray) {
            JSONObject tasks = (JSONObject) jsonobj;
            addCourse(cp, tasks);
        }
    }

    // MODIFIES: cp
    // EFFECTS: parses tasks from JSON object and adds it to CoursePlanner
    private void addCourse(CoursePlanner cp, JSONObject jsonObject) {
        String courseName = jsonObject.getString("course");
        JSONArray jsonTaskArray = jsonObject.getJSONArray("tasks");
        Course course = new Course(courseName);
        addTasks(course, jsonTaskArray);
        cp.addCourse(course);
    }

    // MODIFIES: cp
    // EFFECTS: parses task info from JSON object and adds it to CoursePlanner
    private void addTasks(Course category, JSONArray jsonTaskArray) {
        for (Object json: jsonTaskArray) {
            JSONObject nextTask = (JSONObject) json;

            String task = nextTask.getString("task name");
            int weight = nextTask.getInt("weight");

            int year = nextTask.getInt("year");
            int month = nextTask.getInt("month");
            int day = nextTask.getInt("day");

            Date date = new Date(year, month, day);
            category.addTask(task, date, weight);
        }
    }

}
