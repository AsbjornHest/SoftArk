package hotciv.standard;

import static org.junit.Assert.*;

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
    public void redTakesTheBlueCityAndWins(){
		game.moveUnit(new Position(2,0), new Position(4,1));		
		assertEquals(Player.RED, game.getCityAt(new Position(4,1)).getOwner());
		assertEquals(Player.RED, game.getWinner());
	}
	
	@Test
	public void blueTAakesTheRedCityAndWins(){
		game.moveUnit(new Position(3,2), new Position(1,1));		
//		assertEquals(Player.RED, game.getCityAt(new Position(4,1)).getOwner());
		assertEquals(Player.BLUE, game.getWinner());
	}
	
	@Test
	public void redShouldWinByWinning3CombatsAfter20PlusRoundsHavePassed(){
		((GameImpl) game).setUnitAt(new Position(2,1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		((GameImpl) game).setUnitAt(new Position(2,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		((GameImpl) game).setUnitAt(new Position(2,3), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		numberOfRounds(20);
		game.moveUnit(new Position(2,0), new Position(2,1));
		game.moveUnit(new Position(2,1), new Position(2,2));
		game.moveUnit(new Position(2,2), new Position(2,3));
		assertEquals(Player.RED, game.getWinner());
	}
	
	@Test
	public void blueShouldWinByWinning3CombatsAfter20PlusRoundsHavePassed(){
		((GameImpl) game).setUnitAt(new Position(2,1), new UnitImpl(GameConstants.LEGION, Player.RED));
		((GameImpl) game).setUnitAt(new Position(2,2), new UnitImpl(GameConstants.LEGION, Player.RED));
		((GameImpl) game).setUnitAt(new Position(2,3), new UnitImpl(GameConstants.LEGION, Player.RED));
		numberOfRounds(20);
		game.moveUnit(new Position(3,2), new Position(2,1));
		game.moveUnit(new Position(2,1), new Position(2,2));
		game.moveUnit(new Position(2,2), new Position(2,3));
		assertEquals(Player.BLUE, game.getWinner());
	}
	
	@Test
	public void redWins3CombatsBefore20RoundsHaveElapsedAndDoesNotWin(){
		((GameImpl) game).setUnitAt(new Position(2,1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		((GameImpl) game).setUnitAt(new Position(2,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		((GameImpl) game).setUnitAt(new Position(2,3), new UnitImpl(GameConstants.LEGION, Player.BLUE));
		game.moveUnit(new Position(2,0), new Position(2,1));
		game.moveUnit(new Position(2,1), new Position(2,2));
		game.moveUnit(new Position(2,2), new Position(2,3));
		assertNull(game.getWinner());
	}
	
	@Test
	public void blueWins3CombatsBefore20RoundsHaveElapsedAndDoesNotWin(){
		((GameImpl) game).setUnitAt(new Position(2,1), new UnitImpl(GameConstants.LEGION, Player.RED));
		((GameImpl) game).setUnitAt(new Position(2,2), new UnitImpl(GameConstants.LEGION, Player.RED));
		((GameImpl) game).setUnitAt(new Position(2,3), new UnitImpl(GameConstants.LEGION, Player.RED));
		game.moveUnit(new Position(3,2), new Position(2,1));
		game.moveUnit(new Position(2,1), new Position(2,2));
		game.moveUnit(new Position(2,2), new Position(2,3));
		assertNull(game.getWinner());
	}
	
	@Test
    public void redTakesTheBlueCityAfter20RoundAndDoesNotWin(){
		game.moveUnit(new Position(2,0), new Position(4,1));
		numberOfRounds(20);
		assertEquals(Player.RED, game.getCityAt(new Position(4,1)).getOwner());
		assertNull(game.getWinner());
	}
	
	@Test
	public void blueTakesTheBlueCityAfter20RoundAndDoesNotWin(){
		game.moveUnit(new Position(3,2), new Position(1,1));
		numberOfRounds(20);
		assertNull(game.getWinner());
	}
	
}