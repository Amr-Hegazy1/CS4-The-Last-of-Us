package model.characters;

import exceptions.NoAvailableResourcesException;
import model.collectibles.Supply;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}
	public  void useSpecial() throws  NoAvailableResourcesException {
	if (this.getSupplyInventory().isEmpty())
		throw new NoAvailableResourcesException("No Supply available");
	else {
		Supply.use(this);
		setSpecialAction(true);
		//this.attack();
}
}
}