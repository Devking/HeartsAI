import java.util.*;

public class Deck {

	// use this to check if we have already initialized the deck once or not
	// so we do not generate duplicate decks
	boolean initCounter;


	ArrayList<Card> allCards = new ArrayList<Card>();


	// this currently is just being used for debugging to make sure that the enums work
	Card topOfDeck;

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

	Card drawTop() { return allCards.remove(allCards.size() - 1); }

}