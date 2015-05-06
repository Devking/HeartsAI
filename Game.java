import java.util.ArrayList;

class Game {

	ArrayList<Player> playerOrder;	// think of this as a circular queue of the 4 players
	int firstPlayer;				// the index of the first player for this round
	Deck cardsPlayed;				// cards that have already been played -- replace them into the deck
	ArrayList<Card> currentRound;   // cards currently played on the table

	// Every game must have four players and one deck!
	// Note: This WILL NOT shuffle the deck or deal the cards here
	// We ONLY do that upon playing a new game
	Game (Deck deck, Player p1, Player p2, Player p3, Player p4) {
		playerOrder = new ArrayList<Player>();
		playerOrder.add(p1);
		playerOrder.add(p2);
		playerOrder.add(p3);
		playerOrder.add(p4);
		firstPlayer = 0;
		cardsPlayed = deck;
		currentRound = new ArrayList<Card>();
	}

	// Call this every time a new game is played to shuffle the deck and clear player hands
	void initNewGame () {
		cardsPlayed.shuffleDeck();
		// cardsPlayed.printDeck(); // debugging to make sure the deck is correct
		// cardsPlayed.checkDeck(); // we need a way to check that all 52 cards are here correctly
		// clear the hands of all the players (to make sure they're not holding anything already!)
		for (Player p : playerOrder) { p.clearHand(); }
		// pass out all cards to the 4 players
		for (int i = 0; i < 13; i++) { for (Player p : playerOrder) { p.addToHand ( cardsPlayed.drawTop() ); } }
		// sort all hands
		for (Player p : playerOrder) { p.sortHand(); }
		// for (Player p : playerOrder) { p.printHand(); }		// for debugging to check all the hands are valid
		// cardsPlayed.printDeck();								// for debugging to check all cards have been dealt
		// pick first player (the one who holds the two of clubs)
		for (int i = 0; i < 4; i++) { if (playerOrder.get(i).hasTwoOfClubs() ) { firstPlayer = i; } }
		// print message to say who plays first
		System.out.println(playerOrder.get(firstPlayer).getName() + " has the two of clubs and will play first.\n");
		// just to be safe, clear the arraylist of cards on the table
		currentRound.clear();
		// passing cards at start of game -- for now, no passing, but we would add it here
		// passCards();
	}

	// Print the cards that were played so far this round
	// be sure to pass in the index of the first player to get the names right
	void printRound(int firstPlayer) {
		System.out.println("Cards played this round:");
		if (currentRound.size() == 0) {
			System.out.println("No cards have been played this round.");
		}
		for (int i = 0; i < currentRound.size(); i++) {
			int index = (i+firstPlayer) % playerOrder.size();
			System.out.println(playerOrder.get(index).getName() + " played " + currentRound.get(i).printCard() + ".");
		}
		System.out.println();
	}

	// Return the index of the next player who will play // the player who takes this round
	// Pass in the index of the current first player (to check who played what card)
	// NOTE: This MUST return an int from 0 to 3! ALWAYS DO % playerOrder.size();
	int findTaker (int firstPlayer) {
		return (firstPlayer+1)%playerOrder.size();
	}

	// Go through the cards from the currentRound and calculate their point values
	int calculatePoints() {
		int points = 0;
		for (Card c : currentRound) {
			if (c.getSuit() == Suit.HEARTS) points++;
			if (c.getValue() == Value.QUEEN && c.getSuit() == Suit.SPADES) points += 13;
		}
		return points;
	}

	// Print out how many points each player currently has
	void printPoints() {
		for (Player p : playerOrder) {
			System.out.println(p.getName() + " has " + p.getPoints() + " points.");
		}
		System.out.println();
	}

	// Call this whenever you want to start a completely new game and play through it
	void playNewGame() {
		// We must call this to shuffle the deck and deal cards to all the players
		initNewGame();
		// For all 13 rounds of the game...
		for (int i = 1; i < 14; i++) {
			System.out.println("--------------------------------------------");
			System.out.println("Round #" +i+":");
			System.out.println("--------------------------------------------\n");
			// clear the table for this round
			currentRound.clear();
			// go through actions for all four players (ordered based on firstPlayer)
			for (int j = 0; j < 4; j++) {
				// use index to determine the index of the player currently playing
				int index = (j+firstPlayer) % playerOrder.size();
				//printRound(firstPlayer); // for debugging: print the cards that were played this round

				// ideally, we should pass in (a) cardsPlayed, (b) currentRound, (c) scores
				// we should not be passing in the hands of other players (hidden information)
				// each player will already know what cards they have
				Card playedCard = playerOrder.get(index).performAction();
				// we need to check if the playedCard is valid, given this currentRound
				// if it is not valid, we need to loop over it again and get the player to pick another card
				// checkRound(playedCard);

				System.out.println(playerOrder.get(index).getName() + " played " + playedCard.printCard() + ".");
				// we *could* printHand() here, but it's better to have each player do that
				// based on whether or not that player specifically needs to print hand or not

				// add the played card to the currentRound (put the card on the table for all to see)
				// BE CAREFUL! We will be adding a direct pointer to the card here!
				currentRound.add(playedCard);
				// this will take the card that is played and add it back to the deck
				cardsPlayed.restockDeck( playedCard );
				// flush the screen (this is just for convenience for human players)
				/*
				final String ANSI_CLS = "\u001b[2J";
        		final String ANSI_HOME = "\u001b[H";
        		System.out.print(ANSI_CLS + ANSI_HOME);
        		System.out.flush();
        		*/
			}

			// printRound(firstPlayer); 	// for debugging: use this method to see what cards were played this round

			// 1. findTaker() will update who took the cards this round
			// 2. calculatePoints() will calculate how many points this round consisted of
			// 3. addPoints() will add those points to the correct player
			firstPlayer = findTaker(firstPlayer);
			int points = calculatePoints();
			playerOrder.get(firstPlayer).addPoints(points);
			System.out.println("\n" + playerOrder.get(firstPlayer).getName() + " took " + points + " points this round.\n");
			printPoints();

		}

		System.out.println("This game has ended.");
		//cardsPlayed.printDeck(); 		// debugging to make sure that all cards have returned to the deck

	}

}