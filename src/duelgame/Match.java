/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame;

import java.util.ArrayList;

public class Match {
	private static final int DEFAULT_ROUNDS_NUM = 7;
	private static final int MIN_ROUNDS_NUM = 3;
	private static final int MAX_ROUNDS_NUM = 15;
	private static final int DEFAULT_ROUNDS_ADDED = 2;
	private static final int MIN_ROUNDS_ADDED = 0;
	private static final int MAX_ROUNDS_ADDED = 4;

	private Player player1;
	private Player player2;
	@SuppressWarnings("FieldMayBeFinal")
	private ArrayList<Round> rounds = new ArrayList<>();
	private int roundNumber;
	private int roundAdded;
	private boolean weaponsAllowed;
	private boolean mountsAllowed;
	private boolean finished;
	// index number 0: HP Sum Check; 1: Body HP Check; 2: Items Level Check; 3: Actions Check
	private boolean matchDecisionCriteria[] = new boolean[4];

	//<editor-fold defaultstate="collapsed" desc="Constructors, Getters, Setters">
	public Match() {
		this.roundNumber = DEFAULT_ROUNDS_NUM;
		this.roundAdded = DEFAULT_ROUNDS_ADDED;
		this.weaponsAllowed = true;
		this.mountsAllowed = true;		
		this.matchDecisionCriteria = new boolean[] {true, true, true, true};
		this.finished = false;
	}

	public static int getDEFAULT_ROUNDS_NUM() {
		return DEFAULT_ROUNDS_NUM;
	}

	public static int getMIN_ROUNDS_NUM() {
		return MIN_ROUNDS_NUM;
	}

	public static int getMAX_ROUNDS_NUM() {
		return MAX_ROUNDS_NUM;
	}

	public static int getDEFAULT_ROUNDS_ADDED() {
		return DEFAULT_ROUNDS_ADDED;
	}

	public static int getMIN_ROUNDS_ADDED() {
		return MIN_ROUNDS_ADDED;
	}

	public static int getMAX_ROUNDS_ADDED() {
		return MAX_ROUNDS_ADDED;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		player1.setActions(new Action[] {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING});
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		player2.setActions(new Action[] {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING});
		this.player2 = player2;
	}

	public ArrayList<Round> getRounds() {
		return rounds;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public int getRoundAdded() {
		return roundAdded;
	}

	public void setRoundAdded(int roundAdded) {
		this.roundAdded = roundAdded;
	}

	public boolean isWeaponsAllowed() {
		return weaponsAllowed;
	}

	public void setWeaponsAllowed(boolean weaponsAllowed) {
		this.weaponsAllowed = weaponsAllowed;
	}

	public boolean isMountsAllowed() {
		return mountsAllowed;
	}

	public void setMountsAllowed(boolean mountsAllowed) {
		this.mountsAllowed = mountsAllowed;
	}

	public boolean[] getMatchDecisionCriteria() {
		return matchDecisionCriteria;
	}

	public void setMatchDecisionCriteria(boolean[] matchDecisionCriteria) {
		this.matchDecisionCriteria = matchDecisionCriteria;
	}	

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	//</editor-fold>

	// 0: not decided (continue); 1: p1 won; 2: p2 won; -1: tied
	public byte result() {
		// 0: not decided (continue); 1: p1 won; 2: p2 won;
		byte hpCheck = (byte) (!matchDecisionCriteria[0] ? 0 : (3 + Integer.compare(player1.getBodyHp() + player1.getLeftHp() + player1.getRightHp(), player2.getBodyHp() + player2.getLeftHp() + player2.getRightHp())) % 3);
		byte bodyHpCheck = (byte) (!matchDecisionCriteria[1] ? 0 : (3 + Integer.compare(player1.getBodyHp(), player2.getBodyHp())) % 3);
		byte itemsLevelCheck = (byte) (!matchDecisionCriteria[2] ? 0 : 0);
		byte actionsCheck = (byte) (!matchDecisionCriteria[3] ? 0 : (3 + Integer.compare(player2.getActionCount(), player1.getActionCount())) % 3);
		// 0: not decided (continue); 1: p1 won; 2: p2 won; -1: tied
		byte loseCheck = (byte) (!player1.isLost() ? (!player2.isLost() ? 0 : 1) : (!player2.isLost() ? 2 : -1));
		byte bodyCheck = (byte) (!player1.isDisabledBody() ? (!player2.isDisabledBody() ? 0 : 1) : (!player2.isDisabledBody() ? 2 : -1));
		byte armsCheck = (byte) (!player1.isDisabledBody() ? (!player2.isDisabledArms() ? 0 : 1) : (!player2.isDisabledArms() ? 2 : -1));
		// 0: main rounds; 1: final main round / additional rounds; -1: final round
		byte roundCheck = (byte) (rounds.size() - 1 < roundNumber ? 0 : (rounds.size() - 1 < roundNumber + roundAdded ? 1 : -1));
		
		switch (loseCheck) {
			case 0:
				if (roundCheck != 0) {
					if (hpCheck == 0) {
						if (bodyHpCheck == 0) {
							if (itemsLevelCheck == 0) {
								if (actionsCheck == 0) {
									return (byte) ((roundCheck - 1) / 2); //-1 if -1; 0 if 1
								} else return actionsCheck;
							} else return itemsLevelCheck;
						} else return bodyHpCheck;
					} else return hpCheck;
				} else return roundCheck;
			case -1:
				switch (bodyCheck) {
					case 0:
						if (roundCheck != 0) {
							if (bodyHpCheck == 0) {
								if (itemsLevelCheck == 0) {
									if (actionsCheck == 0) {
										return (byte) ((roundCheck - 1) / 2); //-1 if -1; 0 if 1
									} else return actionsCheck;
								} else return itemsLevelCheck;
							} else return bodyHpCheck;
						} else return roundCheck;
					case -1:
						if (roundCheck != -1)
							if (armsCheck <= 0)
								return 0;
							else return armsCheck;
						else return roundCheck;
					default:
						return bodyCheck;
				}
			default:
				return loseCheck;
		}
	}

	public void performTieBreakHeal() {
		if (this.result() <= 0 && rounds.size() < roundNumber + roundAdded) {
			if (player1.isDisabledBody() && player2.isDisabledBody()) {
				player1.modBodyHp(1);
				player2.modBodyHp(1);				
			}
			if (player1.isDisabledArms() && player2.isDisabledArms()) {
				player1.modLeftHp(2);
				player1.modRightHp(2);
				player2.modLeftHp(2);
				player2.modRightHp(2);
			}
		}
	}

	public void addNewRound() {
		Round round = new Round();
		round.setMatch(this);
		round.setPlayer1(player1);
		round.setPlayer2(player2);
		rounds.add(round);
	}

	public void toNextRound(Action[] p1Actions, Action[] p2Actions) {
		rounds.get(rounds.size() - 1).setActions(p1Actions, p2Actions);
		player1 = rounds.get(rounds.size() - 1).newRoundPlayers()[0];
		player2 = rounds.get(rounds.size() - 1).newRoundPlayers()[1];
		this.performTieBreakHeal();
		if (this.result() == 0)
			this.addNewRound();
		else this.finished = true;
	}
	
	public void fixOldRound(int roundIndex) {
		if (roundIndex < rounds.size() - 1) {
			for (int i = rounds.size() - 1; i > roundIndex; i--)
				rounds.remove(rounds.get(i));
			rounds.get(roundIndex).resetActions();
		}
		this.finished = false;
	}
}
