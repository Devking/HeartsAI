class Card {

	Card (Suit thisSuit, Value thisValue) { suit = thisSuit; value = thisValue; }

	Suit getSuit() { return suit; }
	Value getValue() { return value; }

	Suit suit;
	Value value;

}