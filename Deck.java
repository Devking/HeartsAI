class Deck {
	int numberOfCards;
	//ArrayList<Card> allCards;

	// this currently is just being used for debugging to make sure that the enums work
	Card topOfDeck;
	Deck () { numberOfCards = 52; topOfDeck = new Card(Suit.HEARTS, Value.ACE); }
	void printNumber() { System.out.println(topOfDeck.getValue()); }
}