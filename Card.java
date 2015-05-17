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
			case TWO: shorthand += "2"; break;
			case THREE: shorthand += "3"; break;
			case FOUR: shorthand += "4"; break;
			case FIVE: shorthand += "5"; break;
			case SIX: shorthand += "6"; break;
			case SEVEN: shorthand += "7"; break;
			case EIGHT: shorthand += "8"; break;
			case NINE: shorthand += "9"; break;
			case TEN: shorthand += "10"; break;
			case JACK: shorthand += "J"; break;
			case QUEEN: shorthand += "Q"; break;
			case KING: shorthand += "K"; break;
			case ACE: shorthand += "A"; break;
		}
		switch(suit) {
			case SPADES: shorthand += "\u2660"; break;
			case HEARTS: shorthand += "\u2661"; break;
			case DIAMONDS: shorthand += "\u2662"; break;
			case CLUBS: shorthand += "\u2663"; break;
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

	// Allow a copy function for cards
	Card copy() { return new Card(suit, value); } 

}
