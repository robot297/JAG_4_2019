package week_4;

import org.junit.Test;
import test_utils.PrintUtils;

import static org.junit.Assert.*;

public class Question_1_BreakfastTest {
    
    @Test(timeout=3000)
    public void testCereals()  {

        Question_1_Breakfast q1 = new Question_1_Breakfast();

        PrintUtils.catchStandardOut();

        q1.cereals();

        String out = PrintUtils.resetStandardOut();
        
        // Test that 'Cornflakes" was added
        assertTrue("Add 'Cornflakes' to the list", q1.cereals.contains("Cornflakes"));
        
        // Test that "Oatmeal" was removed
        assertFalse("Remove 'Oatmeal' from the list", q1.cereals.contains("Oatmeal"));
        
        // And when the favorite breakfast is added, there should be 4 items in the list
        assertEquals("Add your favorite breakfast food to the list", 4, q1.cereals.size());
        
        // Check that all elements of ArrayList are printed. Don't care what order.
        for (String c : q1.cereals) {
            assertTrue("Print all of the items in the list", out.contains(c));
        }
        
        // Is a message confirming "Special K is in the ArrayList" is printed
        assertTrue("Test if Special K is in the ArrayList and print the exact message requested if so", out.toLowerCase().contains("special k is in the list"));

        // Is the size of the list printed? Should be 4 items.
        assertTrue("Print a message with the number of items in the list. Use size()", out.contains("4"));
        
    }
}