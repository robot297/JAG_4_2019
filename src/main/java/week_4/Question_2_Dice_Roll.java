package week_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static input.InputUtils.positiveIntInput;
import static input.InputUtils.yesNoInput;

/**

 Finish this program to roll a set of dice. Generate a random number between 1 and 6 for
 each dice to be rolled, and save the values in a list.

 Display the total of all the dice rolled.

 In some games, rolling the same number on all dice has a special meaning.
 In your program, check if all dice have the same value, and print a message
 if all the dice show the same value.  In other words, you'll need to write a method that
 checks if all of the values in a list are the same.
 
 */

public class Question_2_Dice_Roll {
    
    public static final String SAME_VALUES = "All the dice have the same value!";
    
    static Random rnd = new Random();   // You will use this Random number generator in your roll method.
    
    public static void main(String[] args) {
    
        // You don't need to modify this method.
        
        // How many dice to roll?
        int numberOfDice = positiveIntInput("How many dice would you like to roll?");
    
        // A do loop is similar to a while loop, but the condition is
        // checked at the end of one loop iteration.
        // So the set of dice are always rolled at least one time.
        
        do {
            // Roll the dice
            List<Integer> diceValues = roll(numberOfDice);
            
            // Print the dice values rolled
            System.out.println("The dice have the values: " + diceValues);
            System.out.println("The total of all dice: " + diceTotal(diceValues));
          
            if (allSameValue(diceValues)) {
                System.out.println(SAME_VALUES);
            }
            
        } while (yesNoInput("Do you want to roll again?"));
    }
    
    
    public static List<Integer> roll(int numberOfDice) {

        // Use the Random rnd variable declared on line 28 to generate random numbers.
        // Don't create another Random object.
        
        // TODO create an ArrayList of Integer.
        // TODO Roll the given number of dice. Store the values in an ArrayList and return it.
        
       return null;  // TODO Replace with your code
    }


    public static int diceTotal(List<Integer> diceValues) {
    
        // TODO if the diceValues List is null, return false.  (hint: do this check first)
        // TODO if the diceValues List is empty, return false
        
        // TODO add up all of the values in the List and return this total.
        // TODO this should still work for any number of dice in the diceValues List.
        
        return 0;  // TODO Replace with your code.
    }


    public static boolean allSameValue(List<Integer> diceValues) {
    
        // TODO if the diceValues List is null, return false. (hint: do this check first)
        // TODO if the diceValues List is empty, return false
    
        // TODO return true if all of the values in the diceValues List are the same.
        // TODO this method should work for 0 dice, 1 dice, 2 dice, 3 dice, 100 dice...
        
        return false;   // TODO Replace with your code

    }
    
}
