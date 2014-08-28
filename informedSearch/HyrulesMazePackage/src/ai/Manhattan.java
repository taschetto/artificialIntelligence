package ai;

public class Manhattan implements IHeuristic {

	@Override
	public int HeuristicFunction(State stateA, State stateB) {
		return Math.abs(stateA.x - stateB.x) + Math.abs(stateA.y - stateB.y);
	}
}
