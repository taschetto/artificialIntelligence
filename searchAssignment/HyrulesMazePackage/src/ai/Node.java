package ai;

public class Node {
	private State state;
	private int g;
	private int h;
	
	public Node(State state, int g, int h)
	{
		this.state = state;
		this.g = g;
		this.h = h;
	}
	
	public State getState()
	{
		return this.state;
	}
	
	public int getG()
	{
		return this.g;
	}
	
	public int getH()
	{
		return this.h;
	}
}
