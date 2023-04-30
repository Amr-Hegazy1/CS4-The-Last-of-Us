package model.characters;

import exceptions.*;

public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		if(!this.isSpecialAction() && this.getActionsAvailable() <= 0) {
			throw new NotEnoughActionsException("Not Enough Actions Available.");
		}
		
		super.attack();
		if(!this.isSpecialAction()) {
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
		}
		
	}
	

}
