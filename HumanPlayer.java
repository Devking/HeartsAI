import java.io.Console;
import java.util.ArrayList;

class HumanPlayer extends Player {
	
	Console console;

	HumanPlayer (String name) { 
		super(name); 
		console = System.console(); 
		System.out.println("Human player ("+name+") initialized."); 
	}

	Card performAction (ArrayList<Card> currentRound) {
		boolean flag = true;
		int i = 0;
		// run a while loop to make sure the user keeps inputting until valid input
		while (flag) {
			// print hand is only needed for debugging / human players
			printHand();
			flag = false;
			try {
				// read input from user ; make sure that an integer is inputted
				// this may cause an exception -- be sure to catch it!
				i = Integer.parseInt(console.readLine("\nInput the index of the card you ("+name+") wish to play:\n> "));
			} catch (NumberFormatException n) {
				System.out.println("You did not input a valid integer index! Try again!");
				flag = true;
				i = 0;
			}
			// check if the number is valid for this hand size
			if (i > hand.size()-1) { 
				System.out.println("Invalid card index! Please input a valid number!"); 
				flag = true; 
			}
		}
		System.out.println();
		return hand.remove(i);
	}

}