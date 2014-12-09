package hotciv.TestStubs;

import hotciv.interfacevariants.DieStrategy;

/** A test stub saa vi selv kan styre hvad terningen skal slaa **/

public class FixedDieStrategy implements DieStrategy {

	private int value;
	
	public FixedDieStrategy(int value) {
		this.value = value;
	}
	@Override
	public int roll() {
		
		return value;
	}

}
