package stage;

import java.util.*;
import GameMaps.*;
import GameUnit.*;
import game.*;
import item.BonusWeapon;
import GameMaps.*;

public class Move implements Stage{
	private int x;
	private int y;
	private int[][] monsterDir;
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
					System.out.printf(Input.purple + "%2s" + Input.exit, "q");
				} else if (map[i][k] == 2) {
					System.out.printf(Input.yellow + "%2s" + Input.exit, "p");
				} else if (map[i][k] == 4) {
					System.out.printf(Input.red + "%2s" + Input.exit, "o");
				} else if(map[i][k] == 99) {
					System.out.printf(Input.cyan + "%2s" + Input.exit, "■");
				} else if(map[i][k] == 3) {
					System.out.printf(Input.white + "%2s" + Input.exit, "⌂");
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
		int trun = 1;
		if(Input.mapMonsterDir(map) != null) {
			monsterDir = Input.mapMonsterDir(map);		
		}else {
			monsterDir = null;
		}
		while(true) {
			if(trun == 1) {
				int px = x;
				int py = y;
				mapPrint(map);
				System.out.println(UnitManager.getHero());
				String sel = Input.getValueString(Input.getMenu());
				if (sel.equals("a")) {
					px -= 1;
				} else if (sel.equals("s")) {
					py += 1;
				} else if (sel.equals("d")) {
					px += 1;
				} else if (sel.equals("w")) {
					py -= 1;
				} else if (sel.equals("q")) {
					GameManager.setStageName("");
					return;
				} else if (sel.equals("e")) {
					GameManager.setStageName("INVENTORI");
					return;
				}

				try {
					if (map[py][px] == 4) {
						GameManager.setStageName("BATTLE");
						map[y][x] = 0;
						y = py;
						x = px;
						map[y][x] = 1;
						Battle.setTrun(1);
						return;
					}
					if (map[py][px] == 9) {
						continue;
					}
					if (map[py][px] == 3) {
						MyInven.setMyinven(new BonusWeapon());
						System.out.println(Input.green + "전설의 검을 획득하셨습니다!" + Input.exit);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (map[py][px] == 2) {
						GameManager.setStageName("SHOPS");
						return;
					}
					if (Maps(map[py][px])) {
						return;
					}
					map[y][x] = 0;
					y = py;
					x = px;
					map[y][x] = 1;
				} catch (Exception e) {
					System.out.println("더이상 이동 불가능합니다.");
				}
			}else if(trun ==2) {
				int[][] loc = Input.getLoc();
				for(int i = 0; i < monsterDir.length; i+=1) {
					int rd = Input.getRandomMenu(loc.length);
					int xx = monsterDir[i][1];
					int yy = monsterDir[i][0];
					xx += loc[rd][0];
					yy += loc[rd][1];
					if(xx < 0 || xx > map[0].length || yy < 0 || yy > map.length) {
						i-=1;
						continue;
					}
					if(map[yy][xx] == 9 
							||map[yy][xx] == 4 
							||map [yy][xx] == 2
							||map [yy][xx] == 22
							||map [yy][xx] == 11
							||map [yy][xx] == 99) {
						i-=1;
						continue;
					}
					mapPrint(map);
					if (map[yy][xx] == 1) {
						GameManager.setStageName("BATTLE");
						System.out.println("몬스터가 기습공격하였습니다.");
						Battle.setTrun(2);
						map[monsterDir[i][0]][monsterDir[i][1]] = 0;
						return;
					}
					map[monsterDir[i][0]][monsterDir[i][1]] = 0;
					monsterDir[i][0] = yy;
					monsterDir[i][1] = xx;
					map[monsterDir[i][0]][monsterDir[i][1]] = 4;
				}
			}
			if(monsterDir != null) {
				trun = trun == 1? 2 : 1;
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
		}else if(Input.getMapCnt() == 3) {
			init(Map3.getMap());
			gamePlay(Map3.getMap());
		}else if(Input.getMapCnt() == 99) {
			init(MapSecret.getMap());
			gamePlay(MapSecret.getMap());
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
		if(move == 33) {
			Map2.resetMap();
			Input.setMapCnt(3);
			return true;
		}
		if(move == 99) {
			Map2.resetMap();
			Input.setMapCnt(99);
			return true;
		}
		return false;
	}
	@Override
	public void init(Adventurer hero, Monster monster) {
		return;
	}


}
