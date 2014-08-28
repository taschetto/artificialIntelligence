package ai;

public class Move {
	/**
	 * The row index of the move
	 */
	public int row;
	
	/**
	 * The column index of the move.
	 */
	public int col;
	
	/** 
	 * Default constructor
	 * 
	 */
	public Move() {
		this.row = -1;
		this.col = -1;
	}
	
	/**
	 * A constructor in which you can specify the row and column index.
	 * @param row The row index
	 * @param col The column index
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "("+row+","+col+")";
	}
}
