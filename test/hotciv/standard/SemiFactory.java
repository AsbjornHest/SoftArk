package hotciv.standard;

import hotciv.framework.HotCivFactory;
import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.DieStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;
import hotciv.variants.BetaAgeStrategy;
import hotciv.variants.DeltaWorldLayoutStrategy;
import hotciv.variants.EpsilonAttackStrategy;
import hotciv.variants.EpsilonWinnerStrategy;
import hotciv.variants.GammaUnitActionStrategy;

public class SemiFactory implements HotCivFactory {

private DieStrategy d1, d2;
	
	public SemiFactory(DieStrategy d1, DieStrategy d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
	
	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new EpsilonWinnerStrategy();
	}

	@Override
	public AgeStrategy createAgeStrategy(int age) {
		return new BetaAgeStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new DeltaWorldLayoutStrategy();
	}

	@Override
	public UnitActionStrategy createUnitActionStrategy() {
		return new GammaUnitActionStrategy();
	}

	@Override
	public AttackStrategy createAttackStrategy() {
		return new EpsilonAttackStrategy(d1, d2);
	}

}
