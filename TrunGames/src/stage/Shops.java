package stage;

import java.util.List;

import GameUnit.Adventurer;
import GameUnit.Monster;
import GameUnit.UnitManager;
import game.GameManager;
import game.Input;
import item.Item;
import item.ShopDAO;

public class Shops implements Stage {
	
	public Shops(){
	}
	@Override
	public void init(Adventurer hero, Monster monster) {
	}

	@Override
	public void init() {
		ShopDAO shop = ShopDAO.getInstance();
		Adventurer hero = UnitManager.getHero();
		System.out.println("상점에 오신걸 환영합니다.");
		while(true) {
			System.out.println("어떤 장비를 구입하겠습니끼?");
			System.out.println("[1] 무기");
			System.out.println("[2] 방어구");
			System.out.println("[3] 반지");
			System.out.println("[0] 나가기");
			
			int sel = Input.getValue(" 선택 : ",0,3);
			System.out.printf("[현재 골드 : %d]%n",hero.getGold());
			if(sel == 1) {
				List<Item> temp = shop.pringItemList(1);
				int num = Input.getValue("번호 입력 :",1,shop.itemListSize())-1;
				shop.prchase(temp, num,hero);
				System.out.println("무기 구입하였습니다.");
			}else if(sel == 2) {
				List<Item> temp = shop.pringItemList(2);
				int num = Input.getValue("번호 입력 :",1,shop.itemListSize())-1;
				shop.prchase(temp, num,hero);
				System.out.println("방어구 구입하였습니다.");
			}else if(sel == 3) {
				List<Item> temp = shop.pringItemList(3);
				int num = Input.getValue("번호 입력 :",1,shop.itemListSize())-1;
				shop.prchase(temp, num,hero);
				System.out.println("반지 구입하였습니다.");
			}else if(sel == 0) {
				System.out.println("다음에 또만나요!");
				GameManager.setStageName("MOVE");
				return;
			}
		}
	}

}
