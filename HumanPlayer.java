class HumanPlayer extends Player {
	
	HumanPlayer (String name) { super(name); System.out.println("Human player ("+name+") initialized."); }

	Card performAction () {
		// print hand is only needed for debugging / human players
		//printHand();
		return hand.remove(0);
	}

}