package week_4;

import input.InputUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test_utils.ArrayListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.replay;
import static test_utils.ArrayListUtils.newArrayList;


@RunWith(PowerMockRunner.class)
@PrepareForTest(InputUtils.class)
public class Question_2_Dice_RollTest {
    

    @Test(timeout=3000)
    public void testRoll()  {

       // Question_2_Dice_Roll Question_2_Dice_Roll = new Question_2_Dice_Roll();

        Random rnd = mock(Random.class);
        expect(rnd.nextInt(6))
                .andReturn(3)
                .andReturn(4)
                .andReturn(2)
                .andReturn(5);

        replay(rnd);

        // Replace the program's random number generator with the mock
        Question_2_Dice_Roll.rnd = rnd;

        List<Integer> expected = newArrayList(4, 5, 3, 6);

        List<Integer> actual = Question_2_Dice_Roll.roll(4);

        assertTrue("Use the Random rnd variable provided in the program. Don't create a new Random. " +
                "\nRoll the given number of dice, store each number in an ArrayList, and return it. ",
                ArrayListUtils.arrayListEqual(expected, actual));

        
        List<Integer> actualEmpty = Question_2_Dice_Roll.roll(0);
        assertEquals("If the user rolls 0 dice, return an empty ArrayList. You should not generate any random numbers",
                0, actualEmpty.size());
    }

    
    @Test(timeout=3000)
    public void testDiceTotal() {
    
        // Add up 3 values
        List<Integer> example = newArrayList(4, 5, 3);
        int total = Question_2_Dice_Roll.diceTotal(example);
        assertEquals("If there are 3 dice values in the ArrayList, for example [4, 5, 3], the total should be 12.", 12, total);
    
        // Add up 2 values
        example = newArrayList(4, 3);
        total = Question_2_Dice_Roll.diceTotal(example);
        assertEquals("If the dice have values 4 and 3, the total should be 7", 7, total);
    }
    
    @Test(timeout=3000)
    public void testDiceTotalNullList() {
        assertEquals("If the ArrayList is null, return 0", 0, Question_2_Dice_Roll.diceTotal(null));
    }

    @Test(timeout=3000)
    public void testDiceTotalEmptyList()  {
        assertEquals("If the ArrayList is empty, return 0", 0, Question_2_Dice_Roll.diceTotal(new ArrayList<>()));
        
    }

    
    @Test(timeout=3000)
    public void testAllSameValue() {
    
        List<Integer> example = newArrayList(4, 5, 3);
        assertFalse("allSameValue called with an ArrayList of 4, 5, 3 should return false", Question_2_Dice_Roll.allSameValue(example));
    
        example = newArrayList(4, 4, 4, 4, 3);
        assertFalse("allSameValue called with an ArrayList of 4, 4, 4, 4, 3 should return false", Question_2_Dice_Roll.allSameValue(example));
    
        example = newArrayList(4, 4, 4);
        assertTrue("allSameValue called with an ArrayList of 4, 4, 4 should return true", Question_2_Dice_Roll.allSameValue(example));
    
        example = newArrayList(1, 1, 1);
        assertTrue("allSameValue called with an ArrayList of 1, 1, 1 should return true", Question_2_Dice_Roll.allSameValue(example));
    
        example = newArrayList(3, 1, 1, 1);
        assertFalse("allSameValue called with an ArrayList of 3, 1, 1, 1 should return false", Question_2_Dice_Roll.allSameValue(example));
    
        example = newArrayList(4);
        assertTrue("allSameValue called with an ArrayList of 4 should return true", Question_2_Dice_Roll.allSameValue(example));
    }
    
    @Test(timeout=3000)
    public void testAllSameValueEmptyList() {
    
        // Empty list, returns false
        List<Integer> example = new ArrayList<>();
        assertFalse("allSameValue called with an empty ArrayList should return false", Question_2_Dice_Roll.allSameValue(example));
    }
    
    @Test(timeout=3000)
    public void testAllSameValueNullList()  {
        
        // null list, returns false
        assertFalse("allSameValue called with a null ArrayList should return false", Question_2_Dice_Roll.allSameValue(null));

    }
}