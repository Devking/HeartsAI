import java.util.ArrayList;
import java.util.*;

abstract class Player {
	String name;
	int points;
	ArrayList<Card> hand = new ArrayList<Card>();

	Player (String id) { name = id; points = 0;}

	// for drawing cards from the deck
	void addToHand (Card newCard) { hand.add(newCard); }

	// prints the hand that the player currently has
	void printHand () {
		System.out.print(name + "`s hand ("+hand.size()+" card");
		if (hand.size() > 1) System.out.print("s");
		System.out.println("):");
		for (Card c : hand) { System.out.print(c.printCard() + " | "); }
		System.out.println("\n");
	}

	void sortHand () {
		Collections.sort(hand);
	}

	void clearHand () { hand.clear(); }

	void clearPlayer() { clearHand(); points = 0; }

	// used for the beginning of the game, to see who goes first
	boolean hasTwoOfClubs () { 
		if (hand.size() == 0) return false;
		Card holder = new Card(Suit.CLUBS, Value.TWO);
		return holder.equals(hand.get(0));
	}

	String getName () { return name; }

	// given any sort of player, make a decision to play a card
	abstract Card performAction ();

	void addPoints (int pnts) { points += pnts; }
	int getPoints () { return points; }

}