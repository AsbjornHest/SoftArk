package hotciv.variants;

import hotciv.framework.Position;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public class GammaUnitActionStrategy implements UnitActionStrategy {


	@Override
	public void performSettlerActionAt(GameImpl g, Position p) {
		CityImpl newcity = new CityImpl(g.getUnitAt(p).getOwner());
		g.setCityAt(p, newcity);
		g.removeUnitAt(p);
		
	}

	@Override
	public void performArcherActionAt(GameImpl g, Position p) {
		UnitImpl archer = (UnitImpl) g.getUnitAt(p);
		g.setUnitAt(p, archer);
		if(archer.getDefensiveStrength() == 3) {
		archer.setDefensiveStrength(2*archer.getDefensiveStrength());
		}
		else archer.setDefensiveStrength(3);
		}
		
	}





