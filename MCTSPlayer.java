// The ultimate MCTS player

import java.util.*;
import java.util.ArrayList;

class MCTSPlayer extends Player {

	// Instead of changing the hand, we will change the playoutHand
	// when performing random playouts -- notice: these are not picked from the gameCopy deck
	// Remember to "reload" these per full game iteration
	ArrayList<Card> playoutHand;
	Random rng;

	MCTSPlayer(String name) { 
		super(name); 
		System.out.println("MCTSPlayer AI ("+name+") initialized."); 
		playoutHand = new ArrayList<Card>(hand);
		rng = new Random();
	}

	// NOTE: performAction() must REMOVE the card from the hand
	// we would not want this to be the case in the future

	// If you wish to run through multiple iterations of games, copy gameCopy first!


	// Once gameCopy gets to !isGameValid(), then one game has ended 
	// You can check gameCopy.getScore() to get the total score from this game


	Card performAction (State masterCopy) {

		// Make a copy of the masterCopy -- don't actually modify it
		State gameCopy = new State(masterCopy);

		// For human debugging: print the hand
		printHand();

		// If very first move, play the two of clubs (will be first card in hand)
		if (gameCopy.firstMove())
			return hand.remove(0);

		// Get the first suit that was played this round
		Suit firstSuit = getFirstSuit(gameCopy.currentRound);
		// If no cards were played this round, play a random card 
		// (note: this *may* in theory cause a deadlock, if it randomly picks hearts always)
		if (firstSuit == null) {
			int index = rng.nextInt(hand.size());
			return hand.remove(index);
		}

		// Remove a random card of the correct suit
		SuitRange range = getSuitRange(firstSuit);
		if (range.getRange() == 0) return hand.remove(rng.nextInt(hand.size()));
		int index = rng.nextInt(range.getRange());
		return hand.remove(range.startIndex+index);
	}

}