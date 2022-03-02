package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}


//     TODO: go into toJson and try to add those methods to CardDeck
//     TODO: fix the cardDeck renamer to use setDeckName
//      TODO: add in the JSONWriter stuff