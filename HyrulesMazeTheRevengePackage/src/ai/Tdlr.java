package ai;

import java.util.HashMap;

public class Tdlr {
	
	private HashMap<State, Integer> N = new HashMap<>();
	private HashMap<State, Float> U = new HashMap<>();
	
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
	
	public DynamicPolicy recalculatePolicy()
	{
		DynamicPolicy dp = new DynamicPolicy();
		
		// TODO
		
		return dp;
	}
	
	private Action V(State s)
	{
		float max = Float.MIN_VALUE;
		Action arg = Action.NONE;
		
		Action[] actions = new Action[]{ Action.UP, Action.DOWN, Action.LEFT, Action.RIGHT};
		
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
		// TODO
		return 0.f;
	}
}
