public class Hearts {
	public static void main(String[] args) {
		System.out.println("Welcome to Hearts version 0.0.1.");

		// separate deck and four players from game
		Deck thing = new Deck();

		// pass in four players and deck to game
		Game round = new Game();

		
		thing.printNumber();
		thing.drawTop();
		thing.printNumber();
		thing.drawTop();
		thing.printNumber();
		thing.drawTop();
	}
}