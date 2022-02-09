package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    Item testItemNoDescription;
    Item testItemHasDescription;

    @BeforeEach
    public void setup() {
        testItemNoDescription = new Item("title1", "");
        testItemHasDescription = new Item("title1", "This is a test item.");
    }

    //TODO test constructor for different descriptions?

    //TODO
    //@Test
    // public void testRenameItem() {   }

    //TODO
    //@Test
    // public void testRenameDescription() {   }

    //TODO
    //@Test
    // public void useItemOnce() {   }

    //TODO
    //@Test
    // public void useItemMultiple() {   }

    //TODO
    //@Test
    // public void findItemOnce() {   }

    //TODO
    //@Test
    // public void findItemMultipleMoreThanMinWorth() {   }

    //TODO
    //@Test
    // public void findItemMultipleEqualToMinWorth() {   }

    //TODO
    //@Test
    // public void findItemMultipleLessThanMinWorth() {   }

    //TODO
    //@Test
    // public void toggleFavourite() {   }




}