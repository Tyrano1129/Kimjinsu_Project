package stage;

import java.util.*;

import GameUnit.Adventurer;
import GameUnit.Monster;
import GameUnit.UnitManager;
import game.GameManager;
import game.Input;
import item.Item;

public class MyInven implements Stage {
	private static List<Item> myinven = new ArrayList<>();
	
	public List<Item> getMyinven() {
		return myinven;
	}
	public static void setMyinven(Item inven) {
		MyInven.myinven.add(inven);
	}
	public void print() {
		int num = 1;
		for(Item i : myinven) {
			System.out.printf("[%d] %s%n",num++,i);
		}
	}
	
	public void setTing() {
		Adventurer hero = UnitManager.getHero();
		if(myinven.size() == 0) {
			System.out.println("인벤토리에 아무것도 없습니다.");
			GameManager.setStageName("MOVE");
			return;
		}
		while(true) {
			System.out.println("[1] 장비 착용");
			System.out.println("[2] 장비 해제");
			System.out.println("[0] 나가기");
			int sel = Input.getValue("메뉴 선택 : ", 0, 2);
			if (sel == 1) {
				print();
				int idx = Input.getValue("장비 선택 : ", 1, myinven.size()) - 1;
				hero.setTing(myinven.get(idx));
			} else if (sel == 2) {
				System.out.println("[1] 무기");
				System.out.println("[2] 방어구");
				System.out.println("[3] 반지");
				sel = Input.getValue("번호 선택 : ", 1, 3);
				if (sel == 1) {
					if (hero.getWeapon() == null) {
						System.out.println("무기는 착용하지않았습니다.");
						continue;
					}
					hero.unequip(sel);
				} else if (sel == 2) {
					if (hero.getRing() == null) {
						System.out.println("방어구는 착용하지않았습니다.");
						continue;
					}
					hero.unequip(sel);
				} else if (sel == 3) {
					if (hero.getArmro() == null) {
						System.out.println("반지는 착용하지않았습니다.");
						continue;
					}
					hero.unequip(sel);
				}
			} else if(sel == 0) {
				GameManager.setStageName("MOVE");
				return;
			}
		}
	}

	@Override
	public void init(Adventurer hero, Monster monster) {
		
	}

	@Override
	public void init() { 
		setTing();
	}
}
