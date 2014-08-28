package ai;

import java.util.ArrayList;

public class Problem {
	private State goalState;
	private State initialState;
	private TransitionFunction transitionFunction;
	private IHeuristic heuristic;
	private NodeComparator nodeComparator;
	
	public Problem(State goalState, State initialState, TransitionFunction transitionFunction, IHeuristic heuristic, NodeComparator nodeComparator)
	{
		this.goalState = goalState;
		this.initialState = initialState;
		this.transitionFunction = transitionFunction;
		this.heuristic = heuristic;
		this.nodeComparator = nodeComparator;
	}
	
	public State getInitialState()
	{
		return this.initialState;
	}
	
	public NodeComparator getComparator()
	{
		return this.nodeComparator;
	}
	
	public Action[] availableActions(State state)
	{
		return this.transitionFunction.availableActions(state);
	}
	
	public State successor(State state, Action action)
	{
		return this.transitionFunction.successor(state, action);
	}
	
	public int HeuristicFunction(State state)
	{
		return this.heuristic.HeuristicFunction(this.goalState,  state);
	}
	
	public boolean GoalTest(State testState)
	{
		return this.goalState.equals(testState);
	}	
	
	public ArrayList<Action> SolutionFromNode(Node node)
	{
		ArrayList<Action> actions = new ArrayList<Action>();
		
		do
		{
			Action action = node.getAction();
			if (action != Action.NONE)
				actions.add(0, node.getAction());
			node = node.getParent();
		} while (node != null);
		
		return actions;
	}
	
}
