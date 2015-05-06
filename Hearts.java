public class Hearts {
	public static void main(String[] args) {
		System.out.println("Welcome to Hearts version 0.5.0.");
		System.out.println("(First fully playable version will be 1.0.0.)\n");

		// separate deck and four players from game
		Deck thing = new Deck();

		// assume this order is clockwise
		Player p1 = new LowPlayAI("Wells");
		Player p2 = new LowPlayAI("Jai");
		Player p3 = new HumanPlayer("Ant");
		Player p4 = new LowPlayAI("Julian");

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