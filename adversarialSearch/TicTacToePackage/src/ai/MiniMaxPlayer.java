package ai;

import java.util.ArrayList;


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
	
	private int Max(int a, int b)
	{
		return a > b ? a : b;
	}
	
	private int Min(int a, int b)
	{
		return a < b ? a : b;
	}	

	public Move MinimaxDecision(State state)
	{
		return null;
	}
	
	public int MaxValue(State state) throws Exception
	{
		if (this.TerminalTest(state))
			return this.Utility(state);
		
		int v = Integer.MIN_VALUE;
		
		for (Move move : this.Actions(state))
		{
			v = Max(v, MinValue(Results(state, move)));
		}
		
		return v;
	}
	
	public int MinValue(State state) throws Exception
	{
		if (this.TerminalTest(state))
			return this.Utility(state);
		
		int v = Integer.MAX_VALUE;
		
		for (Move move : this.Actions(state))
		{
			v = Min(v, MaxValue(Results(state, move)));
		}
		
		return v;
	}
	
	public Move[] Actions(State state)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		for	(int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				if (state.getState(row, col).equals(State.BLANK))
					moves.add(new Move(row, col));
				
		return moves.toArray(new Move[moves.size()]);		
	}
	
	public State Results(State state, Move move) throws Exception
	{
		State newState = (State)state.clone();
		newState.setState(move.row, move.col, state.getTurn());
		return newState;
	}
	
	public int Utility(State state) throws Exception
	{
		if (state.isWin(State.PLAYER_X))
			return 1;
		else if (state.isWin(State.PLAYER_O))
			return -1;
		else
			return 0;
	}
	
	public boolean TerminalTest(State state) throws Exception
	{
		return state.isGameOver();
	}
}
