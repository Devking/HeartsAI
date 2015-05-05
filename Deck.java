class Deck {

	// use this to check if we have already initialized the deck once or not
	// so we do not generate duplicate decks
	boolean initCounter;

	int numberOfCards;
	//ArrayList<Card> allCards;

	// this currently is just being used for debugging to make sure that the enums work
	Card topOfDeck;
	Deck () { initCounter = true; numberOfCards = 52; topOfDeck = new Card(Suit.HEARTS, Value.ACE); initDeck(); }
	void printNumber() { System.out.println(topOfDeck.getValue()); }

	void initDeck () {
		if (initCounter) {
			System.out.println("The deck has been initialized.");
			initCounter = false;
		}
	}

}