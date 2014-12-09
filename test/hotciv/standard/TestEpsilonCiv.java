package hotciv.standard;

import hotciv.TestStubs.FixedDieStrategy;
import hotciv.framework.*;
import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.DieStrategy;
import hotciv.variants.EpsilonCivAttackStrategy;
import hotciv.variants.EpsilonFactory;
import hotciv.variants.RandomDieStrategy;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for EpsilonCiv test cases */
public class TestEpsilonCiv {
	
	private Game game;
	/** Fixture for epsilonciv testing. */
	
	@Before
	public void setUp() {

		game = new GameImpl( new EpsilonFactory(new RandomDieStrategy(), new RandomDieStrategy()) );
	}

	public void numberOfRounds(int rounds) {
		for(int i = 0; i<rounds*2; i++){		  
			game.endOfTurn();
		}
	}

	@Test
	public void redShouldWinAfter3Attacks(){ // Blue unit moves from 10,0 to 3 positions with red settlers and wins
		game.moveUnit(new Position(10,0), new Position(10,1));
		game.moveUnit(new Position(10,1), new Position(10,2));
		game.moveUnit(new Position(10,2), new Position(10,3));
		assertEquals(Player.BLUE, game.getWinner());
	}

	@Test
	public void blueShouldWinAfter3Attacks(){ // Blue unit moves from 10,0 to 3 positions with red settlers and wins
		game.moveUnit(new Position(10,0), new Position(10,1));
		game.moveUnit(new Position(10,1), new Position(10,2));
		game.moveUnit(new Position(10,2), new Position(10,3));
		assertEquals(Player.BLUE, game.getWinner());
	}
	
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
	        AttackStrategy as = new EpsilonCivAttackStrategy(new FixedDieStrategy(2), new FixedDieStrategy(1));
	        assertTrue("The attacker wins", as.fight(attacker, defender));
	    }
	 
	 /** Bare for lige at overbevise mig selv om at det (også) virker med en random
	 @Test
	    public void randomattackingArcherWinsBattleWhenRolling2AgainstLegionRolling1() {
	        UnitImpl attacker = (UnitImpl) game.getUnitAt(new Position(3, 8));
	        UnitImpl defender = (UnitImpl) game.getUnitAt(new Position(4, 4));
	        assertEquals("The attackers strength: ", 2, attacker.getAttackingStrength());
	        assertEquals("The defenders strength:", 2, defender.getDefensiveStrength());
	        AttackStrategy as = new EpsilonCivAttackStrategy(new RandomDieStrategy(), new RandomDieStrategy());
	        assertTrue("The attacker wins", as.fight(attacker, defender));
	    }
	    **/
}
