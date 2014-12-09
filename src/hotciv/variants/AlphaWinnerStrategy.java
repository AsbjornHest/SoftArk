package hotciv.variants;

import hotciv.framework.Player;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.standard.GameImpl;

public class AlphaWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(GameImpl game) {
		if( game.getAge() == -3000) { // Hvis alder er -3000 returneres r�d spiller som vinder
		return Player.RED;
	}
	return null;
	}
}
