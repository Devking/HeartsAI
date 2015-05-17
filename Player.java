// Dependencies on Card, Suit, and Value files

import java.util.ArrayList;
import java.util.*;

abstract class Player {

	String name;
	int points;
	ArrayList<Card> hand = new ArrayList<Card>();

	Player (String id) { name = id; points = 0; }

	// use this class to keep track of suit ranges
	// [startIndex, endIndex) or startIndex = -1 if no suit
	class SuitRange {
		int startIndex;
		int endIndex;
		SuitRange() { startIndex = -1; endIndex = -1; }
		// Returns how many cards of that suit exist
		int getRange() { return endIndex-startIndex; }
	}

	/******************************************************************************
	/ Hand-related Methods
	/******************************************************************************/

	// Draw cards from the deck
	void addToHand (Card newCard) { hand.add(newCard); }

	// Sorts the hand by suit (used once at the start of every game)
	void sortHand () { Collections.sort(hand); }

	// Clear the cards in the hand (just to make sure the game is initialized properly)
	void clearHand () { hand.clear(); }

	// Given a suit, check if the hand has that suit
	boolean checkSuit(Suit check) {
		boolean flag = false;
		if (check == null) return false;
		for (Card c: hand) { if (c.getSuit() == check) flag = true; }
		return flag;
	}

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

	// Get the first suit that was played this round
	Suit getFirstSuit(ArrayList<Card> currentRound) {
		if (currentRound.size() == 0) return null;
		return currentRound.get(0).getSuit();
	}

	// Given a suit, check the range of indices where that suit exists
	SuitRange getSuitRange(Suit check, ArrayList<Card> currentHand) {
		SuitRange range = new SuitRange();
		if (check == null) return range;
		for (int i = 0; i < currentHand.size(); i++) { 
			if (range.startIndex == -1 && currentHand.get(i).getSuit() == check) range.startIndex = i;
			if (range.startIndex != -1 && currentHand.get(i).getSuit() != check) { range.endIndex = i; break; }
		}
		if (range.startIndex != -1 && range.endIndex == -1) range.endIndex = currentHand.size();
		return range;
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

	/******************************************************************************
	/ Points and Name Methods
	/******************************************************************************/

	// Return the name of the player
	String getName () { return name; }

	// Add points to this player
	void addPoints (int pnts) { points += pnts; }

	// Return the amount of points this player has
	int getPoints () { return points; }

	// Clear the cards in the hand and clear all points
	void clearPlayer() { clearHand(); points = 0; }

	/******************************************************************************
	/ Methods to be implemented by children
	/******************************************************************************/

	// Return true if you want your controller to print debug messages
	// Or false if you want it to just play through
	abstract boolean setDebug();

	// Given any sort of player, make a decision to play a card
	// Pass in a copy of the game state for full playout functionality
	abstract Card performAction (State masterCopy);

}