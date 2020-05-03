/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame.mount;

import duelgame.Player;

public abstract class Mount {
	private Player owner;
	
	public abstract String getNAME();
	
	public abstract byte getLEVEL();
	
	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public abstract boolean hasInnateEffect();

	public abstract boolean hasPassiveEffect();

	public abstract boolean hasActiveEffect();
	
	public abstract void triggerInnateEffect(byte state);
	
	public abstract void triggerPassiveEffect(byte state);
	
	public abstract void triggerActiveEffect(byte state);
}
