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
	
		deck.shuffleDeck();
		playerOrder = new ArrayList<Player>();
		playerOrder.add(p1);
		playerOrder.add(p2);
		playerOrder.add(p3);
		playerOrder.add(p4);

		//deck.printDeck();
		// deck.checkDeck();

		for (Player p : playerOrder) { p.clearHand(); }
		// pass out all cards to the 4 players
		for (int i = 0; i < 13; i++) {
			for (Player p : playerOrder) { p.addToHand ( deck.drawTop() ); }
		}
		// sort all hands
		for (Player p : playerOrder) { p.sortHand(); }

		// for debugging:
		// for (Player p : playerOrder) { p.printHand(); }

		// be careful with copy/move semantics
		oldDeck = deck;
		//oldDeck.printDeck();

		// pick first player (the one whose first card is two of clubs)
		// and add everyone into the playing queue
		for (int i = 0; i < 4; i++) {
			if (playerOrder.get(i).hasTwoOfClubs() ) { firstPlayer = i; }
		}

		// debug message
		System.out.println(playerOrder.get(firstPlayer).getName() + " has the two of clubs.");

		// passing cards at start of game -- for now, no passing
	}

	void playGame() {

		System.out.println("The game has started.");

		// for all 13 rounds
		for (int i = 1; i < 14; i++) {
			System.out.println("\n--------------------------------------------");
			System.out.println("Round #" +i+":");

			// go through actions for all four players (ordered based on firstPlayer)
			for (int j = 0; j < 4; j++) {
				int index = (j+firstPlayer) % playerOrder.size();
				
				playerOrder.get(index).printHand();

				// now find a way to do player action, for both humans and AI
				oldDeck.restockDeck( playerOrder.get(index).performAction() );
			}

			// update who is firstPlayer
			// right now, for debugging, we move the firstPlayer over by 1 every round
			firstPlayer = (firstPlayer+1) % playerOrder.size();

		}

		System.out.println("The game has ended.");
		//oldDeck.printDeck();

	}

}