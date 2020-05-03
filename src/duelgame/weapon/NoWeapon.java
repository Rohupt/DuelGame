/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */

package duelgame.weapon;

public class NoWeapon extends Weapon {
	private static final String NAME = "Không có vũ khí";
	private static final byte LEVEL = 0;
	private final boolean innateEffect = false;
	private final boolean passiveEffect = false;
	private final boolean activeEffect = false;

	public static String name()	 {
		return NAME;
	}
	
	public static byte level() {
		return LEVEL;
	}

	@Override
	public String getNAME() {
		return NAME;
	}

	@Override
	public byte getLEVEL() {
		return LEVEL;
	}
	
	@Override
	public boolean hasInnateEffect() {
		return innateEffect;
	}

	@Override
	public boolean hasPassiveEffect() {
		return passiveEffect;
	}

	@Override
	public boolean hasActiveEffect() {
		return activeEffect;
	}
	
	@Override
	public void triggerInnateEffect(byte state) {
		
	}

	@Override
	public void triggerPassiveEffect(byte state) {
		
	}

	@Override
	public void triggerActiveEffect(byte state) {
		
	}
}
