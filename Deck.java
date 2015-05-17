// dependencies are on Suit, Value, and Card files

import java.util.*;

public class Deck {

	// use this to check if we have already initialized the deck once or not
	// so we do not generate duplicate decks
	boolean initCounter;

	// these are all of the cards currently in the deck
	ArrayList<Card> allCards = new ArrayList<Card>();

	// these are all of the cards not in the deck (during gameplay)
	// keeps pointers to those cards, so be careful
	ArrayList<Card> invertDeck = new ArrayList<Card>();

	// constructor for a deck: initialize the 52 cards and shuffle the deck
	Deck () { initCounter = true; initDeck(); shuffleDeck(); }

	// copy constructor for a deck
	Deck (Deck toCopy) {
		initCounter = toCopy.initCounter;
		allCards 	= new ArrayList<Card>(toCopy.allCards);
		invertDeck 	= new ArrayList<Card>(toCopy.invertDeck);
		// For debugging, to check the new Decks have the right size
		//int number = allCards.size()+invertDeck.size();
		//System.out.println("New Deck has cards: " + number + "(" + allCards.size() + ") (" + invertDeck.size() + ")");
	}

	// generate deck for the first time; can only be used once; should never be called outside constructor
	void initDeck () {
		if (initCounter) {
			System.out.println("The deck has been initialized.");
			for (Suit sui: Suit.values()) {
				for (Value val: Value.values()) {
					allCards.add(new Card(sui, val));
				}
			}
			initCounter = false;
		}
	}
	
	// this is used to shuffle the deck (reorder the elements in the arraylist)
	void shuffleDeck() {
		long seed = System.nanoTime();
		Collections.shuffle(allCards, new Random(seed));
		// this is for debugging
		 //printDeck();
	}

	// for debugging: print out entire deck
	void printDeck() {
		for(Card car: allCards) { System.out.print(car.printCard() + " "); }
		System.out.println("\nSize of Deck: " + allCards.size() + "\n");
	}

	// remove and return the top card, to place it in a hand (used in dealing, which is in Game)
	Card drawTop() { 
		if (allCards.size() != 0) {
			// as you deal from the deck, add a pointer to the invertDeck
			invertDeck.add(allCards.get(allCards.size()-1));
			return allCards.remove(allCards.size() - 1); 
		} else
			System.out.println("Error! The Deck is empty; cannot draw from it!");
		return null;
	}

	// Return the number of cards in the Deck
	int size() { return allCards.size(); }

	// return a card to the deck
	// be careful with pointer issues
	void restockDeck(Card returned) { allCards.add(returned); invertDeck.remove(returned); }

	// check that we have all 52 unique cards
	boolean checkDeck() {
		if (allCards.size() == 52 && invertDeck.size() == 0) return true;
		return false;
	}

}