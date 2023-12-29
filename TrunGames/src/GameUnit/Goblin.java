package GameUnit;

import game.Input;

public class Goblin extends Monster {
	public Goblin() {
		super(50,11,2,"고블린",50,200);
	}
	
	@Override
	public void Attack(Unit unit) {
		power = Input.getRandomMonster(super.getAtt());
		attackMonster(unit);
		Debuff(unit);
		if (unit.getHp() <= 0) {
			unit.setHp(0);
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n", this.getName(), unit.getName(), power - (unit.getDef() / 2));
		} else if (unit.getHp() >= 0 && !miss) {
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n", this.getName(), unit.getName(), power - (unit.getDef() / 2));
		}
		
		System.out.println(unit);
		System.out.println("========================================");
		miss = false;
	}
	@Override
	public void Debuff(Unit unit) {
		int duf = Input.getRandomMenu(10);
		if(duf == 1 && unit.getDebuff() == 0 && !miss) {
			unit.setDebuff(3);
			System.out.println("독에 걸렸습니다!");
		}
		
	}
	@Override
	public void debuffCnt(Unit unit) {
		if(unit.getDebuff() > 0) {
			System.out.printf("%s가 %s 에게 독에 걸려 %d데미지를 입혔습니다.%n",unit.getName(),this.getName(),3);
			unit.setDebuff(unit.getDebuff() - 1);
			unit.setHp(unit.getHp() - 3);
			if(unit.getDebuff() == 0) {
				System.out.println("독이 해제되었습니다.");
			}
		}
	}
}
