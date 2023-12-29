package stage;

import GameUnit.Adventurer;
import GameUnit.Monster;
import game.GameManager;
import game.Input;

public class GameTitle implements Stage{


	private void title() {
		String input = Input.getValueString("아무거나 입력해주세요."+Input.green);
		System.out.println("게임 실행...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("이동조작 %n %s%n(q) 는 게임을 종료가 됩니다.%n"
				+ "(e) 장비 착용과 장비 해제를 할수있습니다.%n",Input.getMenu());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("그럼 게임 시작하겠습니다 즐거운 모험 되시기바랍니다." + Input.exit);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GameManager.setStageName("MOVE");
	}
	@Override
	public void init(Adventurer hero, Monster monster) {
	}

	@Override
	public void init() {
		title();
	}
}
