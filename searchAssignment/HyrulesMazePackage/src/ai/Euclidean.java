package ai;

public class Euclidean implements IHeuristic {

	@Override
	public int HeuristicFunction(State stateA, State stateB) {
		return (int) Math.sqrt((stateA.x - stateB.x)^2 + (stateA.y - stateB.y)^2);
	}

}
