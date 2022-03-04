package persistence;

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Card;
import model.CardDeck;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CardDeck cd = new CardDeck();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCardDeck() {
        try {
            CardDeck cd = new CardDeck();
            cd.setDeckName("My Card Deck");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCardDeck.json");
            writer.open();
            writer.writeCardDeck(cd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCardDeck.json");
            cd = reader.read();
            assertEquals("My Card Deck", cd.getDeckName());
            assertEquals(0, cd.getNumberOfCards());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCardDeck() {
        try {
            CardDeck cd = new CardDeck();
            cd.setDeckName("My Card Deck");
            Card c1 = new Card("Cookie");
            c1.setCardFields(1,"Cookie","This is warm.", 0, 50, true);
            Card c2 = new Card("Ice Cream");
            c2.setCardFields(2,"Ice Cream","This is cold.", 2, 100, false);
            cd.addCard(c1);
            cd.addCard(c2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCardDeck.json");
            writer.open();
            writer.writeCardDeck(cd);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralCardDeck.json");
            cd = reader.read();
            assertEquals("My Card Deck", cd.getDeckName());
            List<Card> cards = cd.getCards();
            assertEquals(2, cd.getNumberOfCards());
            checkCard(1,"Cookie","This is warm.",0, 50,true,cards.get(0));
            checkCard(2,"Ice Cream","This is cold.",2, 100,false,cards.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
