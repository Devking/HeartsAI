import java.util.ArrayList;

class Game {

	// should get four players and one deck
	// be sure to shuffle deck at the start of each game

	ArrayList<Player> playerOrder;

	// every round we just check who 'firstplayer' is and go around the 
	// arraylist like a circular queue -- and then modify firstPlayer every round
	int firstPlayer;

	Deck cardsPlayed;
	ArrayList<Card> currentRound;

	Game (Deck deck, Player p1, Player p2, Player p3, Player p4) {
	
		deck.shuffleDeck();
		playerOrder = new ArrayList<Player>();
		playerOrder.add(p1);
		playerOrder.add(p2);
		playerOrder.add(p3);
		playerOrder.add(p4);

		// deck.printDeck();
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
		cardsPlayed = deck;
		//cardsPlayed.printDeck();

		// pick first player (the one whose first card is two of clubs)
		// and add everyone into the playing queue
		for (int i = 0; i < 4; i++) {
			if (playerOrder.get(i).hasTwoOfClubs() ) { firstPlayer = i; }
		}

		// debug message
		System.out.println(playerOrder.get(firstPlayer).getName() + " has the two of clubs and will play first.\n");
		currentRound = new ArrayList<Card>();

		// passing cards at start of game -- for now, no passing


	}

	// print the cards that were played so far this round
	// be sure to pass in the index of the first player to get the names right
	void printRound(int firstPlayer) {
		System.out.println("Cards played this round:");
		if (currentRound.size() == 0) {
			System.out.println("No cards have been played this round.");
		}
		for (int i = 0; i < currentRound.size(); i++) {
			int index = (i+firstPlayer) % playerOrder.size();
			System.out.println(playerOrder.get(index).getName() + " played " + currentRound.get(i).printCard());
		}
		System.out.println();
	}

	// return the index of the next player who will play // the player who takes this round
	// pass in the index of the current first player
	// we will go through currentRound to check who "wins"
	// NOTE: you MUST return an int from 0 to 3! ALWAYS DO % playerOrder.size();
	int findTaker (int firstPlayer) {
		return (firstPlayer+1)%playerOrder.size();
	}

	// go through the cards from the currentRound and calculate their point values
	// then give those points to the user with index "loser"
	void givePoints(int loser) {
		int points = 0;
		for (Card c : currentRound) {
			if (c.getSuit() == Suit.HEARTS) points++;
			if (c.getValue() == Value.QUEEN && c.getSuit() == Suit.SPADES) points += 13;
		}
		playerOrder.get(loser).addPoints(points);
	}

	void printPoints() {
		for (Player p : playerOrder) {
			System.out.println(p.getName() + " has " + p.getPoints() + " points.");
		}
		System.out.println();
	}

	void playGame() {

		// we need to make sure that this is only called ONCE, after the game has been initialized

		// for all 13 rounds
		for (int i = 1; i < 14; i++) {
			System.out.println("--------------------------------------------");
			System.out.println("Round #" +i+":");
			System.out.println("--------------------------------------------\n");

			currentRound.clear();

			// go through actions for all four players (ordered based on firstPlayer)
			for (int j = 0; j < 4; j++) {
				// use index to determine the index of the player currently playing
				int index = (j+firstPlayer) % playerOrder.size();

				// for debugging: print the cards that were played this round
				//printRound(firstPlayer);

				Card playedCard = playerOrder.get(index).performAction();
				System.out.println(playerOrder.get(index).getName() + " played " + playedCard.printCard() + ".\n");

				// we *could* printHand() here, but it's better to have each player do that
				// based on whether or not that player specifically needs to print hand or not

				// add the played card to the currentRound
				// BE CAREFUL! We will be adding a direct pointer to the card here!
				currentRound.add(playedCard);

				// now find a way to do player action, for both humans and AI
				cardsPlayed.restockDeck( playedCard );

				// flush the screen (this is just for convenience for human players)
				/*
				final String ANSI_CLS = "\u001b[2J";
        		final String ANSI_HOME = "\u001b[H";
        		System.out.print(ANSI_CLS + ANSI_HOME);
        		System.out.flush();
        		*/
			}

			printRound(firstPlayer);

			// we will use this function to find the player who wins this round
			// we will assign the "firstPlayer" to this index, so they play first next
			// they also take the points associated with this round
			firstPlayer = findTaker(firstPlayer);
			givePoints(firstPlayer);
			printPoints();

		}

		System.out.println("The game has ended.");
		//cardsPlayed.printDeck();

	}

}