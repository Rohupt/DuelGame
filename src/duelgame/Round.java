/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame;

public class Round {
	private Match match;
	private Player player1;
	private Player player2;
	private Action p1Actions[] = new Action[3];
	private Action p2Actions[] = new Action[3];

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Action[] getP1Actions() {
		return p1Actions;
	}

	public void setP1Actions(Action[] p1Actions) {
		this.p1Actions = p1Actions;
	}

	public Action[] getP2Actions() {
		return p2Actions;
	}

	public void setP2Actions(Action[] p2Actions) {
		this.p2Actions = p2Actions;
	}	

	public void setActions(Action[] p1Actions, Action[] p2Actions) {
		this.setP1Actions(p1Actions);
		this.setP2Actions(p2Actions);
	}
	
	public void resetActions() {
		Action doNothing[] = {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING};
		this.setActions(doNothing, doNothing);
	}
	
	public Player[] newRoundPlayers() {
		Player[] newPlayer = new Player[2];
		newPlayer[0] = new Player(this.player1);
		newPlayer[1] = new Player(this.player2);
		newPlayer[0].setActions(p1Actions);
		newPlayer[1].setActions(p2Actions);
		Player tempPlayer = new Player(newPlayer[0]);
		tempPlayer.setActions(p1Actions);
		newPlayer[0].setNewAttrs(newPlayer[1]);
		newPlayer[1].setNewAttrs(tempPlayer);
		return newPlayer;
	}
}
