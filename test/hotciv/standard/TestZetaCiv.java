package hotciv.standard;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.ZetaFactory;

public class TestZetaCiv {
	
	private Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl( new ZetaFactory() );
	}
	
	public void numberOfRounds(int turns) {
		  for(int i = 0; i<turns*2; i++){		  
			  game.endOfTurn();
		  }  }
	
	@Test
    public void moveRedArcherAt2_0ToBlueCityAt4_1AndShouldConquerCity() {
        assertEquals("There should be a blue city at (4,1)", Player.BLUE, game.getCityAt(new Position(4,1)).getOwner());
        assertEquals("There should be a red archer at (2,0)", GameConstants.ARCHER, game.getUnitAt(new Position(2,0)).getTypeString());
        game.moveUnit(new Position(2,0), new Position(4,1));
        assertEquals("There should be a archer at (4,1)", GameConstants.ARCHER, game.getUnitAt(new Position(4,1)).getTypeString());
        assertEquals("There should be a blue city at (4,1)", Player.BLUE, game.getCityAt(new Position(4,1)).getOwner());

        
	}
	
}