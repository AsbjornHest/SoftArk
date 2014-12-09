package hotciv.variants;

import hotciv.interfacevariants.WorldLayoutStrategy;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {

	public String[] defineWorld() {
		// Basically we use a 'data driven' approach - code the
		// layout in a simple semi-visual representation, and
		// convert it to the actual Game representation.
		String[] layoutString =
				new String[] {
				"...ooMooooo.....",
				"..ohhoooofffoo..",
				".oooooMooo...oo.",
				".ooMMMoooo..oooo",
				"...ofooohhoooo..",
				".ofoofooooohhoo.",
				"...ooo..........",
				".ooooo.ooohooM..",
				".ooooo.oohooof..",
				"offfoooo.offoooo",
				"oooooooo...ooooo",
				".ooMMMoooo......",
				"..ooooooffoooo..",
				"....ooooooooo...",
				"..ooohhoo.......",
				".....ooooooooo..",
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
				"----------------",
				"----------------",
				"----------------",
				"-----B----------",
				"----------------",
				"----------------",
				"----------------",
				"-----------R----",
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
				"----------------",
				"--------A-------",
				"----l-----------",
				"-----S----------",
				"----------------",
				"----------------",
				"----------------",
				"----------------",
				"sSSS------------",
				"SSSS------------",
				"-SSS------------",
				"----------------",
				"----------------",
				"----------------",
				
		};
		return layoutString;
	}
}
