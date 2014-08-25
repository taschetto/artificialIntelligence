package ai;

public class Node {
	private Node parent;
	private Action action;
	private State state;
	private int g;
	private int h;
	
	public Node(Node parent, Action action, State state, int g, int h)
	{
		this.parent = parent;
		this.action = action;
		this.state = state;
		this.g = g;
		this.h = h;
	}
	
	public Node getParent()
	{
		return this.parent;
	}
	
	public Action getAction()
	{
		return this.action;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public int G()
	{
		return this.g;
	}
	
	public int H()
	{
		return this.h;
	}
	
	public int F()
	{
		return this.G() + this.H();
	}
	
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