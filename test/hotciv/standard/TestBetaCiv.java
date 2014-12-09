package hotciv.standard;

import hotciv.framework.*;
//import hotciv.variants.BetaAgeStrategy;
//import hotciv.variants.BetaWinnerStrategy;
import hotciv.variants.BetaFactory;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for BetaCiv test cases */
public class TestBetaCiv {
	private Game game;
	/** Fixture for betaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl( new BetaFactory() );
	}

	public void numberOfRounds(int rounds) {
		for(int i = 0; i<rounds*2; i++){		  
			game.endOfTurn();
		}
	}

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
	
	@Test
	public void gameShouldBeWonByPlayerRedIfRedOwnsAllCities(){
		assertEquals(Player.RED, game.getWinner());
	}
}
