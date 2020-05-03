/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame;

import duelgame.weapon.Weapon;
import duelgame.mount.Mount;

public class Player {
	private static final int BODY_HP = 24;
	private static final int ARM_HP = 12;
	private static final int BASIC_ATK = 10;
	private static final int BASIC_DEF = 12;
	private static final int MIN_ATK_DEF = 3;
	private int initBodyHp, initLeftHp, initRightHp;
	private int bodyHp, leftHp, rightHp;
	private int initLeftAtk, initRightAtk, initLeftDef, initRightDef;
	private int leftAtk, rightAtk, leftDef, rightDef;
	private int actionCount;
	private String name;
	private Weapon weapon;
	private Mount mount;
	//Index number 0: left action; number 1: body action; number 2: right action
	@SuppressWarnings("FieldMayBeFinal")
	private Action actions[] = new Action[3];
	
	// <editor-fold defaultstate="collapsed" desc="Getters-Setters">
	public static int getBODY_HP() {
		return BODY_HP;
	}

	public static int getARM_HP() {
		return ARM_HP;
	}

	public static int getBASIC_ATK() {
		return BASIC_ATK;
	}

	public static int getBASIC_DEF() {
		return BASIC_DEF;
	}

	public static int getMIN_ATK_DEF() {
		return MIN_ATK_DEF;
	}

	public int getInitBodyHp() {
		return initBodyHp;
	}

	public void setInitBodyHp(int initBodyHp) {
		this.initBodyHp = initBodyHp;
	}

	public void modInitBodyHp(int amount) {
		this.initBodyHp += amount;
	}

	public int getInitLeftHp() {
		return initLeftHp;
	}

	public void setInitLeftHp(int initLeftHp) {
		this.initLeftHp = initLeftHp;
	}

	public void modInitLeftHp(int amount) {
		this.initLeftHp += amount;
	}

	public int getInitRightHp() {
		return initRightHp;
	}

	public void setInitRightHp(int initRightHp) {
		this.initRightHp = initRightHp;
	}

	public void modInitRightHp(int amount) {
		this.initRightHp += amount;
	}

	public int getBodyHp() {
		return bodyHp;
	}

	public void setBodyHp(int bodyHp) {
		this.bodyHp = bodyHp;
	}

	public void modBodyHp(int amount) {
		this.bodyHp += amount;
		if (this.bodyHp < 0) this.bodyHp = 0;
		if (this.bodyHp - amount <= this.initBodyHp && this.bodyHp > this.initBodyHp) this.bodyHp = this.initBodyHp;
	}

	public int getLeftHp() {
		return leftHp;
	}

	public void setLeftHp(int leftHp) {
		this.leftHp = leftHp;
	}

	public void modLeftHp(int amount) {
		this.leftHp += amount;
		if (this.leftHp < 0) this.leftHp = 0;
		if (this.leftHp - amount <= this.initLeftHp && this.leftHp > this.initLeftHp) this.leftHp = this.initLeftHp;
	}

	public int getRightHp() {
		return rightHp;
	}

	public void setRightHp(int rightHp) {
		this.rightHp = rightHp;
	}

	public void modRightHp(int amount) {
		this.rightHp += amount;
		if (this.rightHp < 0) this.rightHp = 0;
		if (this.rightHp - amount <= this.initRightHp && this.rightHp > this.initRightHp) this.rightHp = this.initRightHp;
	}

	public int getInitLeftAtk() {
		return initLeftAtk;
	}

	public void setInitLeftAtk(int initLeftAtk) {
		this.initLeftAtk = initLeftAtk;
	}

	public int getInitRightAtk() {
		return initRightAtk;
	}

	public void setInitRightAtk(int initRightAtk) {
		this.initRightAtk = initRightAtk;
	}

	public int getInitLeftDef() {
		return initLeftDef;
	}

	public void setInitLeftDef(int initLeftDef) {
		this.initLeftDef = initLeftDef;
	}

	public int getInitRightDef() {
		return initRightDef;
	}

	public void setInitRightDef(int initRightDef) {
		this.initRightDef = initRightDef;
	}

	public int getLeftAtk() {
		return leftAtk;
	}

	public void setLeftAtk(int leftAtk) {
		this.leftAtk = leftAtk;
	}

	public int getRightAtk() {
		return rightAtk;
	}

	public void setRightAtk(int rightAtk) {
		this.rightAtk = rightAtk;
	}

	public int getLeftDef() {
		return leftDef;
	}

	public void setLeftDef(int leftDef) {
		this.leftDef = leftDef;
	}

	public int getRightDef() {
		return rightDef;
	}

	public void setRightDef(int rightDef) {
		this.rightDef = rightDef;
	}

	public int getActionCount() {
		return actionCount;
	}

