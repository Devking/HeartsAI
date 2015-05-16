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

	// Used to check if all the cards in this player's hand is hearts
	boolean hasAllHearts() {
		boolean flag = true;
		for (Card c : hand) { if (c.getSuit() != Suit.HEARTS) flag = false; }
		return flag;
	}

	// given a suit, check if the hand has that suit
	boolean checkSuit(Suit check) {
		boolean flag = false;
		for (Card c: hand) { if (c.getSuit() == check) flag = true; }
		return flag;
	}

	// prints the hand that the player currently has
	void printHand () {
		System.out.print("\n" + name + "`s hand ("+hand.size()+" card");
		if (hand.size() > 1) System.out.print("s");
		System.out.print("):\n|");
		for (int i = 0; i < hand.size(); i++) { System.out.format("%3d|", i); }
		System.out.print("\n|");
		for (int i = 0; i < hand.size(); i++) { 
			// we can either use printCard() or printCardShort()
			System.out.format("%3s|", hand.get(i).printCardShort()); 
		}
		System.out.println("");
	}

	// Get the first suit that was played this round
	Suit getFirstSuit(ArrayList<Card> currentRound) {
		if (currentRound.size() == 0) return null;
		return currentRound.get(0).getSuit();
	}

	// Given any sort of player, make a decision to play a card
	// Pass in a copy of the game state for full playout functionality
	abstract Card performAction (State gameCopy);

}