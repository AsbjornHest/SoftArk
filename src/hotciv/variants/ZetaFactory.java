package hotciv.variants;

import hotciv.framework.HotCivFactory;
import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;

public class ZetaFactory implements HotCivFactory {

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new ZetaWinnerStrategy();
	}

	@Override
	public AgeStrategy createAgeStrategy(int age) {
		return new AlphaAgeStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new AlphaWorldLayoutStrategy();
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
