package hotciv.variants;

import hotciv.interfacevariants.AgeStrategy;

public class AlphaAgeStrategy implements AgeStrategy {

	public int AgeWorld(int age) {
		return age += 100;
	}

}
