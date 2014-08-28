package ai;

import java.util.ArrayList;

public class Problem {
	private State goalState;
	private State initialState;
	private TransitionFunction transitionFunction;
	private IHeuristic heuristic;
	
	public Problem(State goalState, State initialState, TransitionFunction transitionFunction, IHeuristic heuristic)
	{
		this.goalState = goalState;
		this.initialState = initialState;
		this.transitionFunction = transitionFunction;
		this.heuristic = heuristic;
	}
	
	public State getInitialState()
	{
		return this.initialState;
	}
	
	public TransitionFunction getTransitionFunction()
	{
		return transitionFunction;
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
