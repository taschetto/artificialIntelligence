package ai;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {
	
	/**
	 * Random number generator
	 */
	private Random rand;
	
	public RandomPlayer() {
		this.rand = new Random();
	}

	@Override
	public Move getNextMove(State state) {
		return getRandomMove(state);
	}
	
	/**
	 * Returns a random move
	 * @param state The current board state
	 * @return A random move
	 */
	private Move getRandomMove(State state) {
		ArrayList<Move> availableMoves = new ArrayList<Move>();
		for( int row = 0; row < State.SIZE; row++ ) {
			for( int col = 0; col < State.SIZE; col++ ) {
				if( state.getState(row,col) == State.BLANK ) {
					availableMoves.add(new Move(row,col));
				}
			}
		}
		return (Move)availableMoves.get(rand.nextInt(availableMoves.size()));
	}

	@Override
	public int getPlayerType() {
		// TODO Auto-generated method stub
		return Player.RANDOM_PLAYER;
	}

}
