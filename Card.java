class Card implements Comparable<Card> {

	Suit suit;
	Value value;

	// All cards MUST be constructed with a suit and value!
	Card (Suit thisSuit, Value thisValue) { suit = thisSuit; value = thisValue; }

	// Getter functions for the suit, value, and identity of this card
	Suit getSuit() { return suit; }
	Value getValue() { return value; }
	String printCard() { return getValue() + " of " + getSuit(); }

	// Overriden method for comparing cards by suit (for sorting hands)
	public int compareTo (Card other) {
		if (suit.compareTo(other.getSuit()) == 0) 
			return value.compareTo(other.getValue());
		return suit.compareTo(other.getSuit());
	}

	// Overriden method to check if cards are the same
	public boolean equals(Card other) {
		return suit.equals(other.getSuit()) && value.equals(other.getValue());
	}

}