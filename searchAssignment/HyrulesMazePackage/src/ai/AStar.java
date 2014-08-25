package ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar implements ISolver {
	
	private State goalState;

	//Now you know that you need to use the transition function we provided you
	private TransitionFunction tf = new TransitionFunction();
	
	private boolean GoalTest(State state)
	{
		return state.x == this.goalState.x && state.y == this.goalState.y;
	}
	
	private int DistanceToGoal(State state)
	{
		return Math.abs(this.goalState.x - state.x) + Math.abs(this.goalState.y - state.y);
	}
	
	private ArrayList<Action> Solution(Node node)
	{
		ArrayList<Action> actions = new ArrayList<Action>();
		
		do
		{
			actions.add(node.getAction());
			node = node.getParent();
		} while (node != null);
		
		return actions;
	}
	
	@Override
	public ArrayList<Action> solve(int x0, int y0, int xG, int yG, int[][] map){
		//TODO Your code here
		this.goalState = new State(xG, yG, map);
		State initialState = new State(x0, y0, map);
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, new NodeComparator());
		frontier.add(new Node(null, null, initialState, 0, DistanceToGoal(initialState)));
		HashSet<State> explored = new HashSet<State>();
		
		while (true)
		{
			if (frontier.isEmpty())
				break;
			
			Node node = frontier.poll();
			State state = node.getState();
			
			if (this.GoalTest(state)) return Solution(node);
			explored.add(state);
			
			for (Action action : tf.availableActions(state))
			{
				State successor = tf.successor(state, action);
				Node child = new Node(node, action, successor, node.G() + 1, DistanceToGoal(successor));
				if (!explored.contains(successor) && !frontier.contains(child))
				{
					frontier.add(child);
				}
				else
				{
					frontier.remove(child);
					frontier.add(child);
				}
			}			
		}
		
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
