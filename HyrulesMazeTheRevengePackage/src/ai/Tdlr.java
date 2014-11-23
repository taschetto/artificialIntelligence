package ai;

import java.util.ArrayList;
import java.util.HashMap;

import tilemap.TileMap;

public class Tdlr {
	TileMap tileMap = null;
	
	private HashMap<State, Integer> N = new HashMap<>();
	private HashMap<State, Float> U = new HashMap<>();
	
	HashMap<Action, Action[]> sideEffects = new HashMap<>();
	
	public Tdlr()
	{
		sideEffects.put(Action.UP, new Action[]{Action.LEFT, Action.RIGHT});
		sideEffects.put(Action.DOWN, new Action[]{Action.RIGHT, Action.LEFT});
		sideEffects.put(Action.LEFT, new Action[]{Action.DOWN, Action.UP});
		sideEffects.put(Action.RIGHT, new Action[]{Action.UP, Action.DOWN});
	}
	
	private float alpha(int n)
	{
		return 1 / (n + 1); // deixa divByZero estourar
	}	

	public void updateUtilities(State current, State previous)
	{
		previous = current;
		
		if (!U.containsKey(current))
		{
			U.put(current, current.reward);
		}
		else if (previous != null)
		{
			float gamma = 1;
			
			int n = N.containsKey(previous) ? N.get(previous) + 1 : 1;
			N.put(previous, n);
			
			float uPrevious = U.get(previous);
			float uCurrent = U.get(current);
			
			float updatedUtility = uPrevious + alpha(n) * (previous.reward + gamma * uCurrent - uPrevious);
			U.put(previous, updatedUtility);
		}		
	}
	
	public DynamicPolicy recalculatePolicy(TileMap tileMap)
	{
		this.tileMap = tileMap;
		DynamicPolicy dp = new DynamicPolicy();
		
		// TODO
		
		return dp;
	}
	
	private Action Pi(State s)
	{
		float max = Float.MIN_VALUE;
		Action arg = Action.NONE;
		
		Action[] actions = availableActions(s);
		
		for (Action a : actions)
		{
			float expectedValue = E(s, a);
			if (expectedValue > max)
			{
				max = expectedValue;
				arg = a;
			}
		}
		
		return arg;
	}
	
	private float E(State s, Action a)
	{
		float value = 0.7f * successor(s, a).reward;
		
		for (Action se : sideEffects.get(a))
			value += 0.15f * successor(s, se).reward;
		
		return value;
	}
	
	private Action[] availableActions(State s)
	{
		ArrayList<Action> actions = new ArrayList<>();
		
		if (s.y > 0) actions.add(Action.UP);
		if (s.y < tileMap.numRows - 1) actions.add(Action.DOWN);
		if (s.x > 0) actions.add(Action.LEFT);
		if (s.x < tileMap.numCols - 1) actions.add(Action.RIGHT);
		
		return (Action[]) actions.toArray();
	}
	
	private State successor(State s, Action a)
	{
		State suc = new State(s.y, s.x, 0);
		
		if (a == Action.UP) suc.y--;
		else if (a == Action.DOWN) suc.y++;
		else if (a == Action.LEFT) suc.x--;
		else if (a == Action.RIGHT) suc.x++;
		
		suc.reward = U.containsKey(suc) ? U.get(suc) : -1;
		return suc;
	}
}
