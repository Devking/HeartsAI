class HumanPlayer extends Player {
	
	HumanPlayer (String name) { super(name); System.out.println("Human player ("+name+") initialized."); }

	Card performAction () {
		System.out.print(super.name + " plays ");
		hand.get(0).printCard();
		System.out.println(".\n");
		return hand.remove(0);
	}

}