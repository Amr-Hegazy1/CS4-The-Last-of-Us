package model.characters;

public class Medic extends Hero{
	
	// declare heal amount
	
	// Beware of case where heal amount is negative
	
	// also if this.currentHp += healAmount then check if currentHp is greater than max 
	// if that's the case then set currentHp to maxHp
	
	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}

}
