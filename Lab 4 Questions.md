# Lab 4

### Question 1 Cereal

ArrayList practice. 

*	Remove "Oatmeal" from the ArrayList.
*	Add the name of your favorite breakfast food to the ArrayList.
*	Add "Cornflakes" to the ArrayList.
*	Print all of the items in the ArrayList, one per line.
*	Print a message if the ArrayList contains “Special K”. Print a different message if it does not contain "Special K".
*   Print the number of items in the list, using size()
*	(Optional) non-programming question: what does Captain Crunch have to do with computer hacking?


### Question 2 Dice

Write a program to roll a set of dice. Generate a random number between 1 and 6 for
each dice to be rolled, and save the values in an ArrayList.

Display the total of all the dice rolled.

In other words, you'll need a method that 
tests if all of the values in an ArrayList are the same. 


### Question 3 Go Fish

`Question_3_Go_Fish.java` is a first prototype of a program that plays a simplified version of the children's card
game Go Fish against you.  This version is based from the rules given at [https://en.wikipedia.org/wiki/Go_Fish](Wikipedia)

"Seven cards are dealt from a standard 52-card deck to each player.
The remaining cards are spread out in a disorderly pile referred to as the "pool".
The player whose turn it is to play asks another player for his or her cards of a
particular face value. For example Alice may ask, "Bob, do you have any threes?"
Alice must have at least one card of the rank she requested. Bob must hand
over all cards of that rank if possible. If he has none, Bob tells Alice to "go fish"
and Alice draws a card from the pool and places it in her own hand.

Then it is the next player's turn – unless the card Alice is given is the card she asked for,
in which case she shows it to the other players, and she gets another turn. When any player at
any time has all four cards of one face value, it forms a book, and the cards must be placed
face up in front of that player.

The players take turns. When all sets of cards have been laid down in books, the game ends.
The player with the most books wins."

Your tasks: finish the incomplete methods. Run and test the program.
You might want to add some extra System.out.println() statements to update the player on the status of the game.

Optional extra challenge: Write your own test to check the behavior of your selectComputerCardValue method.
