// this AI will look at the hand and look at the cards currently on the board
// and play the lowest VALID card
// if no cards are on the board, will play the lowest non-hearts (if possible)
// if no cards are on the board and there are only hearts, play the lowest hearts

import java.util.ArrayList;

class LowPlayAI extends Player {
	
	LowPlayAI(String name) { super(name); System.out.println("Low Play AI ("+name+") initialized.");  }

	boolean setDebug() { return false; }

	// NOTE: performAction() must REMOVE the card from the hand
	// we would not want this to be the case in the future
	Card performAction (State masterCopy) {
		// For human debugging: print the hand
		printHand();
		// Get the first suit that was played this round
		Suit firstSuit = getFirstSuit(masterCopy.currentRound);
		// If no cards were played this round, just play the smallest card in the hand
		if (firstSuit == null) return hand.remove(0);

		// Remove the smallest card of the correct suit
		for (int i = 0; i < hand.size(); i++)
			if (hand.get(i).getSuit() == firstSuit) return hand.remove(i);

		// If no cards of that suit, then play the smallest card in the hand
		return hand.remove(0);
	}

}