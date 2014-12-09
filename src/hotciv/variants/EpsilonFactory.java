package hotciv.variants;

import hotciv.framework.HotCivFactory;
import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.DieStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;

public class EpsilonFactory implements HotCivFactory {
	
	private DieStrategy d1, d2;
	
	public EpsilonFactory(DieStrategy d1, DieStrategy d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new EpsilonWinnerStrategy();
	}

	@Override
	public AgeStrategy createAgeStrategy(int age) {
		return new AlphaAgeStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new DeltaWorldLayoutStrategy();
	}

	@Override
	public UnitActionStrategy createUnitActionStrategy() {
		return new AlphaUnitActionStrategy();
	}

	@Override
	public AttackStrategy createAttackStrategy() {
		return new EpsilonCivAttackStrategy(d1, d2);
	}
	
	
	
	

}
