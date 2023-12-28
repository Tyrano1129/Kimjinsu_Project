package stage;

import java.util.*;
import GameMaps.*;
import GameUnit.*;
import game.*;
import GameMaps.*;

public class Move implements Stage{
	private int x;
	private int y;
	//맵 위치 값
	@Override
	public void init() {
		run();
	}
	//주인공 위치값
	private void init(int[][] map) {
		for (int i = 0; i < map.length; i += 1) {
			for (int k = 0; k < map[i].length; k += 1) {
				if (map[i][k] == 1) {
					x = k;
					y = i;
				}
			}
		}
	}
	//맵 프린트
	private void mapPrint(int[][] map) {
		for (int i = 0; i < map.length; i += 1) {
			for (int k = 0; k < map[i].length; k += 1) {
				if (map[i][k] == 9) {
					System.out.printf("%2s", "■");
				}else if (map[i][k] == 1) {
					System.out.printf("%2s", "q");
				} else if (map[i][k] == 2) {
					System.out.printf("%2s", "p");
				} else if (map[i][k] == 4) {
					System.out.printf("%2s", "o");
				} else {
					System.out.printf("%2s", " ");
				}
			}
			System.out.println();
		}
		System.out.println("========================================");
	}
	//움직임
	private void gamePlay(int[][] map) {
		while(true) {
			int px = x;
			int py = y;
			mapPrint(map);
			System.out.println(UnitManager.getHero());
			String sel = Input.getValueString(Input.getMenu());
			if(sel.equals("a")) {
				px -=1;
			}else if(sel.equals("s")) {
				py +=1;
			}else if(sel.equals("d")) {
				px +=1;
			}else if(sel.equals("w")) {
				py -=1;
			}else if(sel.equals("q")) {
				GameManager.setStageName("");
				return;
			}else if(sel.equals("e")) {
				GameManager.setStageName("INVENTORI");
				return;
			}
			
			try {
				if(map[py][px] == 4) {
					GameManager.setStageName("BATTLE");
					map[y][x] = 0;
					y = py;
					x = px;
					map[y][x] = 1;
					return;
				}
				if(map[py][px] == 9) {
					continue;
				}
				if(map[py][px] == 2) {
					GameManager.setStageName("SHOPS");
					return;
				}
				if(Maps(map[py][px])) {
					return;
				}
				map[y][x] = 0;
				y = py;
				x = px;
				map[y][x] = 1;
			}catch(Exception e) {
				System.out.println("더이상 이동 불가능합니다.");
			}
		}
	}

	public void run() {
		if(Input.getMapCnt() == 1) {
			init(Map1.getMap());
			gamePlay(Map1.getMap());
		}else if(Input.getMapCnt() == 2) {
			init(Map2.getMap());
			gamePlay(Map2.getMap());
		}
	}
	public boolean Maps(int move) {
		if(move == 11) {
			Map2.resetMap();
			Input.setMapCnt(1);
			return true;
		}
		if(move == 22) {
			Map1.resetMap();
			Input.setMapCnt(2);
			return true;
		}
		return false;
	}
	@Override
	public void init(Adventurer hero, Monster monster) {
		return;
	}


}
