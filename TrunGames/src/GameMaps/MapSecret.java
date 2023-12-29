package GameMaps;

import game.Input;

public class MapSecret implements MapManager{
	private static int[][] map = {
			{9,9,9,9,9},
			{9,0,0,0,9},
			{9,0,3,0,9},
			{9,0,0,0,9},
			{9,0,1,0,9},
			{9,22,22,22,9}
	};
	public static int[][] getMap() {
		return map;
	}

	public static void setMap(int[][] map) {
		MapSecret.map = map;
	}
	
}
