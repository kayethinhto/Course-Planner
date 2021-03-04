package persistance;

import model.CoursePlanner;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This code is based on the sample repo provided
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

public class JsonWriter { //TODO: add comments
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;  // use ./data/--name of file--


    public JsonWriter(String destination) {
        this.destination = destination;
    }


    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(CoursePlanner list) {
        JSONObject json = list.toJson();
        saveToFile(json.toString(TAB));
    }

    public void close() {
        writer.close();
    }

    public void saveToFile(String json) {
        writer.print(json);
    }
}
