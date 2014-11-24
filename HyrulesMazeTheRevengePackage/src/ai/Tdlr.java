package ai;

import java.util.ArrayList;
import java.util.HashMap;

import tilemap.TileMap;

public class Tdlr {
	
	private static Tdlr Instance;
	
	private static int policySteps = 20;
	private static int explorationThreshold = 0;
	private static float maximumReward = 50;
	
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

	public void updateUtilities(State current, State previous)
	{
		if (!U.containsKey(current))
		{
			U.put(current, current.reward);
			N.put(current, 0);
		}
		else if (previous != null)
		{
			float gamma = 1.f;
			
			int n = N.get(previous) + 1;
			N.put(previous, n);
			
			float uPrevious = U.get(previous);
			float uCurrent = U.get(current);
			float a = alpha(n);
			
			float updatedUtility = uPrevious + a * (previous.reward + gamma * uCurrent - uPrevious);
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
		
		for (int row = 0; row < this.tileMap.numRows; row++)
			for (int col = 0; col < this.tileMap.numCols; col++)
			{
				State s = new State(row, col, 0);
				dp.setAction(s, Pi(s));
				System.out.printf("Atualizei Pi(%d, %d) = %s\n", row, col,Pi(s).toString().charAt(0));
			}
		
		System.out.printf("\n");		
		
		/* Debug information */
		for (int row = 0; row < this.tileMap.numRows; row++)
		{
			for (int col = 0; col < this.tileMap.numCols; col++)
			{
				State s = new State(row, col, 0);
				System.out.printf("%0+3.1f [%s] ", U.containsKey(s) ? U.get(s) : 0.f, dp.nextMove(s).toString().charAt(0));
				
			}
			System.out.printf("\n");
		}
		/*
		for (int row = 0; row < this.tileMap.numRows; row++)
		{
			for (int col = 0; col < this.tileMap.numCols; col++)
			{
				State s = new State(row, col, 0);
				System.out.printf("%3d ", U.containsKey(s) ? N.get(s) : 0);
				
			}
			System.out.printf("\n");
		}*/
				
		return dp;
	}
	
	private Action Pi(State s)
	{
		float max = Float.NEGATIVE_INFINITY;
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
	    Action[] actionList = new Action[]{ Action.UP, Action.DOWN, Action.LEFT, Action.RIGHT };
	    
	    if (tileMap.map[s.y][s.x] != 1) // calcula somente se não é um arbusto
	    {
	      for (Action a : actionList)
	      {
	        if(a == Action.UP && s.y - 1 >= 0 && tileMap.map[s.y - 1][s.x] != 1)
	          actions.add(Action.UP);
	        else if(a == Action.DOWN && s.y + 1 < tileMap.numRows && tileMap.map[s.y + 1][s.x] != 1)
	          actions.add(Action.DOWN);
	        else if(a == Action.LEFT && s.x - 1 >= 0 && tileMap.map[s.y][s.x - 1] != 1)
	          actions.add(Action.LEFT);
	        else if(a == Action.RIGHT && s.x + 1 < tileMap.numCols && tileMap.map[s.y][s.x + 1] != 1)
	          actions.add(Action.RIGHT);  
	      }
	    }
	    
	    return actions.toArray(new Action[actions.size()]);
	}
	
	private State successor(State s, Action a)
	{
		State suc = new State(s.y, s.x, 0);
		
		if (a == Action.UP) suc.y--;
		else if (a == Action.DOWN) suc.y++;
		else if (a == Action.LEFT) suc.x--;
		else if (a == Action.RIGHT) suc.x++;
		
		suc.reward = U.containsKey(suc) ? U.get(suc) : 0;
		return suc;
	}
}
