class Game {

	// should get four players and one deck
	// be sure to shuffle deck at the start of each game

	Game (Deck deck, Player p1, Player p2, Player p3, Player p4) { 
		System.out.println("Starting new game...\n"); 
		deck.shuffleDeck();
		deck.printDeck();
		// deck.checkDeck();
		p1.clearHand();
		p2.clearHand();
		p3.clearHand();
		p4.clearHand();

		// pass out all cards to the 4 players
		for (int i = 0; i < 13; i++) {
			p1.addToHand ( deck.drawTop() );
			p2.addToHand ( deck.drawTop() );
			p3.addToHand ( deck.drawTop() );
			p4.addToHand ( deck.drawTop() );
		}

		// sort all hands
		

		// for debugging:
		p1.printHand();
		p2.printHand();
		p3.printHand();
		p4.printHand();

		// pick first player (the one whose first card is two of clubs)
		// and add everyone into the playing queue


		// passing cards at start of game -- for now, no passing
	}



}