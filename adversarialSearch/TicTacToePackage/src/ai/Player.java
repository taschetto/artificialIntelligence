package ai;

/**
 * A generic class representing a player in the TicTacToe Game
 * @author meneguzzi
 *
 */
public interface Player {
	/**
	 * These are constants to define the type of the player.
	 */
	public static final int HUMAN_PLAYER = 0;
	public static final int RANDOM_PLAYER = 1;
	public static final int MINIMAX_PLAYER = 2;
	public static final int ALPHABETA_PLAYER = 3;
	
	/**
	 * Returns the next move to be taken by the player given the current {@link State} of the world.
	 * @param state
	 * @return
	 * @throws Exception
	 */
	public Move getNextMove(State state) throws Exception;
	
	/**
	 * Gets the player type, where the player type can take on the values in the constants of the player
	 * interface.
	 * @return The player type
	 */
	public int getPlayerType();
}
