package gamestate;

import java.awt.*;

import tilemap.*;
import entity.*;
import ai.AStar;
import ai.Util;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	
	private Player player;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init() {
		tileMap = new TileMap(60);
		tileMap.loadTiles("/sprites/tiles.png");
		tileMap.loadMap(Util.map);
		tileMap.setPosition(0, 0);
		
		
		player = new Player(tileMap, new AStar());

		player.setPosition(((tileMap.player_y + 1) * 60) - 60/2, (tileMap.player_x * 60) + 60/2);
	}

	public void update() {
		
		// update player
		player.update();
		if(!player.isMoving())
			player.move();
	}
	
	public void draw(Graphics2D g) {
		// draw tilemap
		tileMap.draw(g);
		// draw player
		player.draw(g);
		
	}
	
	public void keyPressed(int k) {
		//if(k == KeyEvent.VK_LEFT) player.moveLeft();//player.setLeft(true);
		//if(k == KeyEvent.VK_RIGHT)player.moveRight(); //player.setRight(true);
		//if(k == KeyEvent.VK_UP) player.moveUp();//player.setUp(true);
		//if(k == KeyEvent.VK_DOWN) player.moveDown(); //player.setDown(true);
		
	}
	
	public void keyReleased(int k) {
		/*if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false); */
	}
	
}