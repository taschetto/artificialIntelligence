package ai;


public class MiniMaxPlayer implements Player {
	
	protected final int playerIndex;
	
	public MiniMaxPlayer(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	@Override
	public Move getNextMove(State state) throws Exception {
		//TODO Your code here
		return null;
	}

	@Override
	public int getPlayerType() {
		return MINIMAX_PLAYER;
	}

}
