class Card {

	enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	enum Value {
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}

	Card () {
		suit = Suit.HEARTS;
		value = Value.ACE;
	}

	Suit getSuit() { return suit; }
	Value getValue() { return value; }

	Suit suit;
	Value value;

}