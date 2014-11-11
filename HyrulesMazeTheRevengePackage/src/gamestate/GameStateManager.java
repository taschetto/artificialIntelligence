package gamestate;

import java.awt.Graphics2D;

public class GameStateManager {
	
	public GameState gs;
	
	private GameState gameState;
	
	public GameStateManager(){
		
		gameState = new Level1State(this);
	}
	
	public void init(){
		gameState.init();
	}
	
	public void update(){
		gameState.update();
	}
	
	public void draw(Graphics2D g){
		gameState.draw(g);
	}
	
	public void keyPressed(int k) {
		gameState.keyPressed(k);
		
	}
	
	public void keyReleased(int k) {
		gameState.keyReleased(k);
	}
	
}
