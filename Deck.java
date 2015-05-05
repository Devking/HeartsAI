import java.util.*;

public class Deck {

	// use this to check if we have already initialized the deck once or not
	// so we do not generate duplicate decks
	boolean initCounter;

	// these are all of the cards currently in the deck
	ArrayList<Card> allCards = new ArrayList<Card>();

	// constructor for a deck: initialize the 52 cards and shuffle the deck
	Deck () { initCounter = true; initDeck(); shuffleDeck(); }

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
		printDeck();
	}

	// for debugging: print out entire deck
	void printDeck() {
		for(Card car: allCards) { System.out.println(car.getValue() + " of " + car.getSuit()); }
	}

	// remove and return the top card, to place it in a hand (used in dealing, which is in Game)
	Card drawTop() { 
		if (allCards.size() != 0) 
			return allCards.remove(allCards.size() - 1); 
		else
			System.out.println("Error! The Deck is empty; cannot draw from it!");
	}

	// return a card to the deck
	// be careful with pointer issues
	void restockDeck(Card returned) { allCards.add(returned); }

}