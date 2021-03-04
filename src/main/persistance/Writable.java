package persistance;

import org.json.JSONObject;

// This code is based on the sample repo provided
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

public interface Writable {
    // EFFECTS: returns a Json object
    JSONObject toJson();
}
