/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package duelgame;

public enum Action {
	DO_NOTHING(0, "Không làm gì", '0'),
	DODGE(1, "Né", 'd'),
	ATK_NEAR(1, "Công Gần", 'N'),
	ATK_BODY(1, "Công Thân", 'B'),
	ATK_FAR(1, "Công Xa", 'F'),
	DEF_NEAR(1, "Thủ Gần", 'n'),
	DEF_BODY(1, "Thủ Thân", 'b'),
	DEF_FAR(1, "Thủ Xa", 'f');
	
	private final int cost;
	private final String name;
	private final char abbr;
	
	Action(int cost, String name, char abbr) {
		this.cost = cost;
		this.name = name;
		this.abbr = abbr;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

	public char getAbbr() {
		return abbr;
	}	

	@Override
	public String toString() {
		return name;
	}
	
}
