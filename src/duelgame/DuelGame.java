/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame;

import gui.Main;
import javafx.application.Application;

public class DuelGame{
	
	private static Match match;
	private static Match tempNewMatch;
	private static boolean newMatchReady;
	
	public static Match getMatch() {
		return match;
	}

	public static void setMatch(Match aMatch) {
		match = aMatch;
	}

	public static Match getTempNewMatch() {
		return tempNewMatch;
	}

	public static void setTempNewMatch(Match aTempNewMatch) {
		tempNewMatch = aTempNewMatch;
	}

	public static boolean isNewMatchReady() {
		return newMatchReady;
	}

	public static void setNewMatchReady(boolean newMatchReady) {
		DuelGame.newMatchReady = newMatchReady;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}	

	
}
