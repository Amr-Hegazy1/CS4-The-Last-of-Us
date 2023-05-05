package model.characters;

import java.awt.Point;

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
		int xHero =(int) this.getLocation().getX();
		int yHero =(int) this.getLocation().getY();
		Character z =this.getTarget();
		Point p = z.getLocation();
		int xTarget = (int) p.getX();
		int yTarget = (int) p.getY();
		if(Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1 ){
		if(z instanceof Zombie)
			throw new InvalidTargetException("Cannot heal a Zombie");
		else {
			super.useSpecial();
			z.setCurrentHp(z.getMaxHp());
			this.setSpecialAction(false);
				
			}
		}
		else {
			throw new InvalidTargetException("Cannot heal , out of range");
		}
		}
	
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
			
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
			
		}
}
