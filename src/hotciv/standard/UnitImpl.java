package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

	private Player owner;
	private String unitType;
	private int moveCount;
	private int defensiveStrength;
	private int attackingStrength;
	
	public UnitImpl(String unitType, Player owner) {
		this.owner = owner;
		this.unitType = unitType;
		if(unitType.equals(GameConstants.ARCHER)){ defensiveStrength = 3; attackingStrength = 2; }
		if(unitType.equals(GameConstants.LEGION)){ defensiveStrength = 2; attackingStrength = 4;}
		if(unitType.equals(GameConstants.SETTLER)){ defensiveStrength = 3; attackingStrength = 0;}
	}
	
	@Override
	public String getTypeString() {
		return unitType;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getMoveCount() {
		return moveCount;
	}

	@Override
	public int getDefensiveStrength() {		
		return defensiveStrength; 
	}
	
	public void setDefensiveStrength(int value) {
		defensiveStrength = value;
	}

	@Override
	public int getAttackingStrength() {
		return attackingStrength;
	}

}
