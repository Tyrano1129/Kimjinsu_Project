package stage;

import GameMaps.*;
import GameUnit.*;
import game.*;
import item.BonusWeapon;

public class Move implements Stage{
	private int x;
	private int y;
	private int[][] monsterDir;
	private int[][] map;
	//맵 위치 값
	@Override
	public void init() {
		run();
	}
	//플레이어 위치
	private void playerDir() {
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
	private void mapPrint() {
		for (int i = 0; i < map.length; i += 1) {
			for (int k = 0; k < map[i].length; k += 1) {
				if (map[i][k] == 9) {
					System.out.printf("%2s", "■");
				} else if (map[i][k] == 1) {
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
	// 플레이어 움직임
	private boolean plyerMove() {
		int px = x;
		int py = y;
		mapPrint();
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
			return false;
		} else if (sel.equals("e")) {
			GameManager.setStageName("INVENTORI");
			return false;
		}
		try {
			if (map[py][px] == 4) {
				GameManager.setStageName("BATTLE");
				map[y][x] = 0;
				y = py;
				x = px;
				map[y][x] = 1;
				Battle.setTrun(1);
				return false;
			}
			if (map[py][px] == 9) {
				return true;
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
				return false;
			}
			if (Maps(map[py][px])) {
				return false;
			}
			//움직일때마다 마나 2씩채우기
			UnitManager.getHero().setMana(UnitManager.getHero().getMana() + 2);
			if (UnitManager.getHero().getMana() > UnitManager.getHero().getManaMax()) {
				UnitManager.getHero().setMana(UnitManager.getHero().getManaMax());
			}
			map[y][x] = 0;
			y = py;
			x = px;
			map[y][x] = 1;
		} catch (Exception e) {
			System.out.println("더이상 이동 불가능합니다.");
		}
		return true;
	}
	// 몬스터 움직임
	private boolean monsterMove() {
		int[][] loc = Input.getLoc();
		for (int i = 0; i < monsterDir.length; i += 1) {
			int rd = Input.getRandomMenu(loc.length);
			int xx = monsterDir[i][1];
			int yy = monsterDir[i][0];
			xx += loc[rd][0];
			yy += loc[rd][1];
			if (xx < 0 || xx > map[0].length || yy < 0 || yy > map.length) {
				i -= 1;
				continue;
			}
			if (map[yy][xx] == 9 || map[yy][xx] == 4 || map[yy][xx] == 2 || map[yy][xx] == 22 || map[yy][xx] == 11
					|| map[yy][xx] == 99) {
				i -= 1;
				continue;
			}
			mapPrint();
			if (map[yy][xx] == 1) {
				GameManager.setStageName("BATTLE");
				System.out.println("몬스터가 기습공격하였습니다.");
				Battle.setTrun(2);
				map[monsterDir[i][0]][monsterDir[i][1]] = 0;
				return false;
			}
			map[monsterDir[i][0]][monsterDir[i][1]] = 0;
			monsterDir[i][0] = yy;
			monsterDir[i][1] = xx;
			map[monsterDir[i][0]][monsterDir[i][1]] = 4;
		}
		return true;
	}
	// 몬스터와 플레이어 움직임 1턴씩
	private void gamePlay() {
		int trun = 1;
		if(Input.mapMonsterDir(map) != null) {
			monsterDir = Input.mapMonsterDir(map);		
		}else {
			monsterDir = null;
		}
		while(true) {
			if(trun == 1) {
				if(!plyerMove()) {
					return;
				}
			}else if(trun ==2) {
				if(!monsterMove()) {
					return;
				}
			}
			if(monsterDir != null) {
				trun = trun == 1? 2 : 1;
			}
		}
	}
	// 맵
	public void run() {
		if(Input.getMapCnt() == 1) {
			map = Map1.getMap();
			playerDir();
			gamePlay();
		}else if(Input.getMapCnt() == 2) {
			map = Map2.getMap();
			playerDir();
			gamePlay();
		}else if(Input.getMapCnt() == 3) {
			map = Map3.getMap();
			playerDir();
			gamePlay();
		}else if(Input.getMapCnt() == 99) {
			map = MapSecret.getMap();
			playerDir();
			gamePlay();
		}
	}
	// 맵이동
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
