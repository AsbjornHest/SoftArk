package hotciv.variants;

import hotciv.framework.HotCivFactory;
import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;

public class BetaFactory implements HotCivFactory {

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new BetaWinnerStrategy();
	}

	@Override
	public AgeStrategy createAgeStrategy(int age) {
		return new BetaAgeStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new BetaWorldLayoutStrategy();
	}

	@Override
	public UnitActionStrategy createUnitActionStrategy() {
		return new AlphaUnitActionStrategy();
	}

	@Override
	public AttackStrategy createAttackStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

}
