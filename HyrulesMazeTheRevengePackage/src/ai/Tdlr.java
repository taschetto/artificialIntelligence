package ai;

import java.util.ArrayList;
import java.util.HashMap;

import tilemap.TileMap;

public class Tdlr {
	
	private static Tdlr Instance;
	
	private static int policySteps = 25;
	private static int explorationThreshold = 200;
	private static float maximumReward = 55;
	private static float gamma = .9f;
	
	HashMap<Action, Action[]> sideEffects = new HashMap<>();
	
	TileMap tileMap = null;
	
	private HashMap<State, Integer> N = new HashMap<>();
	private HashMap<State, Float> U = new HashMap<>();
	private int steps = 0;
	
	public static Tdlr getInstance()
	{
		if (Instance == null)
			Instance = new Tdlr();
		
		return Instance;
	}
	
	private Tdlr()
	{
		sideEffects.put(Action.UP, new Action[]{Action.LEFT, Action.RIGHT});
		sideEffects.put(Action.DOWN, new Action[]{Action.RIGHT, Action.LEFT});
		sideEffects.put(Action.LEFT, new Action[]{Action.DOWN, Action.UP});
		sideEffects.put(Action.RIGHT, new Action[]{Action.UP, Action.DOWN});
	}
	
	private float alpha(int n)
	{
		return 1.f / (n + 1); // deixa divByZero estourar
	}
	
	private float f(float u, int n)
	{
		if (n < explorationThreshold)
			return maximumReward;
		
		return u;
	}

	private int conta = 0;
	public void updateUtilities(State current, State previous)
	{
		if (previous != null && previous.equals(current))
			return;
		
		if (!U.containsKey(current))
		{
			U.put(current, current.reward);
			N.put(current, 0);
		}
		else if (previous != null)
		{			
			int n = N.get(previous) + 1;
			N.put(previous, n);
			
			float updatedUtility = U.get(previous) + alpha(n) * (previous.reward + gamma * U.get(current) - f(U.get(previous), n));			
			U.put(previous, updatedUtility);
		}		
	}
	
	public IPolicy updatePolicy(TileMap tileMap, IPolicy currentPolicy)
	{
		steps++;
		if (steps % policySteps == 0)
		{
			steps = 0;
			return recalculatePolicy(tileMap);
		}
		return currentPolicy;
	}
	
	private DynamicPolicy recalculatePolicy(TileMap tileMap)
	{
		this.tileMap = tileMap;
		DynamicPolicy dp = new DynamicPolicy();
		
		for (int y = 0; y < this.tileMap.numRows; y++)
			for (int x = 0; x < this.tileMap.numCols; x++)
			{
				State s = new State(x, y, 0);
				dp.setAction(s, Pi(s));
			}
		
		/* Debug information */
		System.out.printf("\n");
		for (int y = 0; y < this.tileMap.numRows; y++)
		{
			for (int x = 0; x < this.tileMap.numCols; x++)
			{
				State s = new State(x, y, 0);
				System.out.printf("%+02.2f ", U.containsKey(s) ? U.get(s) : 0.f);
				//System.out.printf("%+02.2f [%s] ", U.containsKey(s) ? U.get(s) : 0.f, dp.nextMove(s).toString().charAt(0));
			}
			System.out.printf("\n");
		}
		
		System.out.printf("\n");
		for (int y = 0; y < this.tileMap.numRows; y++)
		{
			for (int x = 0; x < this.tileMap.numCols; x++)
			{
				State s = new State(x, y, 0);
				System.out.printf("%s ", dp.nextMove(s).toString().charAt(0));
			}
			System.out.printf("\n");
		}		
				
		return dp;
	}
	
	private Action Pi(State s)
	{
		Action[] actions = new Action[]{Action.UP, Action.DOWN, Action.LEFT, Action.RIGHT};
		
		float max = Float.NEGATIVE_INFINITY;
		Action arg = actions.length > 0 ? actions[0] : Action.UP;
		
		for (Action a : actions)
			if (canExecute(s, a))
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
		State suc = null;
		float value = 0.f;
		
		if (canExecute(s, a))
		{
			suc = successor(s, a);
			value = .7f * suc.reward;
		}
		
		for (Action e : sideEffects.get(a))
		{
			if (canExecute(s, e))
			{
				suc = successor(s, e);
				value += .15f * suc.reward;
			}
		}
		
		return value;
	}
	
	private boolean canExecute(State s, Action a)
	{
		if(a == Action.UP && s.y - 1 >= 0 && tileMap.map[s.y - 1][s.x] != 1)
			return true;
		else if(a == Action.DOWN && s.y + 1 < tileMap.numRows && tileMap.map[s.y + 1][s.x] != 1)
			return true;
		else if(a == Action.LEFT && s.x - 1 >= 0 && tileMap.map[s.y][s.x - 1] != 1)
			return true;
		else if(a == Action.RIGHT && s.x + 1 < tileMap.numCols && tileMap.map[s.y][s.x + 1] != 1)
			return true;
		
		return false;
	}
	
	private State successor(State s, Action a)
	{
		// este método já assume que a ação 'a' é possível em no estado 's'
		State suc = new State(s.x, s.y, 0);
		
        if (a == Action.UP) suc.y--;
        else if(a == Action.DOWN) suc.y++;
        else if(a == Action.LEFT) suc.x--;
        else if(a == Action.RIGHT) suc.x++;		
		suc.reward = U.containsKey(suc) ? U.get(suc) : 0;
		return suc;
	}
}
