package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ai.Player;
import ai.State;
import ai.TicTacToe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MiniMaxTest {
	
	private static final int PLAYS = 100;

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}
	
	@Test
	public final void testBasicMinimaxVsRandom() throws Exception {
		TicTacToe game;
		@SuppressWarnings("unused")
		int wins=0, losses=0, draws=0;
		for(int i=0; i< PLAYS; i++) {
			game = new TicTacToe(Player.MINIMAX_PLAYER, Player.RANDOM_PLAYER);
			game.setSilent(true);
			int winner = game.playGame();
			switch(winner) {
			case State.PLAYER_X:
				wins++;
				break;
			case State.PLAYER_O:
				losses++;
				break;
			case -1:
				draws++;
				break;
			}
		}
		assertTrue(winsPercentage(wins, PLAYS) >= 0.5f);
	}
	
	@Test
	public final void testBasicRandomVsMinimax() throws Exception {
		TicTacToe game;
		int wins=0, losses=0, draws=0;
		for(int i=0; i< PLAYS; i++) {
			game = new TicTacToe(Player.RANDOM_PLAYER, Player.MINIMAX_PLAYER);
			game.setSilent(true);
			int winner = game.playGame();
			switch(winner) {
			case State.PLAYER_X:
				losses++;
				break;
			case State.PLAYER_O:
				wins++;
				break;
			case -1:
				draws++;
				break;
			}
		}
		assertTrue(winsPercentage(wins, PLAYS) >= 0.2f);
	}

	private float winsPercentage(int wins, int games) {
		return ((float)wins)/((float)games);
	}
}
