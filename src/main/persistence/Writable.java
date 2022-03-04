package persistence;

// Interface for writing content

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}