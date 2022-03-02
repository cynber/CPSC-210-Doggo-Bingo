package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Card;
import model.CardDeck;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a card deck from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CardDeck read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCardDeck(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses card deck from JSON object and returns it
    private CardDeck parseCardDeck(JSONObject jsonObject) {
    //    int cardID = jsonObject.getInt("cardID");
        CardDeck cd = new CardDeck();
        addCards(cd, jsonObject);
        return cd;
    }

    // MODIFIES: cd
    // EFFECTS: parses card deck from JSON object and adds them to card deck
    private void addCards(CardDeck cd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(cd, nextCard);
        }
    }

    // MODIFIES: cd
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addCard(CardDeck cd, JSONObject jsonObject) {
        int cardId = jsonObject.getInt("cardID");
        String cardTitle = jsonObject.getString("Title");
        String cardDescription = jsonObject.getString("Description");
        int cardUsedCount = jsonObject.getInt("Use Count");
    //  int cardFoundCount = jsonObject.getInt("Found Count");
        int cardPointsWorth = jsonObject.getInt("Points");
        boolean cardIsFavourite = jsonObject.getBoolean("Favourite");
        Card oneCard = new Card(cardTitle);
        oneCard.setId(cardId);
        oneCard.setTitle(cardTitle);
        oneCard.setDescription(cardDescription);
        oneCard.setUsedCount(cardUsedCount);
    //  oneCard.setFoundCount(cardFoundCount);
        oneCard.setPoints(cardPointsWorth);
        oneCard.setIsFavourite(cardIsFavourite);
        cd.addCard(oneCard);
    }
}
