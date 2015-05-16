// The ultimate MCTS player

import java.util.*;
import java.util.ArrayList;

class MCTSPlayer extends Player {

	// Instead of changing the hand, we will change the playoutHand
	// when performing random playouts
	ArrayList<Card> playoutHand;
	Random rng;

	MCTSPlayer(String name) { 
		super(name); 
		System.out.println("Low Play AI ("+name+") initialized."); 
		playoutHand = new ArrayList<Card>(hand);
		rng = new Random();
	}

	// NOTE: performAction() must REMOVE the card from the hand
	// we would not want this to be the case in the future
	Card performAction (State gameCopy) {
		// For human debugging: print the hand
		printHand();
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