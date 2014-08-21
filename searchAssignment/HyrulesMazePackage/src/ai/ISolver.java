package ai;

import java.util.ArrayList;

public interface ISolver {
	public ArrayList<Action> solve(int x0, int y0, int xG, int yG, int[][] map);
	public boolean isSolvable(int x, int y, int gx, int gy, int [][]map);
	public int maxTreeHeight(int x, int y, int gx, int gy, int [][]map);
	public int minMoves(int x, int y, int gx, int gy, int [][]map);

}
