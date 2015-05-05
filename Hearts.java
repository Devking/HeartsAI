public class Hearts {
	public static void main(String[] args) {
		System.out.println("Welcome to Hearts version 0.0.1.");
		Deck thing = new Deck();
		Game round = new Game();
		thing.printNumber();
		thing.drawTop();
		thing.printNumber();
		thing.drawTop();
		thing.printNumber();
		thing.drawTop();
	}
}