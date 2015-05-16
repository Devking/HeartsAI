import java.util.ArrayList;

// This is what's passed in for each player
// in order to do full playouts of any one game.

// We will pass in three pieces of information:
// (1) Cards already played
// (2) Cards on the table
// (3) Scores of each of the players (this game)
// These are named the same as their copies in Game.java

class State {

	Deck 				cardsPlayed;
	ArrayList<Card> 	currentRound;
	ArrayList<Integer> 	playerScores;

	// Remember to make COPIES of what is passed in!
	State (Deck deck, ArrayList<Card> round, ArrayList<Integer> scores) {
		//cardsPlayed = 
		currentRound = new ArrayList<Card>(round);
		playerScores = new ArrayList<Integer>(scores);
	}
	
}