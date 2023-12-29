package game;

import java.util.*;

import GameMaps.Map1;
import GameMaps.Map2;
import GameMaps.Map3;
import GameMaps.MapManager;
import GameUnit.UnitManager;
import item.ShopDAO;
import stage.*;

public class GameManager {
	private static String stageName = "";
	private static Map<String,Stage> stageList = new HashMap<>();
	
	
	//초기값 스테이지
	public GameManager(){
		Input.init(ShopDAO.getInstance());
		stageList.put("MOVE",new Move());
		stageList.put("BATTLE",new Battle());
		stageList.put("INVENTORI",new MyInven());
		stageList.put("GAMETITLE",new GameTitle());
		stageList.put("SHOPS", new Shops());
		stageName = "GAMETITLE";
	}
	
	//스테이지 바뀌는 메서드
	public void changeStage() {
		while(true) {
			System.out.println("현재 스테이지 : " + stageName);

			Stage stage = stageList.get(stageName);
			if (stage instanceof Battle) {
				stage.init(UnitManager.getHero(), UnitManager.monster_rand_set(mapcheck()));
			} else {
				stage.init();
			}

			if (stageName.equals("")) {
				System.out.println("게임종료...");
				return;
			}
		}
	}
	// 맵 옴길때 사용되는 카운트
	public MapManager mapcheck(){
		if(Input.getMapCnt() == 1) {
			return new Map1();
		}else if(Input.getMapCnt() == 2) {
			return new Map2();
		}else if(Input.getMapCnt() == 3){
			return new Map3();
		}
		return null;
	}
	
	public static String getStageName() {
		return stageName;
	}


	public static void setStageName(String stageName) {
		GameManager.stageName = stageName;
	}

}
