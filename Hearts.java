public class Hearts {
	public static void main(String[] args) {
		System.out.println("Welcome to Hearts version 0.0.3.");

		// separate deck and four players from game
		Deck thing = new Deck();

		// assume this order is clockwise
		Player p1 = new HumanPlayer("Wells");
		Player p2 = new HumanPlayer("Jai");
		Player p3 = new HumanPlayer("Ant");
		Player p4 = new HumanPlayer("Julian");

		// pass in four players and deck to game
		Game round = new Game(thing, p1, p2, p3, p4);
		round.playGame();

	}
}