class Card implements Comparable<Card> {

	Suit suit;
	Value value;
	String shorthand;

	// All cards MUST be constructed with a suit and value!
	Card (Suit thisSuit, Value thisValue) { 
		suit = thisSuit; 
		value = thisValue;
		setShorthand();
	}

	// This will get the "shorthand" display for this specific card
	// We only need to call this once, on instantiation
	void setShorthand () {
		shorthand = "";
		switch(value) {
			case Value.TWO: shorthand += "2"; break;
			case Value.THREE: shorthand += "3"; break;
			case Value.FOUR: shorthand += "4"; break;
			case Value.FIVE: shorthand += "5"; break;
			case Value.SIX: shorthand += "6"; break;
			case Value.SEVEN: shorthand += "7"; break;
			case Value.EIGHT: shorthand += "8"; break;
			case Value.NINE: shorthand += "9"; break;
			case Value.TEN: shorthand += "10"; break;
			case Value.JACK: shorthand += "J"; break;
			case Value.QUEEN: shorthand += "Q"; break;
			case Value.KING: shorthand += "K"; break;
			case Value.ACE: shorthand += "A"; break;
		}
		switch(suit) {
			case Suit.HEARTS: shorthand += "\u2661"; break;
			case Suit.SPADES: shorthand += "\u2660"; break;
			case Suit.CLUBS: shorthand += "\u2663"; break;
			case Suit.DIAMONDS: shorthand += "\u2662"; break;
		}
	}

	// Getter functions for the suit, value, and identity of this card
	Suit getSuit() { return suit; }
	Value getValue() { return value; }
	String printCard() { return getValue() + " of " + getSuit(); }
	String printCardShort() { return shorthand; }

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