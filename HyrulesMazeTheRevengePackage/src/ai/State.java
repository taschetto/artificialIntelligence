package ai;

public class State {
	public int x;
	public int y;
	public float reward;
	
	public State(int x, int y, float reward){
		this.x = x;
		this.y = y;
		this.reward = reward;
	}
	
	public int hashCode() {
		return (int)(Math.pow((x*7), 2) + y);
	}
	
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		} else {
			State other = (State) obj;
			return other.x == x && other.y == y && other.reward == reward;
		}
	}
}
