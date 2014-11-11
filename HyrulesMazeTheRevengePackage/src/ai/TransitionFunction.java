package ai;

import java.util.Random;

public class TransitionFunction {
	private int map[][];
	private Random dice;
	
	
	public TransitionFunction(int map[][]) {
		this.map = map;
		this.dice = new Random();
	}
	

	public Action successor(State s, Action a) {
		
		if (a == Action.NONE) return a;
		
		Action realAction = Action.NONE;
		
		double r = dice.nextDouble();
		if(r < 0.7){
			realAction = a;
		}
		
		//esquerda, cima
		else if(r< 0.85){
			if(a == Action.UP || a == Action.DOWN){
				realAction = Action.LEFT;
			}
			else{
				realAction = Action.UP;
			}
		}
		
		//direita, baixo
		else{
			if(a == Action.UP || a == Action.DOWN){
				realAction = Action.RIGHT;
			}
			else{
				realAction = Action.DOWN;
			}
		}
		
		return realAction;
	}

}
