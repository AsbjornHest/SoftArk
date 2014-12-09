package hotciv.framework;

import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;

public interface HotCivFactory {
	
	public WinnerStrategy createWinnerStrategy();
	
	public AgeStrategy createAgeStrategy(int age);
	
	public WorldLayoutStrategy createWorldLayoutStrategy();
	
	public UnitActionStrategy createUnitActionStrategy();
	
	public AttackStrategy createAttackStrategy();

}
