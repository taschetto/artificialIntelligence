package ai;

import java.util.ArrayList;

public class AlphaBetaPlayer extends MiniMaxPlayer {

	public AlphaBetaPlayer(int playerIndex) {
		super(playerIndex);
		
	}

	@Override
	public int getPlayerType() {
		return ALPHABETA_PLAYER;
	}
	
	@Override
	public Move getNextMove(State state) throws Exception {
		Move move = null;
		int max = Integer.MIN_VALUE;
		for (Move a : Actions(state)) {
			int utility = MinValue(Results(state, a), Integer.MIN_VALUE, Integer.MAX_VALUE);
			if (utility > max) {
				move = a;
				max = utility;
			}
		}
		return move;
	}

	public int MaxValue(State state, int alpha, int beta) throws Exception
	{
		if (TerminalTest(state)) return Utility(state);
		
		int v = Integer.MIN_VALUE;
		for (Move a : Actions(state))
		{
			v = Math.max(v, MinValue(Results(state, a), alpha, beta));
			if (v >= beta) return v;
			alpha = Math.max(alpha, v);
		}
		
		return v;
	}
	
	public int MinValue(State state, int alpha, int beta) throws Exception
	{
		if (TerminalTest(state)) return Utility(state);
		
		int v = Integer.MAX_VALUE;
		for (Move a : Actions(state))
		{
			v = Math.min(v, MaxValue(Results(state, a), alpha, beta));
			if (v <= alpha) return v;
			beta = Math.min(beta, v);
		}
		
		return v;
	}
	
	public Move[] Actions(State state) throws Exception
	{
		ArrayList<Move> actions = new ArrayList<Move>();
		for	(int row = 0; row < State.SIZE; row++)
			for (int col = 0; col < State.SIZE; col++)
				if (state.getPlayerIndexOfSquare(row, col) == State.PLAYER_NONE)
					actions.add(new Move(row, col));
				
		return actions.toArray(new Move[actions.size()]);		
	}
	
	public State Results(State state, Move move) throws Exception
	{
		State newState = (State)state.clone();
		int player = state.getTurn();
		int other = player == State.PLAYER_X ? State.PLAYER_O : State.PLAYER_X;
		newState.setState(move.row, move.col, player);
		newState.setTurn(other);
		return newState;
	}
	
	public int Utility(State state) throws Exception
	{
		int player = this.playerIndex == State.PLAYER_X ? State.PLAYER_X : State.PLAYER_O;
		int other = this.playerIndex == State.PLAYER_X ? State.PLAYER_O : State.PLAYER_X;
		
		if (state.isWin(player))
			return 1;
		else if (state.isWin(other))
			return -1;
		else
			return 0;
	}
	
	public boolean TerminalTest(State state) throws Exception
	{
		return state.isGameOver();
	}	

}
