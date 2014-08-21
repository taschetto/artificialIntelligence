package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ai.AStar;
import ai.Action;

public class AStarTest {

	private static final String MAPS_TRIVIAL_MAP = "/maps/trivial.map";
	private static final String MAPS_EASY_MAP = "/maps/easy.map";
	private static final String MAPS_MEDIUM_MAP = "/maps/medium.map";
	
	AStar astar;
	int[][] map;
	int x, y, gx, gy;

	@Before
	public void setUp() throws Exception {
		astar = new AStar();
	}

	// trivial

	@Test
	public final void testAStarTrivialSolve() {
		loadMap(MAPS_TRIVIAL_MAP);
		ArrayList<Action> plan = astar.solve(x, y, gx, gy, map);

		assertEquals(plan.size(), 2);
		assertEquals(plan.get(0), Action.UP);
		assertEquals(plan.get(1), Action.UP);
	}

	@Test
	public final void testAStarTrivialIsSolvable() {
		loadMap(MAPS_TRIVIAL_MAP);
		boolean isSolvable = astar.isSolvable(x, y, gx, gy, map);

		assertEquals(isSolvable, true);
	}

	@Test
	public final void testAStarTrivialMaxTree() {
		loadMap(MAPS_TRIVIAL_MAP);
		int height = astar.maxTreeHeight(x, y, gx, gy, map);

		assertEquals(height, 3);
	}

	@Test
	public final void testAStarTrivialMinMoves() {
		loadMap(MAPS_TRIVIAL_MAP);
		int min = astar.minMoves(x, y, gx, gy, map);

		assertEquals(min, 2);
	}

	// easy

	@Test
	public final void testAStarEasy() {
		loadMap(MAPS_EASY_MAP);
		ArrayList<Action> plan = astar.solve(x, y, gx, gy, map);

		assertEquals(plan.size(), 10);
		assertEquals(plan.get(0), Action.UP);
		assertEquals(plan.get(1), Action.UP);
		assertEquals(plan.get(2), Action.UP);
		assertEquals(plan.get(3), Action.UP);
		assertEquals(plan.get(4), Action.UP);
		assertEquals(plan.get(5), Action.UP);
		assertEquals(plan.get(6), Action.UP);
		assertEquals(plan.get(7), Action.RIGHT);
		assertEquals(plan.get(8), Action.RIGHT);
		assertEquals(plan.get(9), Action.RIGHT);
	}

	@Test
	public final void testAStarEasyIsSolvable() {
		loadMap(MAPS_EASY_MAP);
		boolean isSolvable = astar.isSolvable(x, y, gx, gy, map);

		assertEquals(isSolvable, true);
	}

	@Test
	public final void testAStarEasyMaxTree() {
		loadMap(MAPS_EASY_MAP);
		int height = astar.maxTreeHeight(x, y, gx, gy, map);

		assertEquals(height, 11);
	}

	@Test
	public final void testAStarEasyMinMoves() {
		loadMap(MAPS_EASY_MAP);
		int min = astar.minMoves(x, y, gx, gy, map);

		assertEquals(min, 10);
	}

	// medium

	@Test
	public final void testAStarMedium() {
		loadMap(MAPS_MEDIUM_MAP);
		ArrayList<Action> plan = astar.solve(x, y, gx, gy, map);

		assertEquals(plan.size(), 28);
		assertEquals(plan.get(0), Action.DOWN);
		assertEquals(plan.get(1), Action.DOWN);
		assertEquals(plan.get(2), Action.DOWN);
		assertEquals(plan.get(3), Action.DOWN);
		assertEquals(plan.get(4), Action.DOWN);
		assertEquals(plan.get(5), Action.DOWN);
		assertEquals(plan.get(6), Action.DOWN);
		assertEquals(plan.get(7), Action.RIGHT);
		assertEquals(plan.get(8), Action.RIGHT);
		assertEquals(plan.get(9), Action.RIGHT);
		assertEquals(plan.get(10), Action.RIGHT);
		assertEquals(plan.get(11), Action.RIGHT);
		assertEquals(plan.get(12), Action.UP);
		assertEquals(plan.get(13), Action.UP);
		assertEquals(plan.get(14), Action.UP);
		assertEquals(plan.get(15), Action.RIGHT);
		assertEquals(plan.get(16), Action.RIGHT);
		assertEquals(plan.get(17), Action.RIGHT);
		assertEquals(plan.get(18), Action.UP);
		assertEquals(plan.get(19), Action.UP);
		assertEquals(plan.get(20), Action.LEFT);
		assertEquals(plan.get(21), Action.LEFT);
		assertEquals(plan.get(22), Action.LEFT);
		assertEquals(plan.get(23), Action.UP);
		assertEquals(plan.get(24), Action.UP);
		assertEquals(plan.get(25), Action.LEFT);
		assertEquals(plan.get(26), Action.LEFT);
		assertEquals(plan.get(27), Action.LEFT);
	}

	@Test
	public final void testAStarMediumIsSolvable() {
		loadMap(MAPS_MEDIUM_MAP);
		boolean isSolvable = astar.isSolvable(x, y, gx, gy, map);

		assertEquals(isSolvable, true);
	}

	@Test
	public final void testAStarMediumMaxTree() {
		loadMap(MAPS_MEDIUM_MAP);
		int height = astar.maxTreeHeight(x, y, gx, gy, map);

		assertEquals(height, 29);
	}

	@Test
	public final void testAStarMediumMinMoves() {
		loadMap(MAPS_MEDIUM_MAP);
		int min = astar.minMoves(x, y, gx, gy, map);

		assertEquals(min, 28);
	}

	private void loadMap(String s) {

		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			x = Integer.parseInt(br.readLine());
			y = Integer.parseInt(br.readLine());

			int numCols = Integer.parseInt(br.readLine());
			int numRows = Integer.parseInt(br.readLine());

			map = new int[numRows][numCols];

			String delims = " ";

			

			for (int row = 0; row < numRows; row++) {
				
				String line = br.readLine();
				String[] tokens = line.split(delims);
				
				for (int col = 0; col < numCols; col++) {
					
					
					map[row][col] = Integer.parseInt(tokens[col]);
					
					
					if (map[row][col] == 2) {
						gx = row;
						gy = col;
					}
					// System.out.println(row + " " + col + " - " +
					// tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
