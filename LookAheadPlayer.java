// LookAheadPlayer - play all of the valid cards once
// do a full playthrough of the game after that card is played

// worst-case: second round, allow opening move
// must test all 12 cards, for 12 rounds each -- 144 turns to make 1 decision

import java.util.*;
import java.util.ArrayList;

class LookAheadPlayer extends Player {

	// Instead of changing the hand, we will change the playoutHand
	// when performing random playouts
	ArrayList<Card> playoutHand;
	Random rng;

	LookAheadPlayer(String name) { 
		super(name); 
		System.out.println("LookAheadPlayer AI ("+name+") initialized."); 
		playoutHand = new ArrayList<Card>(hand);
		for (Card c : hand) playoutHand.add(c.copy());
		rng = new Random();
	}

	boolean setDebug() { return false; }

	// Used to check if all the cards in this hand is hearts
	boolean hasAllHearts(ArrayList<Card> hand) {
		boolean flag = true;
		for (Card c : hand) { if (c.getSuit() != Suit.HEARTS) flag = false; }
		return flag;
	}

	// For random playout of the game to the end
	int playoutGame(State gameCopy, ArrayList<Card> gameHand) {
		int totalpoints = 0;
		while (gameCopy.isGameValid()) {
			//System.out.println("Playout!");
			Suit firstSuit = getFirstSuit(gameCopy.currentRound);
			SuitRange range = getSuitRange(firstSuit, gameHand);
			if (range.getRange() == 0) {
				// Deal with playing hearts
				//System.out.println(gameHand.size());
				int index = rng.nextInt(gameHand.size());
				while (gameCopy.firstInRound() && !gameCopy.hasHeartsBroken && gameHand.get(index).getSuit() == Suit.HEARTS && !hasAllHearts(gameHand)) {
					//System.out.println("BAD: " + index);
					index = rng.nextInt(gameHand.size());
				}
				totalpoints += gameCopy.advance(gameHand.remove(index), gameHand);
			} else {
				int index = rng.nextInt(range.getRange());
				totalpoints += gameCopy.advance(gameHand.remove(range.startIndex+index), gameHand);
			}
		}
		return totalpoints;
	}

	Card performAction (State masterCopy) {

		playoutHand.clear();
		for (Card c : hand) playoutHand.add(c.copy());
		//System.out.println("PlayoutHand Size: " + playoutHand.size());

		// If this is the first move, then we must play the two of spades regardless
		if (masterCopy.firstMove())
			return hand.remove(0);

		// For human debugging: print the hand
		printHand();

		// Get the first suit that was played this round
		Suit firstSuit = getFirstSuit(masterCopy.currentRound);

		// Get range of possible choices for the correct suit
		SuitRange range = getSuitRange(firstSuit, hand);

		// Track what the best card index to play is, and what score it ended with
		int bestIndex = 0;
		if (range.startIndex != -1) bestIndex = range.startIndex;
		int lowestScore = 100;

		//System.out.println("Test for " + range.getRange() + " possibilites.");

		// If we don't have a card in that suit, or we are starting the round
		// Then we are allowed to play any card in our hand (check hasHeartsBroken)
		if (range.getRange() == 0) { 
			// Loop through all possible cards in the hand
			for (int i = 0; i < playoutHand.size(); i++) {
				// Be sure to remove cards from the game hand and not the playout hand (or else we won't have them later!)
				ArrayList<Card> gameHand = new ArrayList<Card>(playoutHand);
				// If first card in round, and hearts has not broken, we stop before playing Hearts
				// Note: Sorting of the hand will put all Hearts cards at the end, which allows this to work
				if (masterCopy.firstInRound() && !masterCopy.hasHeartsBroken && gameHand.get(i).getSuit() == Suit.HEARTS) break;
				// Copy the game state for this situation
				State gameCopy = new State(masterCopy);
				// Get the points that this move leads to
				int score = gameCopy.advance(gameHand.remove(i), gameHand);
				// Add the points that a random playout of the game will lead to
				score += playoutGame(gameCopy, gameHand);
				// Notice: If there's a tie, we'll want to play the earlier card!
				if (score < lowestScore) {
					lowestScore = score;
					bestIndex = i;
				}
			}
			// After running through all of the possibilities, return the best index
			return hand.remove(bestIndex);
		}

		// This case occurs only if we have a limited scope of cards to pick from
		for (int i = range.startIndex; i < range.endIndex; i++) {
			ArrayList<Card> gameHand = new ArrayList<Card>(playoutHand);
			//System.out.println("Game Hand Size:" + gameHand.size());
			if (masterCopy.firstInRound() && !masterCopy.hasHeartsBroken && gameHand.get(i).getSuit() == Suit.HEARTS) break;
			State gameCopy = new State(masterCopy);
			int score = gameCopy.advance(gameHand.remove(i), gameHand);
			score += playoutGame(gameCopy, gameHand);
			if (score < lowestScore) {
				lowestScore = score;
				bestIndex = i;
			}
		}

		return hand.remove(bestIndex);
	}

}