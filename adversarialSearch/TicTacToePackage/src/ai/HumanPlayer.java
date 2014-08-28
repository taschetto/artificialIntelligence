package ai;

public class HumanPlayer implements Player {

	/**
	 * This constant is used to specify that a square is invalid.
	 */
	private static final int INVALID = -1;
	
	/**
	 * The square that the player has chosen.
	 */
	private Move chosenSquare;
	
	/**
	 * Flag specifying whether the human player has taken his/her action
	 * in the game.
	 */
	@SuppressWarnings("unused")
	private boolean actionTaken;
	
	private final TicTacToe game;
	
	public HumanPlayer(TicTacToe game) {
		chosenSquare = new Move();
		chosenSquare.row = INVALID;
		chosenSquare.col = INVALID;
		actionTaken = true;
		this.game = game;
	}
	
	@Override
	public Move getNextMove(State state) {
		try {
			return game.readMoveFromPlayer(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getPlayerType() {
		return HUMAN_PLAYER;
	}

}
