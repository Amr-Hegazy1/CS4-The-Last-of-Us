package model.characters;

import java.awt.Point;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;

public class Medic extends Hero{
	
	
	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}
	public  void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException{
	
		Character z = this.getTarget();
		
			
		if(z == null)
        	throw new InvalidTargetException("Select a zombie to cure.");
		
		if(this.getLocation() == null || z.getLocation() == null )
			throw new InvalidTargetException("Not on Map");
		
		if(z instanceof Zombie)
			throw new InvalidTargetException("Cannot heal a Zombie");
		//if(z.getCurrentHp()==z.getMaxHp())
		
		int xHero= (int)this.getLocation().getX();
		int yHero =(int)this.getLocation().getY();
		int xTarget=(int)z.getLocation().getX();
		int yTarget=(int)z.getLocation().getY();
		
			
		if((Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1)) {
			super.useSpecial();
			z.setCurrentHp(z.getMaxHp());
			this.setSpecialAction(false);
			
				
		}else {
			throw new InvalidTargetException("Target too far away!");
		}
		
		
	}
		
	
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
			
			
		
			super.attack();
		
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
			
		}
}
