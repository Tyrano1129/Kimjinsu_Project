package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import GameUnit.Adventurer;
import item.Armor;
import item.Item;
import item.Ring;
import item.ShopDAO;
import item.Weapon;
import stage.MyInven;

public class Input{
	private static int[][] loc = {{1,0},{-1,0},{0,1},{0,-1}}; 
	private final static Scanner scan = new Scanner(System.in);
	private static Random rd = new Random();
	private static final String menu = "%s\t%s\t%s%n%s\t%s\t%s".formatted("(q)","(↑w)","(e)", "(←a)", "(↓s)", "(→d)");
	private static final int  monsterMinPower = 3;
	private static int mapCnt = 1;
	public static int getValue(String msg, int start, int end) {
		int num = 0;
		System.out.println(msg);
		while(true) {
			try {
				num = scan.nextInt();
				if(num < start || num > end) {
					System.out.printf("[%d ~ %d] 사이로 입력해주세요.%n",start,end);
				}
				return num;
			}catch(Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}finally {
				scan.nextLine();
			}
		}
	}
	// 몬스터 위치에 아무것도없을떄
	public static boolean checkidx(int tempY,int tempX,int[][] map) {
		for(int i = 0; i < loc.length; i+=1) {
			int x = tempX;
			int y = tempY;
			x += loc[i][0];
			y += loc[i][1];
			if(x >= map.length || y < 0 || x < 0 || y >= map[0].length) {
				return false;
			}
			if(map[y][x] == 9 || 
				map[y][x] == 1 
				||map[y][x] == 4 
				||map [y][x] == 2
				||map [y][x] == 22
				||map [y][x] == 11) {
					return false;
			}
		}
		return true;
	}
	public static void init(ShopDAO shop) {
	 List<Item> itemList = new LinkedList<>(Arrays.asList(
				new Ring(3,0,0,"철반지",500)
				,new Ring(0,2,10,"루비반지",800)
				,new Ring(1,1,5,"토파즈반지",900)
				,new Weapon(3,0,0,"철검",700)
			 	,new Weapon(6,0,3,"예리한검",900)
			 	,new Weapon(10,3,2,"강력한검",1800)
			 	,new Armor(0,3,0,"나무갑옷",500)
			 	,new Armor(0,6,5,"철값옷",1000)
			 	,new Armor(2,5,2,"가시값옷",1200)));
	 	shop.setItemList(itemList);
	}
	public static String getMenu() {
		return menu;
	}
	public static int getRandomMonster(int val) {
		return rd.nextInt(val) + monsterMinPower;
	}
	public static int getRandomMenu(int val) {
		return rd.nextInt(val);
	}
	public static int getRandomHero(int val) {
		return rd.nextInt(val) + (val / 2);
	}
	public static String getValueString(String msg) {
		System.out.println(msg);
		return scan.nextLine();
	}
	public static int[][] getLoc() {
		return loc;
	}
	public static void setLoc(int[][] loc) {
		Input.loc = loc;
	}
	public static int getMapCnt() {
		return mapCnt;
	}
	public static void setMapCnt(int mapCnt) {
		Input.mapCnt = mapCnt;
	}

}
