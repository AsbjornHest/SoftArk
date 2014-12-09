package hotciv.variants;

import hotciv.interfacevariants.AttackStrategy;
import hotciv.interfacevariants.DieStrategy;
import hotciv.standard.UnitImpl;

public class EpsilonCivAttackStrategy implements AttackStrategy {
	
	private DieStrategy die1, die2;
	
	public EpsilonCivAttackStrategy(DieStrategy die1, DieStrategy die2) {
		this.die1 = die1;
		this.die2 = die2;
	}
	
	public boolean fight(UnitImpl attacker, UnitImpl defender) {
		return attacker.getAttackingStrength() * die1.roll() > defender.getDefensiveStrength()*die2.roll();
	}

}
