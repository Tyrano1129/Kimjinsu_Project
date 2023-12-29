package GameUnit;

import java.util.Objects;

import game.Input;
import item.*;

public class Adventurer extends Unit{
	  
	private int power;
	private boolean miss;
	private int expMax;
	private int level;
	private Armor armro;
	private Ring ring;
	private Weapon weapon;
	private int potion;
	public Adventurer(int hp, int att, int def,String name) {
		super(hp,hp,att,def,name,0,1000);
		this.level = 1;
		this.expMax = level * 100;
		this.potion = 5;
	}
	
	// 장비할때
	
	public void setTing(Item item) {
		if(item instanceof Ring) {
			if(ring != null) {
				if (ring.getName().equals(item.getName())) {
					System.out.println("이미 같은걸 착용중입니다.");
					return;
				}
			}
			ring = (Ring)item;
			super.setAtt(super.getAtt() + ring.getAtt());
			super.setDef(super.getDef() + ring.getDef());
			super.setHpMax(super.getHpMax() + ring.getHp());
		}
		if(item instanceof Weapon) {
			if(weapon != null) {
				if (weapon.getName().equals(item.getName())) {
					System.out.println("이미 같은걸 착용중입니다.");
					return;
				}
			}
			weapon = (Weapon)item;
			super.setAtt(super.getAtt() + weapon.getAtt());
			super.setDef(super.getDef() + weapon.getDef());
			super.setHpMax(super.getHpMax() + weapon.getHp());
		}
		if(item instanceof Armor) {
			if(armro != null) {
				if (armro.getName().equals(item.getName())) {
					System.out.println("이미 같은걸 착용중입니다.");
					return;
				}
			}
			armro = (Armor)item;
			super.setAtt(super.getAtt() + armro.getAtt());
			super.setDef(super.getDef() + armro.getDef());
			super.setHpMax(super.getHpMax() + armro.getHp());
		}
	}
	// 장비 해제
	public void unequip(int kind) {
		if(kind == 3) {
			super.setAtt(super.getAtt() - ring.getAtt());
			super.setDef(super.getDef() - ring.getDef());
			super.setHpMax(super.getHpMax() - ring.getHp());
			ring = null;
		}
		if(kind == 1) {
			super.setAtt(super.getAtt() - weapon.getAtt());
			super.setDef(super.getDef() - weapon.getDef());
			super.setHpMax(super.getHpMax() - weapon.getHp());
			weapon = null;
		}
		if(kind == 2) {
			super.setAtt(super.getAtt() - armro.getAtt());
			super.setDef(super.getDef() - armro.getDef());
			super.setHpMax(super.getHpMax() - armro.getHp());
			armro = null;
		}
	}
	//포션사용
	public void potionHealing() {
		potion -=1;
		if(this.getHp() + 30 > this.getHpMax()) {
			this.setHp(this.getHpMax());
		}else {
			this.setHp(this.getHp() + 30);
		}
		System.out.printf(Input.green + "회복약 사용 체력이 30 찹니다. (남은갯수  : %d)%n" + Input.exit,potion);
		System.out.println(this);
	}
	// 공격
	private void attackHero(Unit unit) {
		int num = Input.getRandomMenu(10)+1;
		if(num == 1) {
			System.out.println(Input.red +unit.getName()+ "(가/이) 공격을 회피했습니다." + Input.exit);
			miss = true;
			return;
		}
		if(num == 2) {
			System.out.println(Input.red + "크리티컬!!!!!!!!!!" + Input.exit);
			power *= 2;
		}
		if(unit.getDefs()) {
			power /= 2;
		}
		if(power-(unit.getDef()/2) <= 0) {
			System.out.println(Input.blue+"(가/이) 공격을 방어했습니다."+Input.exit);
			miss = true;
		}else {
			unit.setHp(unit.getHp() - (power-(unit.getDef()/2)));
		}
		unit.setDefs(false);
	}
	@Override
	public void Attack(Unit unit) {
		power = Input.getRandomHero(getAtt());
		attackHero(unit);
		if(unit.getHp() <= 0) {
			unit.setHp(0);
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",super.getName(),unit.getName(),power-(unit.getDef()/2));
			goldexpUp(unit);
		}else if(unit.getHp() >= 0 && !miss){
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",this.getName(),unit.getName(),power-(unit.getDef()/2));
		}
		System.out.println(unit);
		System.out.println("========================================");
		miss = false;
	}
	//레벨 업
	private void levelUp() {
		if(super.getExp() >= expMax) {
			System.out.println("레벨업!!!");
			super.setExp(0);
			this.setAtt(this.getAtt()+2);
			this.setDef(this.getDef()+1);
			this.setHpMax(this.getHpMax() + 2);
			this.setHp(this.getHpMax());
			level += 1;
			expMax = level * 120;
		}
	}
	//경험치 업
	private void goldexpUp(Unit unit) {
		super.setExp(super.getExp() + unit.getExp());
		this.setGold(this.getGold() + unit.getGold());
		levelUp();
	}
	@Override
	public String toString() {
		String data = String.format("[체력 : %d / %d] [공격력 : %d] [방어력 : %d] %n[레벨 : %d] [경험치 : %d / %d] %n[골드 : %d]",
				this.getHp(),this.getHpMax(),this.getAtt(),this.getDef(),this.level,this.getExp(),this.expMax,this.getGold());
		if(this.weapon != null) {
			data +="%n[무기 : %s]".formatted(weapon.getName());
		}
		if(this.ring != null) {
			data +="%n[반지 : %s]".formatted(ring.getName());
		}
		if(this.armro != null) {
			data +="%n[방어구 : %s]".formatted(armro.getName());
		}
		return data;
	}
	@Override
	public void Defense(boolean defs) {
		System.out.printf("%s가 방어자세를 취했습니다.%n",this.getName());
		this.setDefs(defs);
	}
	public Armor getArmro() {
		return armro;
	}
	public void setArmro(Armor armro) {
		this.armro = armro;
	}
	public Ring getRing() {
		return ring;
	}
	public void setRing(Ring ring) {
		this.ring = ring;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
