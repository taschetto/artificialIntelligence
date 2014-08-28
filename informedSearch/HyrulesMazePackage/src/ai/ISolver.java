package ai;

import java.util.ArrayList;

public interface ISolver {
	/**
	 * Uses a search algorithm to generate a solution to the N-Puzzle starting from state <code>s0</code> to 
	 * reach goal state <code>g</code>. If there is a solution, return an array of {@link String}s listing the moves, 
	 * if the solution is trivial (no moves), return an array with 1 <code>null</code> {@link String}, otherwise 
	 * (no solution), return <code>null</code>.
	 * @param x0 The initial x state of the search problem
	 * @param y0 The initial y state of the search problem
	 * @param xG The goal x state of the search problem
	 * @param yG The goal y state of the search problem
	 * @param map A reference to the state map.
	 * @return a list of actions [a<sub>0</sub>, ..., a<sub>n</sub>] where each a<sub>i</sub> &in; {"up","down","left","right"}.
	 */
	public ArrayList<Action> solve(int x0, int y0, int xG, int yG, int[][] map);
	
	/**
	 * Returns whether or not the specified problem is solvable.
	 * @param x0
	 * @param y0
	 * @param xG
	 * @param yG
	 * @param map
	 * @return
	 */
	public boolean isSolvable(int x0, int y0, int xG, int yG, int [][]map);
	
	/**
	 * Returns the maximum height of the search tree (i.e. the longest move sequence considered during search).
	 * @param x0
	 * @param y0
	 * @param xG
	 * @param yG
	 * @param map
	 * @return
	 */
	public int maxTreeHeight(int x0, int y0, int xG, int yG, int [][]map);
	
	/**
	 * Returns the minimum number of moves to solve the specified problem, or -1 if the problem is unsolvable.
	 * @param x0
	 * @param y0
	 * @param xG
	 * @param yG
	 * @param map
	 * @return
	 */
	public int minMoves(int x0, int y0, int xG, int yG, int [][]map);

}