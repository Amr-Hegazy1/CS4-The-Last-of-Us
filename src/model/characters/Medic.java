package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;

public class Medic extends Hero{
	
	// declare heal amount
	
	// Beware of case where heal amount is negative
	
	// also if this.currentHp += healAmount then check if currentHp is greater than max 
	// if that's the case then set currentHp to maxHp
	
	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}
	public  void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException{
		
		Character z =this.getTarget();
		if(z instanceof Zombie)
			throw new InvalidTargetException("Cannot heal a Zombie");
		else {
			super.useSpecial();
			z.setCurrentHp(z.getMaxHp());
				
			}
		}
	
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
			
			
			
			
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
			
		}
}
