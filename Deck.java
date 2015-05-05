import java.util.*;

public class Deck {

	// use this to check if we have already initialized the deck once or not
	// so we do not generate duplicate decks
	boolean initCounter;

	int numberOfCards;
	// this currently is just being used for debugging to make sure that the enums work
	Card topOfDeck;
	Deck () { initCounter = true; numberOfCards = 52; topOfDeck = new Card(Suit.HEARTS, Value.ACE); initDeck(); makeDeck(); }
	//Deck () { initCounter = true; numberOfCards = 52; topOfDeck = new Card(Suit.HEARTS, Value.ACE); initDeck(); }
	void printNumber() { System.out.println(topOfDeck.getValue() + " of " + topOfDeck.getSuit());  }
	
	void initDeck () {
		if (initCounter) {
			System.out.println("The deck has been initialized.");
			initCounter = false;
		}
	}
	
	
	void makeDeck() {
		ArrayList<Card> allCards = new ArrayList<Card>();
		for (Suit sui: Suit.values()) {
			for (Value val: Value.values()) {
				allCards.add(new Card(sui, val));
			}
		}
		for(Card car: allCards) {
		    System.out.println(car.getValue() + " of " + car.getSuit()); 
		}
	}
	//ArrayList<String> al = new ArrayList<String>();
	//System.out.println("Initial size of al: " + al.size());
    //al.add("C");
    //al.add("A");
    //al.add("E");
    //al.add("B");
    //al.add("D");
    //al.add("F");
    //al.add(1, "A2");
    //System.out.println("Size of al after additions: " + al.size());

    // display the array list
    //System.out.println("Contents of al: " + al);
    // Remove elements from the array list
    //al.remove("F");
    //al.remove(2);
    //System.out.println("Size of al after deletions: " + al.size());
    //System.out.println("Contents of al: " + al);

}