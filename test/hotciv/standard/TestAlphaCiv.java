package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.AlphaFactory;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases */
public class TestAlphaCiv {
  private Game game;
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
	game = new GameImpl( new AlphaFactory() );
  }

  public void numberOfRounds(int turns) {
	  for(int i = 0; i<turns*2; i++){		  
		  game.endOfTurn();
	  }
  }
  
  @Test
  public void shouldBeRedPlayerThatStarts() {
	  assertEquals( Player.RED, game.getPlayerInTurn());
  }
  
  @Test
  public void shouldBeBlueAfterOneEndOfTurn() {
	  game.endOfTurn();
	  assertEquals(Player.BLUE,game.getPlayerInTurn());
  }
  
  @Test
  public void shouldStartAtAge4000BC() {
	  assertEquals(-4000, game.getAge());
  }
  
  @Test
  public void shouldIncrementAgeBy100YearPerEndOfTurn() {
	  numberOfRounds(2);
	  assertEquals(-3800, game.getAge());
	  numberOfRounds(5);
	  assertEquals(-3300, game.getAge());
  }
  
  @Test
  public void shouldBeRedWhoWinsInYear3000BC(){
	  numberOfRounds(2);
	  numberOfRounds(8);
	  assertEquals(Player.RED, game.getWinner());
  }
  
  @Test
  public void shouldBeRedCityAt1_1(){
	  assertEquals(Player.RED,game.getCityAt(new Position(1,1)).getOwner());
  }

  @Test
  public void shouldBeBlueCityAt4_1(){
	  City c = game.getCityAt(new Position(4,1));
	  assertEquals(Player.BLUE,c.getOwner());
  }
  
  @Test
  public void shouldBeNoCityAt5_1(){
	  City c = game.getCityAt(new Position (5,1));
	  assertNull(c);
  }
 
  @Test
  public void shouldBeOceanTileAt1_0(){
	  Tile t = game.getTileAt(new Position(1,0));
	  assertEquals("ocean",t.getTypeString());
  }
  
  @Test
  public void shouldBeHillsTileAt0_1() {
	  Tile t = game.getTileAt(new Position(0,1));
	  assertEquals("hills",t.getTypeString());
  }

  @Test
  public void shouldBeMountainTileAt2_2() {
	  Tile t = game.getTileAt(new Position(2,2));
	  assertEquals("mountain",t.getTypeString());
  }
  
  @Test
  public void shouldBePlainAtRandomTile() {
	  Tile t = game.getTileAt(new Position(7,2) );
	  
	  assertEquals("plains",t.getTypeString());
  }
  
  @Test
  public void citySizeShouldNotIncrease() {
	  City c = game.getCityAt( new Position(1,1) );
	  
	  assertEquals(1,c.getSize());
	  numberOfRounds(14);
	  assertEquals(1,c.getSize());

  }

  @Test
  public void cityProductionWorthShouldIncreaseBy6EachRound(){
	  assertEquals(0,((GameImpl) game).getCityWorth(Player.RED));
	  numberOfRounds(1);
	  assertEquals(6,((GameImpl)game).getCityWorth(Player.BLUE));
	  numberOfRounds(1);
	  assertEquals(2,((GameImpl)game).getCityWorth(Player.BLUE));
  }

 
  @Test
  public void redShouldHaveAnArcherAt2_0(){
//	  Tjekker at der er en archer p� (2,0)
	  assertEquals("archer", game.getUnitAt(new Position(2,0)).getTypeString());
//	  Tjekker at den unit, der er p� 2,0, er ejet af red.
	  assertEquals(Player.RED, game.getUnitAt(new Position(2,0)).getOwner());
  }
  
  @Test
  public void blueShouldHaveALegionAt3_2(){
//	  Tjekker at der er en legion p� (3,2)
	  assertEquals("legion", game.getUnitAt(new Position(3,2)).getTypeString());
//	  Tjekker at den unit, der er p� 3,2, er ejet af blue.
	  assertEquals(Player.BLUE, game.getUnitAt(new Position(3,2)).getOwner());

  }

  @Test
  public void redShouldHaveASettlerAt4_3(){
//	  Tjekker at der er en settler p� (4,3)
	  assertEquals("settler", game.getUnitAt(new Position(4,3)).getTypeString());
//	  Tjekker at den unit, der er p� 4,3, er ejet af red.
	  assertEquals(Player.RED, game.getUnitAt(new Position(4,3)).getOwner());

  }
  
  
  @Test
  public void redCityShouldProduceArchers() {
	  numberOfRounds(0);
	  assertEquals("archer", game.getCityAt(new Position (1,1)).getWorkforceFocus());
  }
  
  @Test
  public void shouldProduceArcherAt1_1WhenCityWorthIs10OrAbove() {
	  numberOfRounds(3);
	  assertEquals("archer", game.getUnitAt(new Position(1,1)).getTypeString());
  }
  
  @Test
  public void redSettlerCanMoveFrom4_3To3_2() {
	  assertEquals(GameConstants.SETTLER,game.getUnitAt(new Position(4,3)).getTypeString()); // Checks if there is a Settler at 4,3
	  game.moveUnit(new Position(4,3), new Position(3,2) );	// Moves settler from 4,3 to 3,2
	  assertEquals(GameConstants.SETTLER, game.getUnitAt(new Position(3,2)).getTypeString());	// Checks that the Settler has moved to 3,2
	  assertNull(game.getUnitAt(new Position(4,3))); // Checks thats the settler has moved away from 4,3
  }
  	
  @Test
  public void blueLegionIsDefeatedAndRemovedFrom3_2WhenSettlerAttacks() {
	  assertEquals(GameConstants.LEGION, game.getUnitAt(new Position(3,2)).getTypeString()); // Checks that there is a legion at 3_2 before attack
	  game.moveUnit(new Position(4,3), new Position(3,2) );	// Moves settler from 4,3 to 3,2
	  assertEquals(GameConstants.SETTLER,game.getUnitAt(new Position(3,2)).getTypeString());
  }

}

  
 