	public void setActionCount(int actionCount) {
		this.actionCount = actionCount;
	}

	public void modActionCount(int actions) {
		this.actionCount += actions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Mount getMount() {
		return mount;
	}

	public void setMount(Mount mount) {
		this.mount = mount;
	}

	public Action[] getActions() {
		return actions;
	}

	public void setActions(Action[] actions) {
		this.actions = actions;
	}
	
	public void resetActions() {
		Action doNothing[] = {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING};
		this.setActions(doNothing);
	}
	
	// </editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Constructors">
	public Player() {
		this.initBodyHp = BODY_HP;
		this.initLeftHp = ARM_HP;
		this.initRightHp = ARM_HP;
		this.bodyHp = this.initBodyHp;
		this.leftHp = this.initLeftHp;
		this.rightHp = this.initRightHp;
		this.actionCount = 0;
	}

	public Player(Player p) {
		this.initBodyHp = p.getInitBodyHp();
		this.initLeftHp = p.getInitLeftHp();
		this.initRightHp = p.getInitRightHp();
		this.bodyHp = p.getBodyHp();
		this.leftHp = p.getLeftHp();
		this.rightHp = p.getRightHp();
		this.initLeftAtk = p.getInitLeftAtk();
		this.initLeftDef = p.getInitLeftDef();
		this.initRightAtk = p.getInitRightAtk();
		this.initRightDef = p.getInitRightDef();
		this.leftAtk = p.getLeftAtk();
		this.rightAtk = p.getRightAtk();
		this.leftDef = p.getLeftDef();
		this.rightDef = p.getRightDef();
		this.actionCount = p.getActionCount();
		this.name = p.getName();
		this.weapon = p.getWeapon();
		this.mount = p.getMount();
	}

	public void initiate(String name, int initRightAtk, int initRightDef, Weapon weapon, Mount mount) {
		this.setName(name);
		this.setInitRightAtk(initRightAtk);
		this.setInitRightDef(initRightDef);
		this.setInitLeftAtk(BASIC_ATK - initRightAtk);
		this.setInitLeftDef(BASIC_DEF - initRightDef);
		this.setRightAtk(this.initRightAtk);
		this.setRightDef(this.initRightDef);
		this.setLeftAtk(this.initLeftAtk);
		this.setLeftDef(this.initLeftDef);
		this.setWeapon(weapon);
		this.weapon.setOwner(this);
		this.setMount(mount);
		this.mount.setOwner(this);
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Checks">
	public boolean isDisabledLeft() {
		return this.leftHp == 0;
	}

	public boolean isDisabledRight() {
		return this.rightHp == 0;
	}
	
	public boolean isDisabledArms() {
		return this.isDisabledLeft() && this.isDisabledRight();
	}

	public boolean isDisabledBody() {
		return this.bodyHp == 0;
	}

	public boolean isLost() {
		return this.isDisabledArms() || this.isDisabledBody();
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="Match Procedures and Functions">
	public int leftDeal(Action action) {
		switch (action) {
			case ATK_NEAR:
				return this.getLeftAtk();
			case ATK_BODY:
				return Integer.max(this.getLeftAtk() - 1, 0);
			case ATK_FAR:
				return Integer.max(this.getLeftAtk() - 2, 0);
			case DEF_NEAR:
				return this.getLeftDef();
			case DEF_BODY:
				return Integer.max(this.getLeftDef() - 1, 0);
			case DEF_FAR:
				return Integer.max(this.getLeftDef() - 2, 0);
			default:
				return 0;
		}
	}
	
	public int rightDeal(Action action) {
		switch (action) {
			case ATK_NEAR:
				return this.getRightAtk();
			case ATK_BODY:
				return Integer.max(this.getRightAtk() - 1, 0);
			case ATK_FAR:
				return Integer.max(this.getRightAtk() - 2, 0);
			case DEF_NEAR:
				return this.getRightDef();
			case DEF_BODY:
				return Integer.max(this.getRightDef() - 1, 0);
			case DEF_FAR:
				return Integer.max(this.getRightDef() - 2, 0);
			default:
				return 0;
		}
	}
	
	public int oppRightAtkDeal() {
		int deal = 0;
		if (Action.ATK_NEAR.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.ATK_FAR.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int oppBodyAtkDeal() {
		int deal = 0;
		if (Action.ATK_BODY.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.ATK_BODY.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int oppLeftAtkDeal() {
		int deal = 0;
		if (Action.ATK_FAR.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.ATK_NEAR.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int plrLeftDefDeal() {
		int deal = 0;
		if (Action.DEF_NEAR.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.DEF_FAR.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int plrBodyDefDeal() {
		int deal = 0;
		if (Action.DEF_BODY.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.DEF_BODY.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int plrRightDefDeal() {
		int deal = 0;
		if (Action.DEF_FAR.equals(actions[0])) deal += this.leftDeal(actions[0]);
		if (Action.DEF_NEAR.equals(actions[2])) deal += this.rightDeal(actions[2]);
		return deal;
	}
	
	public int plrLeftHpLost(Player opponent) {
		int damage = 0;
		
		damage += actions[0].getCost();
		if (Action.DODGE.equals(actions[0]))
			return damage;
		if (opponent.oppLeftAtkDeal() > this.plrLeftDefDeal())
			damage += opponent.oppLeftAtkDeal() - this.plrLeftDefDeal();
		
		switch (actions[0]) {
			case ATK_NEAR:
				if (this.oppRightAtkDeal() < opponent.plrRightDefDeal())
					if (Action.ATK_FAR.equals(actions[2]))
						damage += (opponent.plrRightDefDeal() - this.oppRightAtkDeal()) / 2 / 2;
					else damage += opponent.plrRightDefDeal() - this.oppRightAtkDeal() / 2;
				break;
			case ATK_BODY:
				if (this.oppBodyAtkDeal() < opponent.plrBodyDefDeal())
					if (Action.ATK_BODY.equals(actions[2]))
						damage += (opponent.plrBodyDefDeal() - this.oppBodyAtkDeal()) / 2 / 2;
					else damage += opponent.plrBodyDefDeal() - this.oppBodyAtkDeal() / 2;
				break;
			case ATK_FAR:
				if (this.oppLeftAtkDeal() < opponent.plrLeftDefDeal())
					if (Action.ATK_NEAR.equals(actions[2]))
						damage += (opponent.plrLeftDefDeal() - this.oppLeftAtkDeal()) / 2 / 2;
					else damage += opponent.plrLeftDefDeal() - this.oppLeftAtkDeal() / 2;
				break;
			default:
				break;
		}
		
		return damage;
	}
	
	public int plrBodyHpLost(Player opponent) {
		int damage = 0;
		
		damage += actions[1].getCost();
		if (Action.DODGE.equals(actions[1]))
			return damage;
		if (opponent.oppBodyAtkDeal() > this.plrBodyDefDeal())
			damage += opponent.oppBodyAtkDeal() - this.plrBodyDefDeal();
		
		return damage;
	}
	
	public int plrRightHpLost(Player opponent) {
		int damage = 0;
		
		damage += actions[2].getCost();
		if (Action.DODGE.equals(actions[2]))
			return damage;
		if (opponent.oppRightAtkDeal() > this.plrRightDefDeal())
			damage += opponent.oppRightAtkDeal() - this.plrRightDefDeal();
		
		switch (actions[2]) {
			case ATK_NEAR:
				if (this.oppLeftAtkDeal() < opponent.plrLeftDefDeal())
					if (Action.ATK_FAR.equals(actions[0]))
						damage += (opponent.plrLeftDefDeal() - this.oppLeftAtkDeal()) / 2 / 2;
					else damage += opponent.plrLeftDefDeal() - this.oppLeftAtkDeal() / 2;
				break;
			case ATK_BODY:
				if (this.oppBodyAtkDeal() < opponent.plrBodyDefDeal())
					if (Action.ATK_BODY.equals(actions[0]))
						damage += (opponent.plrBodyDefDeal() - this.oppBodyAtkDeal()) / 2 / 2;
					else damage += opponent.plrBodyDefDeal() - this.oppBodyAtkDeal() / 2;
				break;
			case ATK_FAR:
				if (this.oppRightAtkDeal() < opponent.plrRightDefDeal())
					if (Action.ATK_NEAR.equals(actions[0]))
						damage += (opponent.plrRightDefDeal() - this.oppRightAtkDeal()) / 2 / 2;
					else damage += opponent.plrRightDefDeal() - this.oppRightAtkDeal() / 2;
				break;
			default:
				break;
		}
		
		return damage;
	}
	
	public void setNewAttrs(Player opponent) {
		this.modLeftHp(-this.plrLeftHpLost(opponent));
		this.modBodyHp(-this.plrBodyHpLost(opponent));
		this.modRightHp(-this.plrRightHpLost(opponent));
		this.leftAtk = Integer.max(this.initLeftAtk - (this.initLeftHp - this.leftHp) / 3, 0);
		this.leftDef = Integer.max(this.initLeftDef - (this.initLeftHp - this.leftHp) / 3, 0);
		this.rightAtk = Integer.max(this.initRightAtk - (this.initRightHp - this.rightHp) / 3, 0);
		this.rightDef = Integer.max(this.initRightDef - (this.initRightHp - this.rightHp) / 3, 0);
		for (Action _action : actions) this.modActionCount(_action.getCost());
	}
	//</editor-fold>
}
