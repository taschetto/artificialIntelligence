package gamestate;

import java.util.ArrayList;
import java.awt.Graphics2D;

public class GameStateManager {
	
	public GameState gs;
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int LEVEL_STATE = 0;
	
	public GameStateManager(){
		gameStates = new ArrayList<GameState>();
		
		currentState = LEVEL_STATE;
		gs = new Level1State(this);
		gameStates.add(gs);
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
		
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}
