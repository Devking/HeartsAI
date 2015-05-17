public class Hearts {
	public static void main(String[] args) {
		System.out.println("Welcome to Hearts version 0.9.2.");
		System.out.println("(First fully playable version will be 1.0.0.)\n");

		// Initalize the deck of cards
		Deck thing = new Deck();

		// Assume this order is clockwise
		//Player p1 = new RandomPlayAI("Wells");
		Player p1 = new LowPlayAI("Wells");
		// Player p1 = new HumanPlayer("Wells");
		Player p2 = new RandomPlayAI("Jai");
		Player p3 = new LookAheadPlayer("Ant");
		Player p4 = new MCTSPlayer("Julian");

		// at the end of every game, we will have all the cards back in the deck
		// thing.printDeck();

		// Play Multiple Games
		int numberOfGames = 10;
		Game round = new Game(thing, p1, p2, p3, p4);
		for (int i = 1; i <= numberOfGames; i++) {
			System.out.println("\n--------------------------------------------");
			System.out.println("--------------------------------------------");
			System.out.println("--------------------------------------------");
			System.out.println("Playing Game #"+i);
			System.out.println("--------------------------------------------");
			System.out.println("--------------------------------------------");
			System.out.println("--------------------------------------------\n");
			round.playNewGame();
		}

	}
}