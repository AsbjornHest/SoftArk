package hotciv.variants;

import hotciv.framework.*;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.standard.GameImpl;

public class BetaWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(GameImpl game) {
		Player assumedWinner = null;
		for(City c : game.getCities().values()){
			if(assumedWinner == null){
				assumedWinner = c.getOwner();
			} else {
				if(!c.getOwner().equals(assumedWinner)){
					return null;
				}
			}
		}
		return assumedWinner;
	}
}