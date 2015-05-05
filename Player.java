import java.util.ArrayList;

abstract class Player {
	String name;
	ArrayList<Card> hand = new ArrayList<Card>();

	Player (String id) { name = id; }

	// for drawing cards from the deck
	void addToHand (Card newCard) { hand.add(newCard); }

	// prints the hand that the player currently has
	void printHand () {
		System.out.println(name + "`s hand:");
		for (Card c : hand) {
			c.printCard();
			System.out.print(" | ");
		}
		System.out.println("\nSize of Hand: " + hand.size() + "\n");
	}

	void clearHand () { hand.clear(); }

}