package hotciv.variants;

import hotciv.framework.Player;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.standard.GameImpl;

public class ZetaWinnerStrategy implements WinnerStrategy {
	
	private BetaWinnerStrategy betaWinnerStrategy = new BetaWinnerStrategy();
	private EpsilonWinnerStrategy epsilonWinnerStrategy = new EpsilonWinnerStrategy();
	
	private int currentRound = 0;

	@Override
	public Player getWinner(GameImpl game) {
		if(currentRound < 21)
		{
			return betaWinnerStrategy.getWinner(game);
		}
		return epsilonWinnerStrategy.getWinner(game);
	}

}
