package ai;

import java.util.ArrayList;

public class TransitionFunction {
	
	
	
	public Action[] availableActions(State s) {
		ArrayList<Action> acts = new ArrayList<>();
		
		if( s.map[s.x].length > s.x + 1  && s.map[s.x + 1][s.y] != 1){
			acts.add(Action.DOWN);
		}
		if( s.x - 1 >= 0 && s.map[s.x - 1][s.y] != 1) {
			acts.add(Action.UP);
		}
		if( s.y - 1 >= 0 && s.map[s.x][s.y - 1] != 1) {
			acts.add(Action.LEFT);
		}
		if( s.map.length > s.y + 1 && s.map[s.x][s.y + 1] != 1) {
			acts.add(Action.RIGHT);
		}

		return acts.toArray(new Action[acts.size()]);
	}


	public State successor(State s, Action a) {
		State news = new State(s.x, s.y, s.map);
		if(a == Action.UP) {
			news.x--;
		} else if(a == Action.DOWN) {
			news.x++;
		} else if(a == Action.LEFT) {
			news.y--;
		} else if(a == Action.RIGHT) {
			news.y++;
		}
		return news;
	}

}
