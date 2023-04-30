package model.characters;

import exceptions.*;
import model.collectibles.Supply;
import model.world.*;



public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}


	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		
		if(this.getActionsAvailable() <= 0) {
			throw new NotEnoughActionsException("Not Enough Actions Available.");
		}
		
		if(! this.isSpecialAction() ) {
			super.attack();
		}else {
			Character target = this.getTarget();
			int xHero= (int) this.getLocation().getX();
			int yHero =(int)this.getLocation().getY();
			int xTarget=(int) target.getLocation().getX();
			int yTarget=(int)target.getLocation().getY();
			int targetHp = target.getCurrentHp();
			targetHp -= this.getAttackDmg();
			target.setCurrentHp(targetHp);
			target.defend(this);
		}
		int actionsAvailable = this.getActionsAvailable();
		actionsAvailable--;
		this.setActionsAvailable(actionsAvailable);
		
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
