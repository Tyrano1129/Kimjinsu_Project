package item;

import java.util.*;
import java.util.stream.Collectors;

import GameUnit.Adventurer;
import stage.*;

public class ShopDAO {
	private static ShopDAO instance = new ShopDAO();
	private List<Item> itemList;
	private List<MagicBook> magicList;
	
	public static ShopDAO getInstance() {
		return instance;
	}
	public void setMagicList(List<MagicBook> magicList) {
		this.magicList = magicList;
	}
	public int itemListSize() {
		return itemList.size();
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public int magicListSize() {
		return this.magicList.size();
	}
	
	public ShopDAO() {
		itemList = new LinkedList<>();
	}
	//장비 값 가지고오기
	public List<Item> pringItemList(int kind) {
		List<Item> temp = new ArrayList<>();
		temp = itemList.stream()
					.filter(i -> i.getKind() == kind)
					.collect(Collectors.toList());
		int num = 1;
		for(Item i : temp ) {
			System.out.printf("[%d] %s [가격 : %d]%n",num++,i,i.getPrice());
		}
		return temp;
	}
	//구입 메서드
	public void prchase(List<Item> temp,int num,Adventurer hero) {
		if(hero.getGold() < temp.get(num).getPrice()) {
			System.out.println("골드가 부족해요!");
			return;
		}
		hero.setGold(hero.getGold()-temp.get(num).getPrice());
		MyInven.setMyinven(temp.get(num));
	}
	// 매직북 구매
	public void prchaseMagicBook(List<MagicBook> temp,int num,Adventurer hero) {
		if(hero.getGold() < temp.get(num).getPrice()) {
			System.out.println("골드가 부족해요!");
			return;
		}
		hero.setGold(hero.getGold()-temp.get(num).getPrice());
		hero.setSkillList(temp.get(num));
	}
	//매직북 출력 가지고오기
	public List<MagicBook> magicBookPrint() {
		List<MagicBook> temp = new ArrayList<>();
		temp = magicList.stream().collect(Collectors.toList());
		int num = 1;
		for (MagicBook i : temp) {
			System.out.printf("[%d] %s%n", num++, i, i.getPrice());
		}
		return temp;
	}
}
