package GameUnit;

import game.*;

public class Monster extends Unit{
	protected int power;
	protected boolean miss;
	public Monster(int hp, int att, int def, String name,int exp,int gold) {
		super(hp, hp, att, def, name,exp,gold);
	}
	
	protected void attackMonster(Unit unit) {
		int num = Input.getRandomMenu(10)+1;
		if(num == 1) {
			System.out.println(Input.red+unit.getName()+"(가/이) 공격을 회피했습니다." + Input.exit);
			miss = true;
			return;
		}
		if(unit.getDefs()) {
			power /= 2;
		}
		if(power-(unit.getDef()/2) <= 0) {
			System.out.println(Input.blue + unit.getName()+"(가/이) 공격을 방어했습니다.."+ Input.exit);
			miss = true;
			return;
		}else {
			unit.setHp(unit.getHp() - (power-(unit.getDef()/2)));
		}
		unit.setDefs(false);
	}
	@Override
	public void Attack(Unit unit) {
		power = Input.getRandomMonster(super.getAtt());
		attackMonster(unit);
		if(unit.getHp() <= 0) {
			unit.setHp(0);
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",super.getName(),unit.getName(),power-(unit.getDef()/2));
		}else if(unit.getHp() >= 0 && !miss){
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",super.getName(),unit.getName(),power-(unit.getDef()/2));
		}
		System.out.println(unit);
		System.out.println("========================================");
		miss = false;
	}

	@Override
	public void Defense(boolean defs) {
		System.out.printf("%s가 방어자세를 취했습니다.%n",this.getName());
		this.setDefs(defs);
	}

	@Override
	public String toString() {
		return Input.green + "%s [체력 : %d] [공격력 : %d] [방어력 : %d]".formatted(this.getName(),this.getHp(),this.getAtt(),this.getDef()) + Input.exit;
	}


}
