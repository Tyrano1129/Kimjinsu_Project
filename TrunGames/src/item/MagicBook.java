package item;

import java.util.ArrayList;
import java.util.Arrays;

public class MagicBook {
	private static ArrayList<MagicBook> instance = new ArrayList<MagicBook>(Arrays.asList(
									new MagicBook("파이어볼",10,15,0,1000),
									new MagicBook("얼음창", 15,20,0,1200),
									new MagicBook("힐링",10,0,30,800)));
	private String name;
	private int mana;
	private int att;
	private int heailing;
	private int price;
	public MagicBook(String name, int mana, int att, int heailing,int price) {
		super();
		this.name = name;
		this.mana = mana;
		this.att = att;
		this.heailing = heailing;
		this.price = price;
	}
	public static ArrayList<MagicBook> getInstance() {
		return instance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getHeailing() {
		return heailing;
	}
	public void setHeailing(int heailing) {
		this.heailing = heailing;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		String data ="";
		if (this.heailing != 0) {
			data += "[책 이름 : %s ] [마나 : %d] [힐링 : %d]%n".formatted(this.name, this.mana, this.heailing);
		} else {
			data += "[책 이름 : %s ] [마나 : %d] [파워 : %d]%n".formatted(this.name, this.mana, this.att);
		}
		data += "[가격 : %d]".formatted(this.price);
		return data;
	}
	
	
}
