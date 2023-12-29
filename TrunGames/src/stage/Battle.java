package stage;

import GameUnit.*;
import game.GameManager;
import game.Input;

public class Battle implements Stage{
	private static int trun;
	public static int getTrun() {
		return trun;
	}
	public static void setTrun(int trun) {
		Battle.trun = trun;
	}
	@Override
	public void init(Adventurer hero,Monster monster) {
		battleAttack(hero,monster);
	}
	public void battleAttack(Adventurer hero,Monster monster) {
		System.out.printf("몬스터와 조우했습니다. %n%s와 전투가 시작됩니다.%n",monster.getName());
		
		while(true) {
			if(hero.getDebuff() > 0) {
				monster.debuffCnt(hero);
			}
			if(trun == 1) {
				int sel = Input.getValue("(1.공격하기) (2.방어하기)\n(3.포션사용) (4.스킬사용)",1,4);
				if (sel == 1) {
					hero.Attack(monster);
				} else if (sel == 2) {
					hero.Defense(true);
				} else if (sel == 3) {
					hero.potionHealing();
				} else if(sel == 4) {
					if(hero.skillBook()) {
						int idx = Input.getValue("공격할 마법 선택 : ",1,hero.skillListSize())-1;
						hero.skillUse(monster, idx);
					}else {
						continue;
					}
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
			// 오크킹 추카타 공격
			if(monster instanceof OrksKing && ((OrksKing) monster).getCnt() > 0) {
				((OrksKing) monster).setCnt(((OrksKing) monster).getCnt()-1);
			}else {
				trun = trun == 1? 2 : 1;
			}
			if(monster.getHp() <= 0) {
				if(monster instanceof OrksKing) {
					System.out.println("던전의 보스를 처치했습니다!");
					System.out.println("수고하셨습니다!");
					GameManager.setStageName("");
					return;
				}
				hero.setDebuff(0);
				System.out.println("몬스터에게 승리했습니다!");
				GameManager.setStageName("MOVE");
				int rd = Input.getRandomMenu(5);
				if(rd == 1) {
					rd = Input.getRandomMenu(2)+1;
					System.out.println("몬스터가 포션을 드랍했습니다.");
					hero.setPotion(hero.getPotion() + rd);
					System.out.printf("%d 개 획득했습니다.%n",rd);
				}
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
