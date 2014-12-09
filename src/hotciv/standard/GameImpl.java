package hotciv.standard;

import java.util.HashMap;

import hotciv.framework.*;
import hotciv.interfacevariants.AgeStrategy;
import hotciv.interfacevariants.UnitActionStrategy;
import hotciv.interfacevariants.WinnerStrategy;
import hotciv.interfacevariants.WorldLayoutStrategy;

/** 
 Skeleton implementation of HotCiv.
 */

public class GameImpl implements Game {
	private HotCivFactory factory;
	private AgeStrategy ageStrategy;
	private WinnerStrategy winnerStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;	
    private UnitActionStrategy unitActionStrategy;
    
    
	private Player playerInTurn; // returns the player in turn == RED or BLUE player	
	private int age; // age of the game
	private int numberOfRounds;
	
	private HashMap<Position, City> cities = new HashMap<Position, City>(); // Hashmap with cities
	private HashMap<Position, Tile> tiles = new HashMap<Position, Tile>(); // Hashmap with tiles
	private HashMap<Player, Integer> battlesWon = new HashMap<Player, Integer>(); // Hashmap with battles won
	public HashMap<Position, Unit> units = new HashMap<Position, Unit>(); // Hashmap with Units

	private CityImpl cityRed = new CityImpl(Player.RED);
	private CityImpl cityBlue = new CityImpl(Player.BLUE);

	public GameImpl(HotCivFactory factory) {
		this.factory = factory;
		this.ageStrategy = factory.createAgeStrategy(getAge());
		this.winnerStrategy = factory.createWinnerStrategy();
		this.worldLayoutStrategy = factory.createWorldLayoutStrategy();
		this.unitActionStrategy = factory.createUnitActionStrategy();
		
		fillTheWorld(worldLayoutStrategy.defineWorld());
		fillTheWorld(worldLayoutStrategy.defineCities());
		fillTheWorld(worldLayoutStrategy.defineUnits());
		
		playerInTurn = Player.RED; // Red player is in turn at the start of the game 
		age = -4000; // sets the starting age of the game to 4000BC
		battlesWon.put(Player.RED, 0);
		battlesWon.put(Player.BLUE, 0);
		numberOfRounds = 0;
		
	
	}
	
	public Tile getTileAt( Position p ) { 
		if (tiles.containsKey(p)) {
			return tiles.get(p);
		}
		return new TileImpl(GameConstants.PLAINS);
	}

	public City getCityAt( Position p ) { 
		if (cities.containsKey(p)) { // If the position is 1,1 red city is returned
			return cities.get(p);
		}
		return null;
	}

	public Player getPlayerInTurn() { 
		return playerInTurn; 
	}

	public Player getWinner() { 
		return winnerStrategy.getWinner(this);
	}

	public int getAge() { 
		return age;
	}

	public boolean moveUnit( Position from, Position to ) {
		if(getUnitAt(from) != null && getUnitAt(to) != null) {
			if(getUnitAt(to).getOwner().equals(Player.BLUE)){
				incrementWinningCount(Player.RED);
			}
			if(getUnitAt(to).getOwner().equals(Player.RED)){
				incrementWinningCount(Player.BLUE);
			}
		}
		if(getCityAt(to) != null){
			if(getCityAt(to).getOwner() != getUnitAt(from).getOwner()){
				((CityImpl) getCityAt(to)).setOwner(getUnitAt(from).getOwner());
			}
		}
		Unit unit = removeUnitAt(from);
		setUnitAt(to, unit);
		return true;
	}
	
	public Unit getUnitAt(Position p) {
		if(units.containsKey(p) ) {
			return units.get(p);
		}
		return null;
	}	
	
	public Unit removeUnitAt(Position from) {
		return units.remove(from);
	}
	
	public void setUnitAt(Position to, Unit unit) {
		units.put(to, unit);
		
	}
	
	public void incrementWinningCount(Player player){
		if(player.equals(Player.RED)){
			int red = battlesWon.get(Player.RED);
			red++;
			battlesWon.put(Player.RED, red);
		} else if(player.equals(Player.BLUE)){
			int blue = battlesWon.get(Player.BLUE);
			blue++;
			battlesWon.put(Player.BLUE, blue);
		}
	}

