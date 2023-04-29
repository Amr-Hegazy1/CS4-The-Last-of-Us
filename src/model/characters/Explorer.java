package model.characters;

import exceptions.NoAvailableResourcesException;
import model.collectibles.Supply;
import model.world.*;

public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}
	public  void useSpecial() throws  NoAvailableResourcesException{
		if (this.getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException("No Supply available");
		else {
			Supply.use(this);
			setSpecialAction(true);
			
	}
	}
}
