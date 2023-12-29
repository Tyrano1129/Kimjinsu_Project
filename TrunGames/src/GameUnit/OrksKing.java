package GameUnit;

import game.Input;
import stage.Battle;

public class OrksKing extends Monster {
	private boolean skill;
	private int cnt;
	private boolean pattern1;
	private boolean pattern2;
	public OrksKing() {
		super(300,20,5,"오크킹",1200,1500);
	}

	@Override
	public void Attack(Unit unit) {
		pattern();
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
		Debuff(unit);
		System.out.println(this);
		System.out.println("========================================");
		System.out.println(unit);
		miss = false;
	}
	
	public void pattern() {
		if(this.getHp() <= 100 && !pattern1) {
			System.out.println("오크킹이 광폭화했습니다. 추가턴을 가집니다.");
			cnt = 1;
			pattern1 = true;
		}else if(this.getHp() <= 50 && !pattern2) {
			System.out.println("오크킹이 점점더 강해집니다.");
			this.setAtt(this.getAtt()+2);
		}
	}
	@Override
	public void Debuff(Unit unit) {
		int duf = Input.getRandomMenu(10);
		if(duf == 1) {
			unit.setDebuff(1);
			System.out.println("오크킹에게 맞아 기절하였습니다.");
			Battle.setTrun(2);
		}
	}
	@Override
	public void debuffCnt(Unit unit) {
		if (unit.getDebuff() > 0) {
			System.out.printf("%s가 1턴 동안 움직이지 못합니다.%n", unit.getName());
			unit.setDebuff(unit.getDebuff() - 1);
		}
	}
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
