package ai;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
	/**
	 * Command line strings that specify the types of the players
	 */
	private final static String HUMAN_PLAYER_STRING = "human";
	private final static String RANDOM_PLAYER_STRING = "random";
	private final static String MINIMAX_PLAYER_STRING = "minimax";
	
	/**
	 * Reference to the class containing the game's state information.
	 */
	private State boardState;
	
	/**
	 * These two references are used to interact with the appropriate player.
	 */
	private Player playerX;
	private Player playerO;

	/**
	 * The strings to be used with the turnLabel, specifying whose turn it is.
	 */
	private String turnString[];
	
	/**
	 * Whether or not the output should be silent (for testing purposes).
	 */
	private boolean silent = false;
	
	public void setSilent(boolean silent) {
		this.silent = silent;
	}

	public TicTacToe(int playerType1, int playerType2) {
		boardState = new State();
		turnString = new String[2];
		turnString[0] = "X's Turn";
		turnString[1] = "O's Turn";
		newGame(playerType1, playerType2);
	}
	
	/**
	 * Used to create a new game 
	 * 
	 * @param type1
	 *            Player1's type.
	 * @param type2
	 *            Player2's type.
	 */
	private void newGame(int type1, int type2) {
		switch (type1) {
		case Player.RANDOM_PLAYER:
			playerX = new RandomPlayer();
			break;
		case Player.MINIMAX_PLAYER:
			playerX = new MiniMaxPlayer(State.PLAYER_X);
			break;
		case Player.ALPHABETA_PLAYER:
			playerX = new AlphaBetaPlayer(State.PLAYER_X);
			break;
		default:
			playerX = new HumanPlayer(this);
			break;
		}

		switch (type2) {
		case Player.RANDOM_PLAYER:
			playerO = new RandomPlayer();
			break;
		case Player.MINIMAX_PLAYER:
			playerO = new MiniMaxPlayer(State.PLAYER_O);
			break;
		case Player.ALPHABETA_PLAYER:
			playerO = new AlphaBetaPlayer(State.PLAYER_O);
			break;
		default:
			playerO = new HumanPlayer(this);
			break;
		}
		
		boardState.setTurn(State.PLAYER_X);
	}
	
	/**
	 * Returns true if the position is a legal one.  A legal position is one in which
	 * the row takes a value of 0, 1, or 2 and the column takes the value of 0, 1, or 2.
	 * @param p The position you want to check the legality of
	 * @return True if the position is legal, false otherwise
	 */
	private boolean isLegalPosition(Move p) {
		if ((p != null) && (p.row >= 0) && (p.row < State.SIZE)
				&& (p.col >= 0) && (p.col < State.SIZE)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Plays a game, once configured, returning the winner, or -1 if the game was a draw.
	 * @return
	 * @throws Exception
	 */
	public int playGame() throws Exception {
		Move newState;
		while(!boardState.isGameOver()) {
			if (boardState.getTurn() == State.PLAYER_X) {
				newState = playerX.getNextMove(boardState);
				if (isLegalPosition(newState)) {
					boardState.setState(newState.row, newState.col, State.X);
					
				} else {
					throw new Exception("Illegal board position returned");
				}
				boardState.setTurn(State.PLAYER_O);
			} else {
				newState = playerO.getNextMove(boardState);
				if (isLegalPosition(newState)) {
					boardState.setState(newState.row, newState.col, State.O);
				} else {
					throw new Exception("Illegal board position returned");
				}
				boardState.setTurn(State.PLAYER_X);
			}
			
			displayBoard(boardState);
			println(turnString[boardState.getTurn()]);
		}
		if (boardState.isWin(State.PLAYER_X)) {
			println("Player X won");
			return State.PLAYER_X;
		} else if (boardState.isWin(State.PLAYER_O)) {
			println("Player O won");
			return State.PLAYER_O;
		} else {
			// Draw
			println("Draw");
			return -1;
		}
	}
	
	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	
	public Move readMoveFromPlayer(Player player) throws Exception {
		boolean validMove = false;
		Move m = new Move(-1,-1);
		while(!validMove) {
			println("State your move:");
			println("7 | 8 | 9");
			println("--|---|---");
			println("4 | 5 | 6");
			println("--|---|---");
			println("1 | 2 | 3");
			print("Move > ");
			int move = 0;
			try {
				move = Integer.parseInt(console.readLine());
			} catch(NumberFormatException e) {
				
			}
			switch (move) {
			case 1:
				m = new Move(2,0);
				break;
			case 2:
				m = new Move(2,1);
				break;
			case 3:
				m = new Move(2,2);
				break;
			case 4:
				m = new Move(1,0);
				break;
			case 5:
				m = new Move(1,1);
				break;
			case 6:
				m = new Move(1,2);
				break;
			case 7:
				m = new Move(0,0);
				break;
			case 8:
				m = new Move(0,1);
				break;
			case 9:
				m = new Move(0,1);
				break;
			default:
				println("Invalid move");
				break;
			}
			//println("Move "+move+" is "+m);
			validMove = isLegalPosition(m);
		}
		return m;
	}
	
	private final void println(String message) {
		if(!silent) {
			System.out.println(message);
		}
	}
	
	private final void print(String message) {
		if(!silent) {
			System.out.print(message);
		}
	}
	
	public void displayBoard(State boardState) {
		if(!silent) {
			boardState.dump(System.out);
		}
	}
	
	/**
	 * The entry point for this program.
	 * 
	 * @param args
	 *            The command line arguments (Not used!)
	 */
	public static void main(String[] args) {
		try {
			int player1Type = Player.HUMAN_PLAYER;
			int player2Type = Player.HUMAN_PLAYER;
			if( args.length != 2  ) {
				System.out.println("Usage: program <player 1 type> <player 2 type>");
				System.out.println("       where player type = human, random, or minimax");
				System.exit(-1);
			}
			if( args[0].equals(HUMAN_PLAYER_STRING) ) {
				player1Type = Player.HUMAN_PLAYER;
			} else if( args[0].equals(RANDOM_PLAYER_STRING)) {
				player1Type = Player.RANDOM_PLAYER;
			} else if( args[0].equals(MINIMAX_PLAYER_STRING)) {
				player1Type = Player.MINIMAX_PLAYER;
			} else {
				throw new Exception("Unrecognized player type");
			}
			
			if( args[1].equals(HUMAN_PLAYER_STRING) ) {
				player2Type = Player.HUMAN_PLAYER;
			} else if( args[1].equals(RANDOM_PLAYER_STRING)) {
				player2Type = Player.RANDOM_PLAYER;
			} else if( args[1].equals(MINIMAX_PLAYER_STRING)) {
				player2Type = Player.MINIMAX_PLAYER;
			} else {
				throw new Exception("Unrecognized player type");
			}

			TicTacToe game = new TicTacToe(player1Type, player2Type);
			game.setSilent(false);
			game.playGame();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
