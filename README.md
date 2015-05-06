# Hearts

### Class Structure
- Value enum  
- Suit enum  
- Card class (for each individual card)  
- Deck class (for the "deck" to deal hands)  
- Hand class (for each of the hands)  
- Game class (plays one game)
- Player abstract class (defines base functionality for any player)  

- HumanPlayer class extends Player (`performAction()` allows human input)  

- Hearts class (this is the main file that brings it all together)  

### Version History

0.0.1 &mdash; just setting all of the infrastructure up and testing some java things  
0.0.2 &mdash; implemented abstract player as well as basic human player skeleton  
0.0.3 &mdash; begun implementation for game playouts  

### Version Goals

0.1.0 &mdash; full game implemented (with limited functionality)  
1.0.0 &mdash; first full working game version (with all human players)  
1.1.0 &mdash; full working game with basic low-play AI  
1.2.0 &mdash; full working game with basic FSM AI