package stage;

import GameUnit.*;
import game.GameManager;
import game.Input;

public class Battle implements Stage{

	@Override
	public void init(Adventurer hero,Monster monster) {
		battleAttack(hero,monster);
	}
	public void battleAttack(Adventurer hero,Monster monster) {
		System.out.printf("몬스터와 조우했습니다. %n%s와 전투가 시작됩니다.%n",monster.getName());
		int trun = 1;
		while(true) {
			if(trun == 1) {
				int sel = Input.getValue("(1.공격하기) (2.방어하기) (3.포션사용)",1,3);
				if (sel == 1) {
					hero.Attack(monster);
				} else if (sel == 2) {
					hero.Defense(true);
				} else if (sel == 3) {
					hero.potionHealing();
				}
			}else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int sel = Input.getRandomMenu(4);
				if(sel >= 1) {
					monster.Attack(hero);
				}else if(sel == 0) {
					monster.Defense(true);
				}
			}
			trun = trun == 1? 2 : 1;
			if(monster.getHp() <= 0) {
				if(monster instanceof OrksKing) {
					System.out.println("던전의 보스를 처치했습니다!");
					System.out.println("수고하셨습니다!");
					GameManager.setStageName("");
					return;
				}
				System.out.println("몬스터에게 승리했습니다!");
				GameManager.setStageName("MOVE");
				return;
			}
			if(hero.getHp() <= 0) {
				System.out.println("히어로가 죽었습니다...");
				GameManager.setStageName("");
				return;
			}
		}
	}
	@Override
	public void init() {
		
	}
}