	public HashMap<Player, Integer> gimmeAttackCounts(){
		return battlesWon;
	}

	public void endOfTurn() {
		// Change who is in turn over to the other player
		if ( getPlayerInTurn() == Player.RED) {
			playerInTurn = Player.BLUE;
			City c1 = cityBlue;
			c1.increaseWorth(6); // increment the city worth of BLUE's city
			if(c1.getWorth() >= 10) {
				changeProductionInCityAt(new Position (4,1), GameConstants.ARCHER);	//Changes production in chosen city
				setUnitAt(new Position (4,1), new UnitImpl(cityBlue.getProduction(),Player.BLUE)); // Sets a unit at a position in the world
				c1.decreaseWorth(10);
			}
		}	else{
			playerInTurn = Player.RED;
			City c2 = cityRed;
			c2.increaseWorth(6); // increment the city worth of RED's city
			if(c2.getWorth() >= 10) {
				changeProductionInCityAt(new Position (1,1), GameConstants.ARCHER);	//Changes production in chosen city
				setUnitAt(new Position (1,1), new UnitImpl(cityRed.getProduction(),Player.RED)); // Sets a unit at a position in the world
				c2.decreaseWorth(10);
			}
			age = ageStrategy.AgeWorld(age); // increment the age of the world
			numberOfRounds++;
		}


	}

	public int getNumberOfRound(){
		return numberOfRounds;
	}
	
	/** Define the world as the DeltaCiv layout */
	private void fillTheWorld(String[] layoutString) {
		String[] layout = layoutString;
		String line;
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
			line = layout[r];
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				Position p = new Position(r,c);
				char tileChar = line.charAt(c);
				if ( tileChar == '.' ) { setTileAt(p, new TileImpl(GameConstants.OCEANS));}
				if ( tileChar == 'o' ) { setTileAt(p, new TileImpl(GameConstants.PLAINS)); }
				if ( tileChar == 'M' ) { setTileAt(p, new TileImpl(GameConstants.MOUNTAINS)); }
				if ( tileChar == 'f' ) { setTileAt(p, new TileImpl(GameConstants.FOREST));; }
				if ( tileChar == 'h' ) { setTileAt(p, new TileImpl(GameConstants.HILLS)); }
				if ( tileChar == 'B' ) { setCityAt(p, new CityImpl(Player.BLUE)); }
				if ( tileChar == 'R' ) { setCityAt(p, new CityImpl(Player.RED)); }
				if ( tileChar == 'A' ) { setUnitAt(p, new UnitImpl(GameConstants.ARCHER, Player.RED)); }
				if ( tileChar == 'a' ) { setUnitAt(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE)); }
				if ( tileChar == 'S' ) { setUnitAt(p, new UnitImpl(GameConstants.SETTLER, Player.RED)); }
				if ( tileChar == 's' ) { setUnitAt(p, new UnitImpl(GameConstants.SETTLER, Player.BLUE)); }
				if ( tileChar == 'L' ) { setUnitAt(p, new UnitImpl(GameConstants.LEGION, Player.RED)); }
				if ( tileChar == 'l' ) { setUnitAt(p, new UnitImpl(GameConstants.LEGION, Player.BLUE)); }
			}
		}
	}

	public void setCityAt(Position to, City city) {
		cities.put(to, city);
	}
	
	public void setTileAt(Position to, Tile tile) {
		tiles.put(to, tile);
		
	}
	public HashMap<Position, City> getCities(){
		return cities; 
	}

	public int getCityWorth(Player player) {
		if(player.equals(Player.RED)){
			return cityRed.getWorth();
		} else if (player.equals(Player.BLUE)){
			return cityBlue.getWorth();
		}
		return 0;
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

	public void changeProductionInCityAt( Position p, String unitType ) {
		if(p.equals(new Position (1,1))) {
			cityRed.setProduction(unitType);
		}
		if(p.equals(new Position (4,1))) {
			cityBlue.setProduction(unitType);
		}
	}

	public void performUnitActionAt( Position p ) {
		 if(getUnitAt(p).getTypeString().equals(GameConstants.SETTLER))
			unitActionStrategy.performSettlerActionAt(this,p);
		else
			unitActionStrategy.performArcherActionAt(this,p);
		
	}
}
