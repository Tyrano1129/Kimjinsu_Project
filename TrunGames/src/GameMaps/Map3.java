package GameMaps;


public class Map3 implements MapManager{
	private static int[][] map = {
			{9,9,9,9,9,9,9,9},
			{9,9,9,9,9,9,9,9},
			{9,9,9,0,0,0,9,9},
			{9,9,9,0,4,0,9,9},
			{22,1,0,0,0,9,9,9},
			{22,0,0,9,9,9,9,9},
			{9,9,9,9,9,9,9,9}
	};

	public static int[][] getMap() {
		return map;
	}

	public static void setMap(int[][] map) {
		Map3.map = map;
	}
	
}
