package week_4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static test_utils.ArrayListUtils.arrayListEqual;
import static test_utils.ArrayListUtils.newArrayList;
import static test_utils.ArrayListUtils.stringArrayListSameElementsAnyOrder;
import static test_utils.PrintUtils.catchStandardOut;
import static test_utils.PrintUtils.resetStandardOut;
import static week_4.Question_3_Go_Fish.*;


public class Question_3_Go_FishTest {
    
    static final int TIMEOUT = 3000;
    
    @Test(timeout=TIMEOUT)
    public void testCreateDeck()  {
        
        List<String> deck = createDeck();
        assertEquals("The deck should contain 52 cards", 52, deck.size());
        
        int expectedSize = 52;
        
        // Remove each card value from cardValues, verify that
        // the correct number are removed after each of 4 loops.
        for (int x = 0 ; x <= 4 ; x++) {
            
            assertEquals("The deck should contain 4 of each card value, e.g. 4 cards of value A, 4 cards of value 2, 4 cards of value 3...", deck.size(), expectedSize);
            
            for (String v : cardValues) {
                deck.remove(v);
            }
            
            expectedSize -= 13;
        }
        
        // TODO how to check if deck is shuffled?
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testCreatePool() throws Exception {
        
        List<String> originalExampleDeck = newArrayList("Q", "J", "4");
        List<String> exampleDeck = newArrayList("Q", "J", "4");
        
        createPool(exampleDeck);
        assertTrue("This method move all cards from the deck to the pool.", stringArrayListSameElementsAnyOrder(originalExampleDeck, pool));
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testDealHand() throws Exception {
        
        ArrayList<String> exampleDeck = newArrayList("Q", "J", "4", "5", "3", "2", "4", "Q", "A");
        
        List<String> hand = dealHand(exampleDeck);
        
        // deck should contain ["Q", "A"]
        // hand should be ["Q", "J", "4", "5", "3", "2", "4"]
        
        ArrayList<String> deckAfterDeal = newArrayList("Q", "A");
        ArrayList<String> handAfterDeal = newArrayList("Q", "J", "4", "5", "3", "2", "4");
        
        assertTrue("When dealing cards, move cards from the deck to the player's hand. " +
                "The deck does not contain the expected cards after an example deal.", arrayListEqual(exampleDeck, deckAfterDeal, true));
        assertTrue("When dealing cards, move cards from the deck to the player's " +
                "hand. The hand does not contain the expected cards after an example deal.", arrayListEqual(hand, handAfterDeal, true));
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testSelectComputerCardValue() throws Exception {
    
        // This is not a very satisfactory test, since the details of how
        // the computer chooses are not yet implemented.
        // You'll can improve  this test after you implement your selectComputerCardValue method,
        // TODO, optional - revise this test to check the behavior of your solution.
    
        computerHand = newArrayList("A", "2", "4");
    
        // Select 100 times, ensure card selected is one from the hand.
        for (int x = 0; x < 100; x++) {
            String card = selectComputerCardValue();
            assertTrue("The computer's selection should be one of the cards in the computer's hand", computerHand.contains(card));
        }
    
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testSelectComputerCardValueNullForEmptyHand() throws Exception {
        // If the hand is empty, return null
        computerHand = new ArrayList<>();
        assertNull("If the computer's hand is empty, return null", selectComputerCardValue());
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testGoFish() throws Exception {
        
        pool = newArrayList("Q", "J", "3");
        ArrayList<String> poolAfterFish = newArrayList("J", "3");
        
        ArrayList<String> exampleHand = newArrayList("4", "2", "5");
        ArrayList<String> exampleHandAfterFish = newArrayList("4", "2", "5", "Q");
        
        goFish(exampleHand);
        
        String msg = "The goFish method should move one card from the start of the pool ArrayList to the end of the player's hand. ";
        assertTrue(msg + " The hand was not changed correctly", arrayListEqual(exampleHand, exampleHandAfterFish, true));
        assertTrue(msg + " The pool was not changed correctly.", arrayListEqual(pool, poolAfterFish, true));
        
        // Test with empty pool. Don't modify hand or pool. Should not crash
        
        pool = new ArrayList<>();
        exampleHand = newArrayList("4", "2", "5");
        exampleHandAfterFish = newArrayList("4", "2", "5");
        
        goFish(exampleHand);
        
        assertTrue("If the pool is empty, don't modify the player's hand.", arrayListEqual(exampleHand, exampleHandAfterFish, true));
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testHandHasCard() throws Exception {
        
        ArrayList<String> exampleHand = newArrayList("4", "2", "5");
        String msg = "Return true if a hand contains a card, false otherwise. Example: if the hand is [\"4\", \"2\", \"5\"] return true for \"4\" or \"5\". Return false for \"6\" or \"Q\"";
        assertTrue(msg, handHasCard(exampleHand, "4"));
        assertTrue(msg, handHasCard(exampleHand, "2"));
        assertTrue(msg, handHasCard(exampleHand, "5"));
        
        
        assertFalse(msg, handHasCard(exampleHand, "Q"));
        assertFalse(msg, handHasCard(exampleHand, "6"));
        
        
        msg = "If the card is null, return false";
        assertFalse(msg, handHasCard(exampleHand, null));
        
    }
   
    
    @Test(timeout=TIMEOUT)
    public void testTransfer() throws Exception {
        
        //Transfer "4"
        
        ArrayList<String> fromHand = newArrayList("4", "2", "2", "5", "2");
        ArrayList<String> expectedFromHandAfter = newArrayList("2", "2", "5", "2");
        
        ArrayList<String> toHand = newArrayList("J", "Q", "8");
        ArrayList<String> expectedToHandAfter = newArrayList("J", "Q", "8", "4");
        
        transfer("4", fromHand, toHand);
        
        String msg = "Check the behavior of your transfer method. It should move all cards of the given value from one hand to the other.";
        
        assertTrue(msg, arrayListEqual(fromHand, expectedFromHandAfter, true));
        assertTrue(msg, arrayListEqual(toHand, expectedToHandAfter, true ));
        
        
        
        //Transfer more than one card "2"
        
        fromHand = newArrayList("4", "2", "2", "5", "2");
        expectedFromHandAfter = newArrayList("4", "5");
        
        toHand = newArrayList("J", "Q", "8");
        expectedToHandAfter = newArrayList("J", "Q", "8", "2", "2", "2");
        
        transfer("2", fromHand, toHand);
        assertTrue(msg, arrayListEqual(fromHand, expectedFromHandAfter, true));
        assertTrue(msg, arrayListEqual(toHand, expectedToHandAfter, true));
        
        
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testMakeBooks() throws Exception {
    
        ArrayList<String> hand = newArrayList("2", "4", "2", "2", "5", "2");
        ArrayList<String> books = new ArrayList<>();
    
        ArrayList<String> expectedHand = newArrayList("4", "5");
        ArrayList<String> expectedBooks = newArrayList("2");
    
        ArrayList<String> expectedHand2 = newArrayList("4", "5");
        ArrayList<String> expectedBooks2 = newArrayList("2");
    
        makeBooks(hand, books);
    
        String msg = "A book is 4 of the same card. If there are 4 of one card in the hand, remove all of those cards. Add one entry to the player's books ArrayList. " +
                "\nIf there are no books, don't modify the hand.";
    
        assertTrue(msg, arrayListEqual(hand, expectedHand, true));
        assertTrue(msg, arrayListEqual(books, expectedBooks, true));
    
        // No books left to make. Verify nothing changes.
        makeBooks(hand, books);
    
        assertTrue(msg, arrayListEqual(hand, expectedHand2, true));
        assertTrue(msg, arrayListEqual(books, expectedBooks2, true));
    }
   
    
    @Test(timeout=TIMEOUT)
    public void testMakeManyBooks()  {
        // Make more than one book
        
        String msg = "A book is 4 of the same card. If there are 4 of one card in the hand, remove all of those cards. Add one entry to the player's books ArrayList. " +
                "\nIf there are no books, don't modify the hand.";
    
        ArrayList<String> hand2books = newArrayList("2", "3", "3", "4", "2", "2", "3", "3", "5", "2");
        ArrayList<String> books2books = new ArrayList<>();
        
        ArrayList<String> expectedHand2books = newArrayList("4", "5");
        ArrayList<String> expectedBooks2books = newArrayList("2", "3");
        
        makeBooks(hand2books, books2books);
        
        assertTrue(msg, arrayListEqual(hand2books, expectedHand2books, true));
        assertTrue(msg, arrayListEqual(books2books, expectedBooks2books, true));
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testGameOver() throws Exception {
        // pool empty?
        
        pool = new ArrayList<>();
        assertTrue(gameOver());
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testIdentifyWinner() throws Exception {
        
        computerBooks = newArrayList("A", "2", "4", "7", "9", "K", "Q", "J");
        playerBooks = newArrayList("3", "5", "6", "8", "10");
        
        assertEquals("If the computer has more books than the human player, return COMPUTER as the winner", COMPUTER, identifyWinner());
        
        computerBooks = newArrayList("A", "2", "4", "7", "9");
        playerBooks = newArrayList("3", "5", "6", "8", "10" , "K", "Q", "J");
        
        assertEquals("If the human player has more books than the human player, return HUMAN as the winner", HUMAN, identifyWinner());
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testDisplayHand() throws Exception {
        
        ArrayList<String> example = newArrayList("A", "3", "Q");
        
        catchStandardOut();
        displayHand(example);
        String out = resetStandardOut();
        
        for (String c : example) {
            assertTrue("Print all of the cards in the hand. For the hand " + example + ", your program printed \n"
                            + out + " \nwhich does not contain " + c,
                    out.contains(c));
        }
        
    }
    
    
    @Test(timeout=TIMEOUT)
    public void testPrintGameStats() throws Exception {
        
        playerBooks = newArrayList("A", "9", "7");
        computerBooks = newArrayList("6", "K");
        
        catchStandardOut();
        printGameStats();
        String out = resetStandardOut();
        
        for (String c : playerBooks) {
            assertTrue("Print all of the books in the player's list of books. If playerBooks is  " + playerBooks + ", your program printed \n"
                            + out + " \nwhich does not contain " + c,
                    out.contains(c));
        }
        
        assertTrue("Display the total number of books for each player. Did not find human total number of books", out.contains("3"));  // human has 3 books
        
        for (String c : computerBooks) {
            assertTrue("Print all of the books in the computer's list of books. If computerBooks is  " + computerBooks + ", your program printed \n"
                            + out + " \nwhich does not contain " + c,
                    out.contains(c));
        }
        
        assertTrue("Display the total number of books for each player. Did not find computer total number of books", out.contains("2"));
        
        // In this case, the human player is the winner.
        
        assertTrue("If the human player has more books than the computer, display \"Human is the winner\"",
                out.toLowerCase().contains("human is the winner"));
        
        
        //And if computer wins?
        
        computerBooks = newArrayList("A", "3", "7");
        playerBooks = newArrayList("6", "K");
        
        catchStandardOut();
        printGameStats();
        out = resetStandardOut();
        
        assertTrue("If the human computer has more books than the computer, display \"Computer is the winner\"",
                out.toLowerCase().contains("computer is the winner"));
        
    }

}