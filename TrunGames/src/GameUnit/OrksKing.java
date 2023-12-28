package GameUnit;

import game.Input;

public class OrksKing extends Monster {
	boolean skill;
	public OrksKing() {
		super(150,20,5,"오크킹",1200,1500);
	}

	@Override
	public void Attack(Unit unit) {
		int num = Input.getRandomMenu(6);
		if(num == 1) {
			skill = true;
			System.out.println("오크킹이 필살기를 시전합니다.");
			return;
		}
		if(skill) {
			this.power = Input.getRandomMonster(this.getAtt()) * 2;
		}else {
			this.power = Input.getRandomMonster(this.getAtt());
		}
		this.attackMonster(unit);
		if(unit.getHp() <= 0) {
			unit.setHp(0);
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",super.getName(),unit.getName(),power-(unit.getDef()/2));
		}else if(unit.getHp() >= 0 && !miss){
			System.out.printf("%s가 %s 에게 %d 를 입혔습니다.%n",this.getName(),unit.getName(),power-(unit.getDef()/2));
		}
		System.out.println(this);
		System.out.println("========================================");
		System.out.println(unit);
		miss = false;
	}
}
