package ai;

import java.util.ArrayList;



public class AStar implements ISolver {

	//Now you know that you need to use the transition function we provided you
	private TransitionFunction tf = new TransitionFunction();
	
	
	@Override
	public ArrayList<Action> solve(int x0, int y0, int xG, int yG, int[][] map){
		//TODO Your code here
		return null;
	}

	@Override
	public boolean isSolvable(int x, int y,int xG, int yG, int[][] map) {
		//TODO Your code here
		return false;
	}

	@Override
	public int maxTreeHeight(int x, int y, int gx, int gy, int[][] map) {
		//TODO Your code here
		return 0;
	}

	@Override
	public int minMoves(int x, int y, int gx, int gy, int[][] map) {
		//TODO Your code here
		return 0;
	}

}
