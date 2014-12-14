package hotciv.standard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hotciv.TestStubs.FixedDieStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.DieStrategy;
import hotciv.variants.EpsilonAttackStrategy;
import hotciv.variants.GammaFactory;
import hotciv.variants.RandomDieStrategy;

import org.junit.Before;
import org.junit.Test;

public class TestSemiCiv {

	private Game game;
	
	/** Fixture for gammaciv testing. */
	@Before
	public void setUp() {

		game = new GameImpl( new SemiFactory(new RandomDieStrategy(), new RandomDieStrategy()) );
	}

	public void numberOfRounds(int turns) {
		for(int i = 0; i<turns*2; i++){		  
			game.endOfTurn();
		}
	}



	/*		Test of World Aging through the BetaCiv algorithm		*/
	@Test
	public void shouldStartAtAge4000BC() {
		assertEquals(-4000, game.getAge());
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval1() {
		numberOfRounds(10);
		assertEquals(-3000, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval2() {
		numberOfRounds(40);
		assertEquals(-1, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval3() {
		numberOfRounds(41);
		assertEquals(1, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval4() {
		numberOfRounds(42);
		assertEquals(50, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval5() {
		numberOfRounds(43);
		assertEquals(100, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval6() {
		numberOfRounds(45);
		assertEquals(200, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval7() {
		numberOfRounds(76);
		assertEquals(1750, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval8() {
		numberOfRounds(82);
		assertEquals(1900, game.getAge()); 
	} 

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval9() {
		numberOfRounds(96);
		assertEquals(1970, game.getAge()); 
	}

	@Test
	public void shouldIncrementAgeAccordingToAgeInterval10() {
		numberOfRounds(100);
		assertEquals(1974, game.getAge()); 
	}


	/*		Test of Unit Actions through the GammaUnitActionStrategy		*/
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


	/*		Test of World Layout through the DeltaWorldLayoutStrategy		*/
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


	/*		Test of Attack Strategy by using EpsilonAttackStrategy		*/
	@Test
	public void dieRolls2() {
		DieStrategy die = new FixedDieStrategy(2);
		assertEquals("The die rolls 2", 2, die.roll());
	}

	/** Af en eller anden grund kører vi med DeltaWorldLayout, så vores units står nogle andre steder end i AlphaCiv**/

	@Test
	public void redShouldHaveAnArcherAt3_0(){
		assertEquals(GameConstants.ARCHER, game.getUnitAt(new Position(3,8)).getTypeString());
		assertEquals(Player.RED, game.getUnitAt(new Position(3,8)).getOwner());
	}
	@Test
	public void blueShouldHaveALegionAt4_4(){
		assertEquals(GameConstants.LEGION, game.getUnitAt(new Position(4,4)).getTypeString());
		assertEquals(Player.BLUE, game.getUnitAt(new Position(4,4)).getOwner());
	}

	@Test
	public void attackingArcherWinsBattleWhenRolling2AgainstLegionRolling1() {
		UnitImpl attacker = (UnitImpl) game.getUnitAt(new Position(3, 8));
		UnitImpl defender = (UnitImpl) game.getUnitAt(new Position(4, 4));
		assertEquals("The attackers strength: ", 2, attacker.getAttackingStrength());
		assertEquals("The defenders strength:", 2, defender.getDefensiveStrength());
		AttackStrategy as = new EpsilonAttackStrategy(new FixedDieStrategy(2), new FixedDieStrategy(1));
		assertTrue("The attacker wins", as.fight(attacker, defender));
	}

	@Test
	public void attackingArcherLossesBattleWhenRolling2AgainstLegionRolling2() {
		UnitImpl attacker = (UnitImpl) game.getUnitAt(new Position(3, 8));
		UnitImpl defender = (UnitImpl) game.getUnitAt(new Position(4, 4));
		assertEquals("The attackers strength: ", 2, attacker.getAttackingStrength());
		assertEquals("The defenders strength:", 2, defender.getDefensiveStrength());
		AttackStrategy as = new EpsilonAttackStrategy(new FixedDieStrategy(2), new FixedDieStrategy(2));
		assertTrue("The defender wins", as.fight(defender, attacker));
	}

	@Test
	public void attackingLegionWinsBattleWhenRolling1AgainstArcherRolling1() {
		UnitImpl attacker = (UnitImpl) game.getUnitAt(new Position(4, 4));
		UnitImpl defender = (UnitImpl) game.getUnitAt(new Position(3, 8));
		assertEquals("The attackers strength: ", 4, attacker.getAttackingStrength());
		assertEquals("The defenders strength:", 3, defender.getDefensiveStrength());
		AttackStrategy as = new EpsilonAttackStrategy(new FixedDieStrategy(1), new FixedDieStrategy(1));
		assertTrue("The attacker wins", as.fight(attacker, defender));
	}

	@Test
	public void attackingLegionLossesBattleWhenRolling2AgainstArcherRolling3() {
		UnitImpl attacker = (UnitImpl) game.getUnitAt(new Position(4, 4));
		UnitImpl defender = (UnitImpl) game.getUnitAt(new Position(3, 8));
		assertEquals("The attackers strength: ", 4, attacker.getAttackingStrength());
		assertEquals("The defenders strength:", 3, defender.getDefensiveStrength());
		AttackStrategy as = new EpsilonAttackStrategy(new FixedDieStrategy(2), new FixedDieStrategy(3));
		assertFalse("The defender wins", as.fight(attacker, defender));
	}


}