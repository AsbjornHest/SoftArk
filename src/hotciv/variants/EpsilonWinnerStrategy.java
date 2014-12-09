package hotciv.variants;

import hotciv.framework.Player;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.standard.GameImpl;

public class EpsilonWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(GameImpl game) {
		int redCount = game.gimmeAttackCounts().get(Player.RED);
		int blueCount = game.gimmeAttackCounts().get(Player.BLUE);
		
		if(redCount >= 3){
			return Player.RED;
		} else if(blueCount >= 3){
			return Player.BLUE;
		}
		return null;
	}

}