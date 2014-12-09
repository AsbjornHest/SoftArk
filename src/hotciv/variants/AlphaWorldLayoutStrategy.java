package hotciv.variants;

import hotciv.framework.*;
import hotciv.interfacevariants.WorldLayoutStrategy;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {

	public String[] defineWorld() {
		// Basically we use a 'data driven' approach - code the
		// layout in a simple semi-visual representation, and
		// convert it to the actual Game representation.
		String[] layoutString =
				new String[] {
				"-h--------------",
				".---------------",
				"--M-------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",

		};
		return layoutString;
	}
	
	public String[] defineCities() {
		// Basically we use a 'data driven' approach - code the
		// layout in a simple semi-visual representation, and
		// convert it to the actual Game representation.
		String[] layoutString =
				new String[] {		
				"----------------",
				"-R--------------",
				"----------------",
				"----------------",
				"-B--------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				
		};
		return layoutString;
	}
	
	public String[] defineUnits() {
		// Basically we use a 'data driven' approach - code the
		// layout in a simple semi-visual representation, and
		// convert it to the actual Game representation.
		String[] layoutString =
				new String[] {
				
				"----------------",
				"----------------",
				"A---------------",
				"--l-------------",
				"---S------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				
		};
		return layoutString;
	}
}
