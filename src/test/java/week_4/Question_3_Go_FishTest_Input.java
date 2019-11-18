package week_4;

import input.InputUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.*;
import static test_utils.ArrayListUtils.*;
import static test_utils.PrintUtils.catchStandardOut;
import static test_utils.PrintUtils.resetStandardOut;
import static week_4.Question_3_Go_Fish.*;

// Mocking libraries for creating a mock InputUtils class, for generating example user input

/** Tests for GoFish.
 * Uses mocks to create mock/pretend methods that provide example user input.
 * http://easymock.org/user-guide.html is a general mocking library
 * https://github.com/powermock/powermock/wiki/MockStatic  needed to mock the static methods in InputUtils
 *
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest({InputUtils.class, Question_3_Go_Fish.class})
public class Question_3_Go_FishTest_Input {
    
    /*

        Example plays covering various scenarios.

         1. Player requests card Opponent does not have. Player fishes.

         2. Player requests card Opponent has. Card is transferred.
               -- Player requests a card Opponent does not have. Player fishes. No book.

         3. Player requests card Opponent has. Card is transferred.
               -- Player requests another card Opponent has, card is transferred
               -- Player requests a card Opponent does have. Player fishes.  No books made.

         4. Opponent does not have card, Player fishes, Player makes book

         5. Opponent does have card, transfer, Player does not make book.
                -- Player requests card Opponent does not have. Player fishes and makes one book
         
         6. Opponent does have card, transfer, Player makes book.
                -- Player requests second card Opponent does not have, Player fishes, does not make book.

         7. Opponent does have card, transfers, Player makes book.
                -- Player requests second card Opponent does not have, Player fishes, makes second book.
        
         8. Opponent does have card, transferred, Player makes book.
                -- Player requests second card opponent has, transferred, no book.
                -- Player requests third card Opponent does not have. Player fishes, makes second book.

         9. Opponent does have card, transferred, Player makes book.
                -- Player requests second card, transferred, makes book.
                -- Player requests third card Opponent does not have. Player fishes, no book.
*/
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesNotHave_PlayerFishesNoBook_1() throws Exception {
        
        playScenario(new String[]{"A"},         /* Cards player will request */
                "Player requests card opponent doesn't have, and has to fish. Does not make book.",  /* Description of play */
                newArrayList("A", "2", "3"),            /* initial player hand */
                newArrayList("7", "8", "9"),            /* initial opponent hand */
                newArrayList("Q", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "2", "3", "Q"),       /* Expected player hand after turn */
                newArrayList("7", "8", "9"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"),                 /* Expected pool after turn */
                new ArrayList<>(),                         /* Expected player books after turn */
                new ArrayList<>()                          /* Expected opponent books after turn */
        );
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_ThenDoesNotHave_PlayerFishesNoBook_2() throws Exception {
        
        playScenario(new String[]{"3", "2"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player requests card opponent doesn't have, and has to fish. Does not make book.",  /* Description of play */
                newArrayList("A", "2", "3"),            /* initial player hand */
                newArrayList("3", "8", "9"),            /* initial opponent hand */
                newArrayList("A", "2", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "2", "3", "3", "A"),       /* Expected player hand after turn */
                newArrayList("8", "9"),            /* Expected opponent hand after turn */
                newArrayList("2", "3"),                 /* Expected pool after turn */
                new ArrayList<>(),                         /* Expected player books after turn */
                new ArrayList<>()                          /* Expected opponent books after turn */
        );
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_ThenDoesHave_ThenDoesNotHave_PlayerFishesNoBook_3() throws Exception {
        
        playScenario(new String[]{"2", "3", "A"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player requests another card opponent has, card is transferred. Player requests card opponent doesn't have, and has to fish. Does not make book.",  /* Description of play */
                newArrayList("A", "2", "3", "Q"),            /* initial player hand */
                newArrayList("3", "8", "9", "2", "2"),            /* initial opponent hand */
                newArrayList("A", "2", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "2", "3", "Q", "2", "2", "3", "A"),       /* Expected player hand after turn */
                newArrayList("8", "9"),            /* Expected opponent hand after turn */
                newArrayList("2", "3"),                 /* Expected pool after turn */
                new ArrayList<>(),                         /* Expected player books after turn */
                new ArrayList<>()                          /* Expected opponent books after turn */
        );
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesNotHave_PlayerFishesMakesBook_4() throws Exception {
        
        playScenario(new String[]{"A"},         /* Cards player will request */
                "Player requests a card opponent doesn't have, goes fishing, and makes book.",  /* Description of play */
                newArrayList("A", "2", "3", "2", "2", "Q"),            /* initial player hand */
                newArrayList("8", "9", "4"),            /* initial opponent hand */
                newArrayList("2", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "3", "Q"),       /* Expected player hand after turn */
                newArrayList("8", "9", "4"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"),                 /* Expected pool after turn */
                newArrayList("2"),                         /* Expected player books after turn */
                new ArrayList<>()                          /* Expected opponent books after turn */
        );
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_ThenDoesNotHave_PlayerFishesMakesBook_5() {
        
        playScenario(new String[]{"7", "A"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player requests card opponent doesn't have, and has to fish, makes book.",
                newArrayList("A", "Q", "Q", "8", "7", "Q"),  /* initial player hand */
                newArrayList("3", "7", "9"),            /* initial opponent hand */
                newArrayList("Q", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "8", "7", "7"),                 /* Expected player hand after turn */
                newArrayList("3", "9"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"), /* Expected pool after turn */
                newArrayList("Q"), /* Expected player books after turn */
                new ArrayList<>() /* Expected computer books after turn */
        );
        
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_MakeBook_ThenDoesNotHave_PlayerFish_NoBook_6() {
        
        playScenario(new String[]{"Q", "A"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player makes book. Player requests card opponent doesn't have, and has to fish, no book.",
                newArrayList("A", "Q", "Q", "8", "7", "Q"),  /* initial player hand */
                newArrayList("3", "Q", "9"),            /* initial opponent hand */
                newArrayList("9", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "8", "7", "9"),                 /* Expected player hand after turn */
                newArrayList("3", "9"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"), /* Expected pool after turn */
                newArrayList("Q"), /* Expected player books after turn */
                new ArrayList<>() /* Expected computer books after turn */
        );
        
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_MakeBook_ThenDoesNotHave_PlayerFish_MakeBook_7() {
        //        7. Opponent does have card, transfers, Player makes book.
        //        --Player requests second card Opponent does not have, Player fishes, makes second book.
        playScenario(new String[]{"Q", "7"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player makes book. Player requests card opponent doesn't have, and has to fish, makes another book.",
                newArrayList("A", "Q", "7", "Q", "7", "7", "Q"),  /* initial player hand */
                newArrayList("3", "Q", "9"),            /* initial opponent hand */
                newArrayList("7", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A"),                 /* Expected player hand after turn */
                newArrayList("3", "9"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"), /* Expected pool after turn */
                newArrayList("Q", "7"), /* Expected player books after turn */
                new ArrayList<>() /* Expected computer books after turn */
        );
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_MakeBook_ThenDoesHaveNoBook_PlayerFish_MakeBook_8() {
        
        //         8. Opponent does have card, transferred, Player makes book.
        //            -- Player requests second card opponent has, transferred, no book.
        //                -- Player requests third card Opponent does not have. Player fishes, makes second book.
        
        
        playScenario(new String[]{"Q", "7", "A"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player makes book. Player requests card opponent does have, does not make book. Player requests card opponent doesn't have. Player has to fish, makes book.",
                newArrayList("A", "Q", "7", "Q", "7", "7", "Q"),  /* initial player hand */
                newArrayList("3", "Q", "9"),            /* initial opponent hand */
                newArrayList("7", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A"),                 /* Expected player hand after turn */
                newArrayList("3", "9"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"), /* Expected pool after turn */
                newArrayList("Q", "7"), /* Expected player books after turn */
                new ArrayList<>() /* Expected computer books after turn */
        );
        
    }
    
    @Test(timeout=3000)
    public void testTurn_OpponentDoesHave_MakeBook_ThenDoesHaveMakeSecondBook_PlayerFish_NoBook_9() {
        
        //        9. Opponent does have card, transferred, Player makes book.
        //        -- Player requests second card, transferred, makes book.
        //                -- Player requests third card Opponent does not have. Player fishes, no book.
        //
        
        playScenario(new String[]{"Q", "7", "A"},         /* Cards player will request */
                "Player requests card opponent does have. Card transferred. Player makes book. Player requests card opponent does have, makes anther book. Player requests card opponent doesn't have. Player has to fish, no book.",
                newArrayList("A", "Q", "7", "Q", "7", "7", "Q"),  /* initial player hand */
                newArrayList("3", "Q", "7"),            /* initial opponent hand */
                newArrayList("7", "A", "3"),            /* initial pool */
                new ArrayList<>(),                         /* initial player books */
                new ArrayList<>(),                         /* initial opponent books */
                newArrayList("A", "7"),                 /* Expected player hand after turn */
                newArrayList("3"),            /* Expected opponent hand after turn */
                newArrayList("A", "3"), /* Expected pool after turn */
                newArrayList("Q", "7"), /* Expected player books after turn */
                new ArrayList<>() /* Expected computer books after turn */
        );
    }
    
    
    // Helper methods for the above tests method.
    private void playScenario(String[] inputs, String playDescription, ArrayList<String> startPlayerHand, ArrayList<String> startOpponentHand, ArrayList<String> startPool, ArrayList<String> startBooks, ArrayList<String> startOpponentBooks,
                              ArrayList<String> expectedPlayerHand, ArrayList<String> expectedOpponentHand, ArrayList<String> expectedPool, ArrayList<String> expectedPlayerBooks, ArrayList<String> expectedOpponentBooks) {
        
        
        //Run tests twice. First time, The human is the player, taking a turn against the computer.
        
        mockStatic(InputUtils.class);
        
        for (String i : inputs) {
            expect(InputUtils.stringInput(anyString())).andReturn(i).once();
        }
        
        replay(InputUtils.class);
        
        
        pool = new ArrayList<>(startPool);
        playerHand = new ArrayList<>(startPlayerHand);
        computerHand = new ArrayList<>(startOpponentHand);
        playerBooks = new ArrayList<>(startBooks);
        computerBooks = new ArrayList<>(startOpponentBooks);
        
        playerTurn();
        
        String msg = "Human player is playing. Computer is their opponent. " + playDescription + "\n";
        
        assertTrue(msg + " The player's hand was not as expected.", arrayListEqual(expectedPlayerHand, playerHand, true));
        assertTrue(msg + " The computers's hand was not as expected.", arrayListEqual(expectedOpponentHand, computerHand, true));
        assertTrue(msg + " The pool was not as expected.", arrayListEqual(expectedPool, pool, true));
        assertTrue(msg + " The player's books are not as expected.", stringArrayListSameElementsAnyOrder(expectedPlayerBooks, playerBooks));
        assertTrue(msg + " The computers's books are not as expected.", stringArrayListSameElementsAnyOrder(expectedOpponentBooks, computerBooks));
        
        
        
        
        // Same thing, but this time, Computer is the player, taking a turn against the human.
        
        
        mockStaticPartial(Question_3_Go_Fish.class, "selectComputerCardValue");
        
        for (String i : inputs) {
            expect(Question_3_Go_Fish.selectComputerCardValue()).andReturn(i);
        }
        
        replay(Question_3_Go_Fish.class);
        
        pool = new ArrayList<>(startPool);
        computerHand = new ArrayList<>(startPlayerHand);
        playerHand = new ArrayList<>(startOpponentHand);
        computerBooks = new ArrayList<>(startBooks);
        playerBooks = new ArrayList<>(startOpponentBooks);
        
        computerTurn();
        
        msg = "Computer is playing. Human is their opponent. " + playDescription + "\n";
        
        assertTrue(msg + " The computer's hand was not as expected.", arrayListEqual(expectedPlayerHand, computerHand, true));
        assertTrue(msg + " The player's hand was not as expected.", arrayListEqual(expectedOpponentHand, playerHand, true));
        assertTrue(msg + " The pool was not as expected.", arrayListEqual(expectedPool, pool, true));
        assertTrue(msg + " The computer's books are not as expected.", stringArrayListSameElementsAnyOrder(expectedPlayerBooks, computerBooks));
        assertTrue(msg + " The player's books are not as expected.", stringArrayListSameElementsAnyOrder(expectedOpponentBooks, playerBooks));
        
    }
    
    
    @Test(timeout=3000)
    public void testCardValueInputValidInput() throws Exception {
        
        playerHand = newArrayList("A", "2", "4", "7", "9");
        
        mockStatic(InputUtils.class);
        expect(InputUtils.stringInput(anyString())).andReturn("7");
        replay(InputUtils.class);
        
        String cardSelection = cardValueInput();
        
        assertEquals("If player has entered a valid card from their hand, return this card.", "7", cardSelection);
    }
    
    
    @Test(timeout=3000)
    public void testCardValueInputInvalidInput() throws Exception {
        
        playerHand = newArrayList("A", "2", "4", "7", "9");
        
        mockStatic(InputUtils.class);
        expect(InputUtils.stringInput(anyString()))
                .andReturn("10")     // Not in the hand
                .andReturn("pizza")  // Not a card value
                .andReturn("100")    // Not a card value
                .andReturn("")       // Empty
                .andReturn("7");     // Finish with a valid input
        
        replay(InputUtils.class);
        
        
        String cardSelection = cardValueInput();
        
        // All of the invalid input should be ignored, and the last, valid, input will be returned.
        assertEquals("Do not accept card values that are not in the player's hand.", "7", cardSelection);
    }
    
    
}