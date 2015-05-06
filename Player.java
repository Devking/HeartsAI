// Dependencies on Card, Suit, and Value files

import java.util.ArrayList;
import java.util.*;

abstract class Player {
	String name;
	int points;
	ArrayList<Card> hand = new ArrayList<Card>();

	Player (String id) { name = id; points = 0;}

	// for drawing cards from the deck
	void addToHand (Card newCard) { hand.add(newCard); }

	// Add points to this player
	void addPoints (int pnts) { points += pnts; }

	// Sorts the hand by suit (used once at the start of every game)
	void sortHand () { Collections.sort(hand); }

	// Return the name of the player
	String getName () { return name; }

	// Return the amount of points this player has
	int getPoints () { return points; }

	// Clear the cards in the hand (just to make sure the game is initialized properly)
	void clearHand () { hand.clear(); }

	// Clear the cards in the hand and clear all points
	void clearPlayer() { clearHand(); points = 0; }

	// Used for the beginning of the game, to see who goes first
	boolean hasTwoOfClubs () { 
		if (hand.size() == 0) return false;
		Card holder = new Card(Suit.CLUBS, Value.TWO);
		return holder.equals(hand.get(0));
	}

	// prints the hand that the player currently has
	void printHand () {
		System.out.print("\n" + name + "`s hand ("+hand.size()+" card");
		if (hand.size() > 1) System.out.print("s");
		System.out.print("):\n|");
		for (int i = 0; i < hand.size(); i++) { System.out.format("%2d|", i); }
		System.out.print("\n|");
		for (int i = 0; i < hand.size(); i++) { 
			// we can either use printCard() or printCardShort()
			System.out.print(hand.get(i).printCardShort() + "|"); 
		}
		System.out.println("");
	}

	// Given any sort of player, make a decision to play a card
	abstract Card performAction ();

}