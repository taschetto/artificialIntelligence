package ai;

import gamestate.GameStateManager;

public final class Util {

	public static String map = "/maps/medium.map";
	public static Double velocity = 30.0;
	public static IPolicy policy = new StaticPolicy();
	
	
	public static GameStateManager gsm = new GameStateManager();
	
	
}
