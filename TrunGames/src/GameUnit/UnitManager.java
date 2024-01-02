package GameUnit;

import java.util.*;
import java.util.Arrays;

import GameMaps.*;
import game.Input;
// 맵마다 유닛 생성 랜덤으로
public class UnitManager {
	private static Adventurer Hero = new Adventurer(50,13,3,"히어로");
	private static final String[] mons = {"Orks","OrksWarrior","Goblin","OrksKing"}; 
	private static final String path = UnitManager.class.getPackageName()+".";
	
	public static Monster monster_rand_set(MapManager map) {
		Monster temp = null;
		try {
			while(true) {
				int num = Input.getRandomMenu(mons.length);
				Class<?> clazz = Class.forName(path + mons[num]);
				Object obj = clazz.getDeclaredConstructor().newInstance();
				if(map instanceof Map1) {
					if(obj instanceof OrksKing || obj instanceof OrksWarrior) {
						continue;
					}
				}else if(map instanceof Map2){
					if(obj instanceof OrksKing) {
						continue;
					}
				}else {
					clazz = Class.forName(path + "OrksKing");
					obj = clazz.getDeclaredConstructor().newInstance();
				}
				temp = (Monster) obj;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	public static Adventurer getHero() {
		return Hero;
	}
	public static void setHero(Adventurer hero) {
		Hero = hero;
	}
	
	
}
