package ai;

import java.util.HashMap;

public class DynamicPolicy implements IPolicy {
	private HashMap<State, Action> policy = new HashMap<State,Action>();
	
	public void setAction(State s, Action a) {
		policy.put(s, a);
	}

	@Override
	public Action nextMove(State s) {
		return policy.get(s);
	}

}
