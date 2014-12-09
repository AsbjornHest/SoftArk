package hotciv.interfacevariants;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public interface WinnerStrategy {
	public Player getWinner(GameImpl game);
}

