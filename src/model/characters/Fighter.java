package model.characters;





import java.util.Arrays;

import engine.Game;
import exceptions.*;


import model.collectibles.Supply;



public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}


	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		
		
		
		super.attack();
		if(!this.isSpecialAction()) {
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
		}
		
	}
	


	
		//this.attack();





	public  void useSpecial() throws  NoAvailableResourcesException {
		if (this.getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException("No Supply available");
		else {
			this.getSupplyInventory().get(0).use(this);
			setSpecialAction(true);
			//this.attack();
	
		}
	}
}
