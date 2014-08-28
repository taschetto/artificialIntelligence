package ai;

public class Node {
	private Problem problem;
	private State state;
	private Node parent;
	private Action action;
	
	public Node(Problem problem, State state, Node parent, Action action)
	{
		this.problem = problem;
		this.state = state;
		this.parent = parent;
		this.action = action;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public int G()
	{
		return this.parent != null ? parent.G() + 1 : 1;
	}
	
	public int H()
	{
		return this.problem.HeuristicFunction(this.state);
	}
	
	public int F()
	{
		return this.G() + this.H();
	}

	public Node getParent()
	{
		return this.parent;
	}
	
	public Action getAction()
	{
		return this.action;
	}
	
	@Override
	public int hashCode()
	{
		return this.state.hashCode();
	};
	
	@Override
	public boolean equals(Object o)
	{
	    if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Node))return false;
	    Node oNode = (Node)o;
	    return this.getState().equals(oNode.getState());
	}
}