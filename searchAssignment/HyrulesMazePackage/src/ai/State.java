package ai;

public class State {
	public int x;
	public int y;
	public int[][] map;
	
	public State(int x, int y, int[][] map){
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	public int hashCode() {
		// TODO Your code here
		return super.hashCode(); //The hashcode here is not efficient if you use a HashSet for your closed list
	}
	
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		} else {
			State other = (State) obj;
			return other.x == x && other.y == y;
		}
	}
}
