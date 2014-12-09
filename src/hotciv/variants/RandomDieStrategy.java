package hotciv.variants;

import hotciv.interfacevariants.DieStrategy;

public class RandomDieStrategy implements DieStrategy {
	
	private java.util.Random random = new java.util.Random();

	@Override
	public int roll() {
		return random.nextInt(6) + 1;
	}

}
