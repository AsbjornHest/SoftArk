package hotciv.interfacevariants;

import hotciv.standard.UnitImpl;

public interface AttackStrategy {

	boolean fight(UnitImpl attacker, UnitImpl defender);

}
