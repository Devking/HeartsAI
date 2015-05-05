class Card {

	// All cards MUST be constructed with a suit and value!
	Card (Suit thisSuit, Value thisValue) { suit = thisSuit; value = thisValue; }

	Suit getSuit() { return suit; }
	Value getValue() { return value; }

	void printCard() { System.out.println( getValue() + " of " + getSuit()); }
	
	Suit suit;
	Value value;

}