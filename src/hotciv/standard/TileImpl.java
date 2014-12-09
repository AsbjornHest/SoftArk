package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {

	private String typeString;
	
	public TileImpl(String t) {
		this.typeString = t;
	}
	
	public String getTypeString() {
		return typeString;
	}
}
