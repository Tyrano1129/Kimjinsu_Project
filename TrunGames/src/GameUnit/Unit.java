package GameUnit;

import game.Attacker;
import game.Defense;

public abstract class Unit implements Attacker,Defense{
	private String name;
	private int hp;
	private int hpMax;
	private int att;
	private int def;
	private boolean defs;
	private int exp;
	private int gold;
	
	public Unit(int hp, int hpMax, int att, int def,String name,int exp,int gold) {
		this.name = name;
		this.hp = hp;
		this.hpMax = hpMax;
		this.att = att;
		this.def = def;
		this.exp = exp;
		this.gold = gold;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}
	public boolean getDefs() {
		return defs;
	}
	public void setDefs(boolean defs) {
		this.defs = defs;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
}
