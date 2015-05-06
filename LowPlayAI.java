// this AI will look at the hand and look at the cards currently on the board
// and play the lowest VALID card
// if no cards are on the board, will play the lowest non-hearts (if possible)
// if no cards are on the board and there are only hearts, play the lowest hearts

class LowPlayAI extends Player {
	
	LowPlayAI(String name) { super(name); System.out.println("Low Play AI ("+name+") initialized.");  }

	Card performAction () {
	}

}