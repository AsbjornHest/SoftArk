package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.GammaFactory;

import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {

	private Game game;
	/** Fixture for gammaciv testing. */
	@Before
	public void setUp() {

		game = new GameImpl( new GammaFactory() );
	}

	public void numberOfRounds(int turns) {
		for(int i = 0; i<turns*2; i++){		  
			game.endOfTurn();
		}
	}
		
	@Test
	public void settlerShouldVanishFromMapWhenUnitActionIsPerformed() { 
		assertEquals(GameConstants.SETTLER, game.getUnitAt( new Position (10,0)).getTypeString() ); // There is a settler here before the unit action is performed
		game.performUnitActionAt( new Position (10,0) );
		assertNull(game.getUnitAt( new Position (10,0) )); // The settler is now removed from the hashmap
	}
	
	@Test
	public void newlyProducedCitySouldHaveSameOwnerAsBuildingSettler(){
		assertNull(game.getCityAt(new Position(10,0)) );	// There is no city at position 10,0
		Player player = game.getUnitAt(new Position(10,0)).getOwner();
		game.performUnitActionAt(new Position(10,0) );
		assertEquals(player, game.getCityAt(new Position(10,0)).getOwner()); // City is produced at 10,0
	}

	@Test
	public void archersFortifyValueShouldDoubleWhenUnitActionIsPerformed() { 
		Position p = new Position(3,8);
		assertEquals(3, game.getUnitAt(p).getDefensiveStrength() ); // The archers defensive strength be normal = 3
		game.performUnitActionAt(p);
		assertEquals(6, game.getUnitAt(p).getDefensiveStrength() ); // The archers defensive strength should have doubled now
		game.performUnitActionAt(p);
		assertEquals(3, game.getUnitAt(p).getDefensiveStrength() ); // The archers defensive strength should be 3 again

	}
	
}
