package item;

import java.util.*;
import java.util.stream.Collectors;

import GameUnit.Adventurer;
import stage.*;

public class ShopDAO {
	private static ShopDAO instance = new ShopDAO();
	private List<Item> itemList;
	
	public static ShopDAO getInstance() {
		return instance;
	}
	public static void setInstance(ShopDAO instance) {
		ShopDAO.instance = instance;
	}
	public int itemListSize() {
		return itemList.size();
	}
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
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
}
