import java.util.ArrayList;

class Game {

	// should get four players and one deck
	// be sure to shuffle deck at the start of each game

	ArrayList<Player> playerOrder;

	// every round we just check who 'firstplayer' is and go around the 
	// arraylist like a circular queue -- and then modify firstPlayer every round
	int firstPlayer;
	
	Deck oldDeck;

	Game (Deck deck, Player p1, Player p2, Player p3, Player p4) {

		System.out.println("Starting new game...\n"); 
	
		deck.shuffleDeck();
		playerOrder = new ArrayList<Player>();
		playerOrder.add(p1);
		playerOrder.add(p2);
		playerOrder.add(p3);
		playerOrder.add(p4);

		deck.printDeck();
		// deck.checkDeck();

		for (Player p : playerOrder) { p.clearHand(); }
		// pass out all cards to the 4 players
		for (int i = 0; i < 13; i++) {
			for (Player p : playerOrder) { p.addToHand ( deck.drawTop() ); }
		}
		// sort all hands
		for (Player p : playerOrder) { p.sortHand(); }

		// for debugging:
		for (Player p : playerOrder) { p.printHand(); }

		// be careful with copy/move semantics
		oldDeck = deck;
		oldDeck.printDeck();

		// pick first player (the one whose first card is two of clubs)
		// and add everyone into the playing queue
		for (int i = 0; i < 4; i++) {
			if (playerOrder.get(i).hasTwoOfClubs() ) { firstPlayer = i; }
		}

		// debug message
		System.out.println(playerOrder.get(firstPlayer).getName() + " has the two of clubs.");

		// passing cards at start of game -- for now, no passing
	}



}