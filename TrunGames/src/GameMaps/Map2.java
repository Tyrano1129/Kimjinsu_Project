package GameMaps;

import game.Input;

public class Map2 implements MapManager{
	private static int[][] map = {
			{9,9,9,9,9,9,9,9},
			{9,0,0,4,0,4,0,9},
			{9,0,9,0,0,0,0,9},
			{9,0,0,0,9,0,0,9},
			{11,1,0,4,0,9,0,33},
			{11,0,9,0,0,0,0,33},
			{9,9,9,9,9,9,9,9}
	};

	// 몬스터 있는지 없는지 체크
	private static boolean checkMonster() {
		for(int[] m : map) {
			for(int M : m) {
				if(M == 4) {
					return true;
				}
			}
		}
		return false;
	}
	//맵 몬스터가 없을때 다시생성
	public static void resetMap() {
		if(checkMonster()) {
			return;
		}
		int num = Input.getRandomMenu(2)+1;
		for(int i = 0; i < num;i+=1) {
			int rdy = Input.getRandomMenu(map.length);
			int rdx = Input.getRandomMenu(map[rdy].length);
			if(Input.checkidx(rdy,rdx,map)) {
				map[rdy][rdx] = 4;
			}else {
				i-=1;
			}
		}
		setMap(map);
	}

	public static int[][] getMap() {
		return map;
	}

	public static void setMap(int[][] map) {
		Map2.map = map;
	}
	
}
