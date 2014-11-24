package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import tilemap.TileMap;
import ai.Action;
import ai.IPolicy;
import ai.State;
import ai.Tdlr;
import ai.TransitionFunction;
import ai.Util;

public class Player extends MapObject {
	//AI Stuff
	private IPolicy policy;
	TransitionFunction tf;
	float totalReward = 0f;
	State previous = null;
	
	Tdlr tdlr;
	
	// player stuff
	private boolean flinching;
	private final int[] numFrames = {3, 3, 3, 3};
	private long flinchTimer;
	double walk = 60;
	double new_x, new_y;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	
	// animation actions
	private static final int IDLE = 3;
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	
	public Player(TileMap tm, IPolicy chooser) {
		super(tm);
		//TODO Change the line below to include a DynamicPolicy (which you will populate with what you learned)
		this.policy = chooser;
		this.tdlr = Tdlr.getInstance();
		
		width = 60;
		height = 60;
		cwidth = 60;
		cheight = 60;
		
		moveSpeed = Util.velocity;
		stopSpeed = Util.velocity;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/sprites/link.png"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 4; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
				}
				
				sprites.add(bi);
			}
			
		}
		catch(Exception e) {e.printStackTrace();}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
		new_x = x;
		new_y = y;
		
		tf = new TransitionFunction(tm.map);
		
	}

	private void getNextPosition() {

		// movement
		if(left && x > new_x) {
			
			dx -= moveSpeed;
			if(dx < -moveSpeed) {
				dx = -moveSpeed;
			}
		}
		else if(right && x < new_x) {
			dx += moveSpeed;
			if(dx > moveSpeed) {
				dx = moveSpeed;
			}
		}
		else if(up && y > new_y ){
			dy -= moveSpeed;
			if(dy < -moveSpeed) {
				dy = -moveSpeed;
			}
		}
		else if(down && y < new_y ){
			dy += moveSpeed;
			if(dy > -moveSpeed) {
				dy = moveSpeed;
			}
		}
		
		
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
			else if(dy > 0){
				dy -= stopSpeed;
				if(dy < 0) {
					dy = 0;
				}
			}
			else if(dy < 0){
				dy += stopSpeed;
				if(dy > 0) {
					dy = 0;
				}
			}
			
		}
		
		//verifica se ja acabou de andar
		if(up && y <= new_y ){up = false;}
		if(down && y >= new_y){down = false;}
		if(left && x <= new_x){left = false;}
		if(right && x >= new_x){right = false;}
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// set animation

		if(up) {
			if(currentAction != UP) {
				currentAction = UP;
				animation.setFrames(sprites.get(UP));
				animation.setDelay(100);
				width = 60;
			}
		}
		else if(left){
			if(currentAction != LEFT){
				currentAction = LEFT;
				animation.setFrames(sprites.get(LEFT));
				animation.setDelay(100);
				width = 60;
			}
		}
		else if(right){
			if(currentAction != RIGHT){
				currentAction = RIGHT;
				animation.setFrames(sprites.get(RIGHT));
				animation.setDelay(100);
				width = 60;
			}
		}
		else if(down){
			if(currentAction != DOWN){
				currentAction = DOWN;
				animation.setFrames(sprites.get(DOWN));
				animation.setDelay(100);
				width = 60;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 60;
			}
		}
		
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		// draw player
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
	}
	
	public boolean isMoving(){
		return up || down || left || right;
	}
	
	public void move(){
			int currRow = (int)y / tileSize;
			int currCol = (int)x / tileSize;	
			
			Action action = computeNextMove(currRow, currCol, tileMap.map);
			
			//System.out.println(action);
			
			if(action == Action.UP){
				//se nao vai sair do mapa nem bater
				if( currRow - 1 >= 0 && tileMap.map[currRow - 1][currCol] != 1){
					up = true;
					new_y = y - walk;
				}
			}
			else if(action == Action.DOWN){
				if(currRow + 1 < tileMap.numRows  && tileMap.map[currRow + 1][currCol] != 1){
					down = true;
					new_y = y + walk;
				}
			}
			else if(action == Action.LEFT){
				if( currCol - 1 >= 0 && tileMap.map[currRow][currCol - 1] != 1){
					left = true;
					new_x = x - walk;
				}
			}
			else if(action == Action.RIGHT){
				if(currCol + 1 <tileMap.numCols && tileMap.map[currRow][currCol + 1] != 1){
					right = true;
					new_x = x + walk;
				}
			}

	}

	public Action computeNextMove(int old_x, int old_y, int[][] map) {
		float reward = 0f;
		
		//if there's a wall
		if(map[old_x][old_y] == 1){
			reward = -0.1f;
		}
				
		//if we can move
		else if(map[old_x][old_y] == 0){
			reward = -0.1f;
		}
				
		//if over rupee
		else if(map[old_x][old_y] == 3){
			reward = 5f;
		}
				
		//If we find the chest, reset
		else{
			reward = 50f;
			totalReward += reward;
			System.out.println("Total reward: " + totalReward);
			Util.gsm.init();
			totalReward = 0;
		}
		
		totalReward += reward;
		
		int currRow = (int)y / tileSize;
		int currCol = (int)x / tileSize;	
		
		State current = new State(currCol, currRow, reward);
		Action next = policy.nextMove(current);
		Action randomOutcome = tf.successor(current, next);
		
		//TODO This is where the learning update should happen
		tdlr.updateUtilities(current, previous);
		this.policy = tdlr.updatePolicy(tileMap, this.policy);
		previous = current;		
		
		return randomOutcome;
	}
	
	
	public float getTotalReward() {
		return totalReward;
	}
	
}

