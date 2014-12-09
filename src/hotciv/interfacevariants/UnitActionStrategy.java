package hotciv.interfacevariants;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface UnitActionStrategy {
	
	public void performSettlerActionAt(GameImpl g, Position p);
	
	public void performArcherActionAt(GameImpl g, Position p);
}
