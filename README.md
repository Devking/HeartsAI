# Hearts

## How To Run

**Using Command Line:**

1. Compile all of the source files by running `javac *.java` or `ant`
2. Run the Hearts program by running `java Hearts`

**Using an IDE**

1. Compile all of the source files using your IDE
2. Run the compiled `Hearts` program using your IDE

## Implementing and Adding Your Own Agents

To implement your own AI agent, all you need to do is write a new class that extends `Player`. As an example, you can take a look at the existing classes such as `LookAheadPlayer` or `RandomPlayAI`.

Once they are implemented, you can add them to the game by modifying `p1`, `p2`, `p3`, or `p4` in `Hearts.java` to instantiate a new agent of your class.

## Implemented Players

- HumanPlayer: Allows humans to play Hearts
- LowPlayAI: Picks the lowest card in a valid suit
- RandomPlayAI: Plays a random card in a valid suit
- LookAheadPlayer: Looks ahead a minimal amount of steps to make a decision
- MCTSPlayer: Uses MCTS to decide on the optimal next move

## Class Structure

- Value enum
- Suit enum
- Card class (for each individual card)
- Deck class (for the "deck" to deal hands)
- Hand class (for each of the hands)
- Player abstract class (defines base functionality for any player)
- HumanPlayer class extends Player (`performAction()` allows human input)
- State class (track state of the game and allow for random playouts)
- Game class (plays one game)
- Hearts class (this is the main file that brings it all together)

## Version History

0.0.1 &mdash;  Setting all of the infrastructure up and testing some java things

0.0.2 &mdash;  Implemented abstract player as well as basic human player skeleton

0.0.3 &mdash;  Begun implementation for game playouts

0.0.4 &mdash;  Able to do multiple playouts of the game

0.0.5 &mdash;  Able to keep track of cards played for each round

0.0.6 &mdash;  Game keeps track of the scores of each player now!

0.0.7 &mdash;  Game keeps track of scores between games; games do
initialization properly now

0.0.8 &mdash;  **HumanPlayer** can now select which card to play, with Exception Handling!

0.0.9 &mdash;  Added card "shorthand" display using unicode characters (and reordered suits)

0.0.10 &mdash; Game now checks if the played card by the Player is valid! (for ALL cases)

0.0.11 &mdash; Game now checks who the "winner" of each round is!

0.1.0 &mdash;  First full game implemented! (With no state tracking functionality for AI)

0.2.0 &mdash;  Game now passes in `currentRound` to `performAction()`

0.3.0 &mdash;  Working **Low-Play AI** has been completed

0.4.0 &mdash;  Added score-tracking statistics

0.5.0 &mdash;  Shooting the moon is implemented &mdash; minor bug fixes as well

0.6.0 &mdash;  Added a copy constructor for the Deck and began working on the State class

0.6.1 &mdash;  All Players now take parameters of the `State` class, State constructor now finished

0.6.2 &mdash;  Added functionality to return the range of cards in any given suit

0.6.3 &mdash;  **RandomPlayAI** has been implemented completely and is working

0.6.4 &mdash;  **MCTSPlayer** implementation has begun

0.6.5 &mdash;  **LookAheadPlayer** implementation has begun

0.7.0 &mdash;  Implementation for `advance()` within the State class has begun

0.7.1 &mdash;  `advance()` now checks for validity of the selected move

0.7.2 &mdash;  Design paradigm for multiple game playouts has been established

0.8.0 &mdash;  Finished preliminary version of `advance()` in the State class

0.8.1 &mdash;  Added functionality for displaying debug messages only for Human players

0.8.2 &mdash;  Fixed printout issue with shooting the moon &mdash; random AI able to shoot the moon!

0.9.0 &mdash;  LookAheadPlayer has `performAction()` implemented (but not `playoutGame()`)

0.9.1 &mdash;  Fixed shallow copy issues with Decks and hands

0.9.2 &mdash;  Playthroughs with state and lookahead player both work! Fixed minor issues

1.0.0 &mdash;  Full working game, with working State class, game playthroughs, and LookAhead AI

1.0.1 &mdash;  Finished skeleton of MCTS Player

1.0.2 &mdash;  Fixed bug with `advance()` method in the State class with returning accrued points

1.0.3 &mdash;  Most MCTS functions have been written; just need to deal with random playouts

1.0.4 &mdash;  Debugging process for MCTS &mdash; fixed an issue with `treePolicy()`

1.0.5 &mdash;  All MCTS functions except `assignReward()` now are fully functional

1.0.6 &mdash;  Preliminary completion of `assignReward()` function

1.0.7 &mdash;  MCTS completed and running &mdash; checking for validity of results now

1.1.0 &mdash;  MCTS player completed (but not optimized) for now

### Future Work

1. Reorganize file structure
2. Create debug flags for running games without any output (except final win statistics)
3. Decouple Hand and Player operations by creating a new Hand class
4. Fix how cards are removed from the hand/selected by Players
