package ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar implements ISolver, ISearch {
	
	@Override
	public ArrayList<Action> solve(int x0, int y0, int xG, int yG, int[][] map)
	{
		Problem problem = new Problem(new State(xG, yG, map), new State(x0, y0, map), new TransitionFunction(), new Manhattan(), new NodeComparator());
		Node solution = Search(problem);
		
		return solution == null ? null : problem.SolutionFromNode(solution);
	}

	@Override
	public Node Search(Problem problem) {
		HashSet<Node> explored = new HashSet<Node>();
		Queue<Node> frontier = new PriorityQueue<Node>(1, problem.getComparator());
		
		frontier.add(new Node(problem, problem.getInitialState(), null, Action.NONE));
		
		while (!frontier.isEmpty())
		{	
			Node parent = frontier.poll();
			State state = parent.getState();
			
			if (problem.GoalTest(state)) return parent;
			explored.add(parent);
			
			for (Action action : problem.availableActions(state))
			{
				State successor = problem.successor(state, action);
				Node child = new Node(problem, successor, parent, action);
				
				if (explored.contains(child))
					continue;
				
				if (!frontier.contains(child))
					frontier.add(child);
			}			
		}
		
		return null;
	}

	@Override
	public boolean isSolvable(int x0, int y0, int xG, int yG, int[][] map)
	{
		return solve(x0, y0, xG, yG, map) != null;
	}

	@Override
	public int maxTreeHeight(int x0, int y0, int xG, int yG, int[][] map)
	{
		Problem problem = new Problem(new State(xG, yG, map), new State(x0, y0, map), new TransitionFunction(), new Manhattan(), new NodeComparator());
		Node solution = Search(problem);
		return solution == null ? -1 : solution.G();
	}

	@Override
	public int minMoves(int x0, int y0, int xG, int yG, int[][] map)
	{
		Problem problem = new Problem(new State(xG, yG, map), new State(x0, y0, map), new TransitionFunction(), new Manhattan(), new NodeComparator());
		Node solution = Search(problem);
		
		return solution == null ? -1 : problem.SolutionFromNode(solution).size();
	}
}
