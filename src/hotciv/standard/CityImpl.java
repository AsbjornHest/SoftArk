package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {

	private Player owner;
	private String production;
	private String focus = GameConstants.ARCHER;
	private int worth;
	
	public CityImpl(Player owner) {
		this.owner = owner;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String getProduction() {
		return production;
	}

	public void setProduction(String unitType) {
		production = unitType;
	}
	
	public void setOwner(Player newOwner){
		owner = newOwner;
	}
	
	@Override
	public String getWorkforceFocus() {
		return focus;
	}
	
	public int getWorth() {
		return worth;
		
	}
	
	public void increaseWorth(int amount) {
		worth += amount;
	}

	public void decreaseWorth(int amount) {
		worth -= amount;
	}
	
}
