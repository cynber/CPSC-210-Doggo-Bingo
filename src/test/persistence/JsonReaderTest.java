package persistence;

import model.Card;
import model.CardDeck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CardDeck cd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCardDeck() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCardDeck.json");
        try {
            CardDeck cd = reader.read();
            assertEquals("My Card Deck", cd.getDeckName());
            assertEquals(0, cd.getNumberOfCards());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCardDeck() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCardDeck.json");
        try {
            CardDeck cd = reader.read();
            assertEquals("My Card Deck", cd.getDeckName());
            List<Card> cards = cd.getCards();
            assertEquals(2, cd.getNumberOfCards());
            checkCard(1,"Blueberry","This is a berry.",0,
                    100,false,cards.get(0));
            checkCard(2,"Apple","",2,
                    200,true,cards.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
