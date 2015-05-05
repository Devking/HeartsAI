class Card implements Comparable<Card> {

	// All cards MUST be constructed with a suit and value!
	Card (Suit thisSuit, Value thisValue) { suit = thisSuit; value = thisValue; }

	Suit getSuit() { return suit; }
	Value getValue() { return value; }

	void printCard() { System.out.print( getValue() + " of " + getSuit()); }
	
	Suit suit;
	Value value;

	public int compareTo (Card other) {
		if (suit.compareTo(other.getSuit()) == 0) 
			return value.compareTo(other.getValue());
		return suit.compareTo(other.getSuit());
	}

	public boolean equals(Card other) {
		return suit.equals(other.getSuit()) && value.equals(other.getValue());
	}

}