# Hearts

## Class Structure
- Value enum  
- Suit enum  
- Card class (for each individual card)  
- Deck class (for the "deck" to deal hands)  
- Hand class (for each of the hands)  
- Game class (plays one game)  
- Player abstract class (defines base functionality for any player)  
- HumanPlayer class extends Player (`performAction()` allows human input)  
- Hearts class (this is the main file that brings it all together)  

## Version History

0.0.1 &mdash; just setting all of the infrastructure up and testing some java things  
0.0.2 &mdash; implemented abstract player as well as basic human player skeleton  
0.0.3 &mdash; begun implementation for game playouts  
0.0.4 &mdash; able to do multiple playouts of the game  
0.0.5 &mdash; able to keep track of cards played for each round  
0.0.6 &mdash; game keeps track of the scores of each player now!  
0.0.7 &mdash; game keeps track of scores between games; games do initialization properly now  
0.0.8 &mdash; HumanPlayer can now select which card to play, with Exception Handling!  
0.0.9 &mdash; Added card "shorthand" display using unicode characters (and reordered suits)  
0.0.10 &mdash; Game now checks if the played card by the Player is valid! (for ALL cases)  
0.0.11 &mdash; Game now checks who the "winner" of each round is!  
0.1.0 &mdash; First full game implemented! (With no state tracking functionality for AI)  
0.2.0 &mdash; Game now passes in `currentRound` to `performAction()`  
0.3.0 &mdash; Working Low-Play AI has been completed  
0.4.0 &mdash; Added score-tracking statistics  
0.5.0 &mdash; Shooting the moon is implemented -- minor bug fixes as well  
0.6.0 &mdash; Added a copy constructor for the Deck and began working on the State class  
0.6.1 &mdash; All Players now take parameters of the `State` class, State constructor now finished  
0.6.2 &mdash; Added functionality to return the range of cards in any given suit  


### Things To Do

- Finish state representations to be passed in to `performAction()`  
- Get working Look-Ahead FSM AI  
- Get working MCTS AI  

## Version Goals

1.0.0 &mdash; first full working game version (with all human players)  
1.2.0 &mdash; full working game with basic Look-ahead FSM AI
1.3.0 &mdash; full working game with basic MCTS AI