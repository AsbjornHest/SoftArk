package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.DeltaFactory;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for deltaCiv test cases */
public class TestDeltaCiv {
	private Game game;
	/** Fixture for deltaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new DeltaFactory());
	}

	public void numberOfRounds(int turns) {
		for(int i = 0; i<turns*2; i++){		  
			game.endOfTurn();
		}
	}
	
	@Test
	public void shouldHaveForestAt8_13(){
		assertEquals(GameConstants.FOREST, game.getTileAt(new Position(8,13)).getTypeString());
	}
	
	@Test
	public void shouldHaveOceanAt0_0(){
		assertEquals(GameConstants.OCEANS, game.getTileAt(new Position(0,0)).getTypeString());
	}
	
	@Test
	public void shouldNotHaveForestAt0_0BecauseOceanIsAlreadyThere(){
		assertNotSame(GameConstants.FOREST, game.getTileAt(new Position(0,0)).getTypeString()); // We are expecting "ocean" so this assert should go straight through 
	}
	
	@Test
	public void shouldHaveForestAt11_4(){
		assertEquals(GameConstants.MOUNTAINS, game.getTileAt(new Position(11,4)).getTypeString());
	}
	
	@Test
	public void shouldHaveForestAt7_10(){
		assertEquals(GameConstants.HILLS, game.getTileAt(new Position(7,10)).getTypeString());
	}
	

	@Test
	public void bluePlayerShouldHaveACityAt4_5(){
		assertEquals(Player.BLUE, game.getCityAt(new Position(4,5)).getOwner()); // There should be a city at 8_11 that is owned by blue player
	}
	
	@Test
	public void redPlayerShouldHaveACityAt8_11(){
		assertEquals(Player.RED, game.getCityAt(new Position(8,11)).getOwner()); // There should be a city at 8_11 that is owned by red player
	}
	
	@Test
	public void shouldBeNoCityAt15_12(){
		assertNull(game.getCityAt(new Position(15,12))); //There should be no city at 15_12
	}


	@Test
	public void redPlayerShouldHaveArcherAt3_8(){
		assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(3,8)).getTypeString()); // There should be an archer at 3_8
		assertEquals(Player.RED, game.getUnitAt(new Position(3,8)).getOwner()); // There should be an archer at 3_8 that is owned by red player
	}
	
	@Test
	public void bluePlayerShouldHaveLegionAt4_4(){
		assertEquals(GameConstants.LEGION, game.getUnitAt(new Position(4,4)).getTypeString()); // There should be a legion at 4_4
		assertEquals(Player.BLUE, game.getUnitAt(new Position(4,4)).getOwner()); // The unit at 4_4 is owned by blue player
	}
	
	@Test
	public void redPlayerShouldHaveSettlerAt5_5(){
		assertEquals(GameConstants.SETTLER, game.getUnitAt(new Position(5,5)).getTypeString()); // There should be a settler at 5_5
		assertEquals(Player.RED, game.getUnitAt(new Position(5,5)).getOwner()); // The unit at 5_5 is owned by red player
	}
	
	@Test
	public void bluePlayerShouldNotHaveALegionAt5_5(){
		assertNotSame(GameConstants.LEGION, game.getUnitAt(new Position(5,5)).getTypeString()); // There should not be a legion at 5_5
		assertNotSame(Player.BLUE, game.getUnitAt(new Position(5,5)).getOwner()); // The unit at 5_5 is NOT owner by blue player
	}
	
	
	
}

