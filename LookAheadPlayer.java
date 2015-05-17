// LookAheadPlayer

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
		rng = new Random();
	}

	boolean setDebug() { return false; }

	// NOTE: performAction() must REMOVE the card from the hand
	// we would not want this to be the case in the future

	// This will only play through ONE masterCopy game
	// Remember to remove cards from playoutHand when using masterCopy.advance()

	Card performAction (State masterCopy) {
		// For human debugging: print the hand
		printHand();
		// Get the first suit that was played this round
		Suit firstSuit = getFirstSuit(masterCopy.currentRound);
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