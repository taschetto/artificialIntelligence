package ai;

import gamestate.GameStateManager;

public final class Util {

	public static String map = "/maps/empty.map";
	public static Double velocity = 2.0;
	public static IPolicy policy = new StaticPolicy();
	
	
	public static GameStateManager gsm = new GameStateManager();
	
	
}
