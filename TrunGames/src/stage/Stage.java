package stage;

import GameMaps.*;
import GameUnit.Adventurer;
import GameUnit.Monster;
import game.*;

public interface Stage {
	void init(Adventurer hero,Monster monster);
	void init();
}
