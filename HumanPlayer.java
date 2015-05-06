class HumanPlayer extends Player {
	
	HumanPlayer (String name) { super(name); System.out.println("Human player ("+name+") initialized."); }

	Card performAction () {
		return hand.remove(0);
	}

}