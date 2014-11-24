package ai;

import gamestate.GameStateManager;

public final class Util {

	public static String map = "/maps/easy.map";
	public static Double velocity = 10.0;
	public static IPolicy policy = new StaticPolicy();
	
	
	public static GameStateManager gsm = new GameStateManager();
	
	
}
