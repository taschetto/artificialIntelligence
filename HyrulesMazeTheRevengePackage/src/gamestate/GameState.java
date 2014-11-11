package gamestate;

import java.awt.Graphics2D;


public abstract class GameState {

	protected GameStateManager gsm;
	
	//private Player player;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
		
}
