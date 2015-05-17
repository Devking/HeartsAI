// The ultimate MCTS player

import java.util.*;
import java.util.ArrayList;

class MCTSPlayer extends Player {

	// Instead of changing the hand, we will change the playoutHand
	// when performing random playouts -- notice: these are not picked from the gameCopy deck
	// Remember to "reload" these per full game iteration
	ArrayList<Card> playoutHand;

	Random 			rng;
	final int 		noIterations = 26; 		// How many times we go through MCTS before making a decision
	final int 		expansionDepth = 3; 	// How many nodes to expand to before doing random playouts
	Node 			root;

	public class Node {
		State 			thisState;
		ArrayList<Card> currentHand;
		int 			bestReward;			
		int 			visitCount;
		Node 			parent;
		Node[] 			children;
		int 			depth;

		Node (State s, ArrayList<Card> hand, Node par) {
			thisState = s;
			currentHand = hand;
			bestReward = 0;
			visitCount = 0;
			parent = par;
			children = new Node[hand.size()]; 		// largest amount of children is # of cards in the hand
			if (par != null) depth = par.depth+1;
			else depth = 0;
		}
	}

	MCTSPlayer(String name) { 
		super(name); 
		System.out.println("MCTSPlayer AI ("+name+") initialized."); 
		playoutHand = new ArrayList<Card>(hand);	// Need to call this every performAction()
		rng = new Random();
	}

	boolean setDebug() { return false; }

	// Used to check if all the cards in this player's hand is hearts
	boolean hasAllHearts(ArrayList<Card> hand) {
		boolean flag = true;
		for (Card c : hand) { if (c.getSuit() != Suit.HEARTS) flag = false; }
		return flag;
	}

	// Note that children can only consist of valid plays
	// This is the skeleton of the entire MCTS algorithm
	int runMCTS (State origState) {
		root = new Node(origState, playoutHand, null);
		for (int i = 0; i < noIterations; i++) {
			Node expanded = treePolicy(root);
			double valueChange = assignReward(expanded);
			backProp(expanded, valueChange);
		}
		return bestRewardChild(root);
	}

	// Part of the process of selecting nodes to expand next (that is not random playout)
	Node treePolicy(Node roNode) {
		Node thisNode = roNode;
		// Go through this node
		while (thisNode.thisState.isGameValid() && expansionDepth > thisNode.depth) {
			Suit firstSuit = getFirstSuit(thisNode.thisState.currentRound);
			SuitRange range = getSuitRange(firstSuit, thisNode.currentHand);
			// Get the correct range of cards that we can possibly test for
			int firstIndex = range.startIndex;
			int lastIndex = range.endIndex;
			// if firstSuit == null, then this is the first move
			if (firstSuit == null) {
				// If hearts has broken or only hearts remain, we can play any move
				if (thisNode.thisState.hasHeartsBroken || hasAllHearts(thisNode.currentHand)) {
					firstIndex = 0;
					lastIndex = thisNode.currentHand.size();
				} else {
					// Hearts has not broken, and we have at least one non-hearts card we must play
					SuitRange heartsRange = getSuitRange(Suit.HEARTS, thisNode.currentHand);
					// If we have no hearts, we can play anything
					if (heartsRange.startIndex == -1) {
						firstIndex = 0;
						lastIndex = thisNode.currentHand.size();
					} else {
						// Otherwise, we need to eliminate the hearts range
						lastIndex = heartsRange.startIndex;
					}
				}
			} else if (firstIndex == -1) {
				// If firstIndex is -1 and firstSuit != null, then we don't have that suit in our hand
				firstIndex = 0;
				lastIndex = thisNode.currentHand.size();
			}
			// Visit all valid cards at least once first
			for (int i = firstIndex; i < lastIndex; i++) {
				// Notice: This will leave some children to be null, since they are invalid
				if (thisNode.children[i] == null) {
					return expandTree(thisNode, i);
				}
			}
			thisNode = bestChild(thisNode, 0.1);
		}
		return thisNode;
	}

	// This method actually creates and adds children to the tree (and runs the first step of advance)
	Node expandTree (Node roNode, int childNo) {
		State childState = new State(roNode.thisState);
		ArrayList<Card> childHand = new ArrayList<Card>(roNode.currentHand);
		// Remove the card from the hand of the child
		// Should probably set a debug here
		int debug = childState.advance( childHand.remove(childNo), childHand );
		if (debug == -1) {System.out.println("Error, we've made a mistake.");}
		// Create a new child node that corresponds to the card we have just successfully played
		roNode.children[childNo] = new Node(childState, childHand, roNode);
		return roNode.children[childNo];
	}

	// Uses UCT function to see which is the best child to visit next during tree expansion
	Node bestChild (Node someNode, double weight) {
		return someNode;
	}

	// At this point, the tree expansion will have occured -- now we will do random playouts of the game
	double assignReward(Node baseNode) {
		return 0;
	}

	// At this point, tree expansion and random playouts have both occured -- propagate the rewards back up
	void backProp (Node baseNode, double value) {

	}

	// Pick the child of the root with the highest reward
	int bestRewardChild(Node roNode) {
		int highestReward = -999999;
		int bestChildNo = 0; 				// Notice this may be an issue
		for (int i = 0; i < roNode.children.length; i++) {
			if (roNode.children[i] != null) {
				if (roNode.children[i].bestReward > highestReward) {
					highestReward = roNode.children[i].bestReward;
					bestChildNo = i;
				}
			}
		}
		return bestChildNo;
	}

	// NOTE: performAction() must REMOVE the card from the hand
	// we would not want this to be the case in the future

	// Once gameCopy gets to !isGameValid(), then one game has ended 
	// You can check gameCopy.getScore() to get the total score from this game

	// Remember to copy masterCopy
	Card performAction (State masterCopy) {
		// If very first move, play the two of clubs (will be first card in hand)
		if (masterCopy.firstMove()) 
			return hand.remove(0);
		playoutHand.clear();
		for (Card c : hand) playoutHand.add(c.copy());
		// For human debugging: print the hand
		printHand();
		// Actually play the card, after doing MCTS
		return hand.remove(runMCTS(masterCopy));
	}

}