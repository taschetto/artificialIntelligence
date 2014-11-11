package ai;

public class StaticPolicy implements IPolicy {
	
	@Override
	public Action nextMove(State s){
		if(s.reward == 50){
			return Action.NONE;
		}

		return Action.UP;
	}


}
