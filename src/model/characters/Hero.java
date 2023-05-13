package model.characters;

import java.awt.Point;
import java.util.*;

import engine.Game;
import exceptions.*;

import model.collectibles.*;
import model.world.Cell;
import model.world.CharacterCell;

import model.world.CollectibleCell;
import model.world.TrapCell;



public abstract class Hero extends Character {
	
	
	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	
	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.vaccineInventory = new ArrayList<Vaccine>();
		this.supplyInventory = new ArrayList<Supply>();
		this.specialAction = false;
	}
	
	
	public int getActionsAvailable() {
		return actionsAvailable;
	}
	
	
	public void setActionsAvailable(int actionsAvailable) {
//		if(actionsAvailable > 0)
//			this.actionsAvailable = actionsAvailable;
//		
//		else
//			this.actionsAvailable = 0;
		
		this.actionsAvailable = actionsAvailable;
	}
	
	
	public boolean isSpecialAction() {
		return specialAction;
	}
	
	
	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}
	
	
	public int getMaxActions() {
		return maxActions;
	}
	
	
	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}
	
	
	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}
	
	

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("Can't attack your fellow heores. Can only attack zombies");
		}
		
		Character target = this.getTarget();
		
		if( target == null || target.getLocation() == null )
			throw new InvalidTargetException("Please select a target!");
		
		int xHero = (int) this.getLocation().getX();
		int yHero = (int) this.getLocation().getY();
		int xTarget = (int) target.getLocation().getX();
		int yTarget = (int) target.getLocation().getY();
		
		if(!((Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1) && !(Math.abs(yTarget-yHero) == 0 && Math.abs(xTarget-xHero) == 0))) {
			throw new InvalidTargetException("Target is too far away! Pick a closer target.");
		}
		
		super.attack();
		
	}

	public static boolean isvalid(Point p) {
		if (p.getX()>14 || p.getX()<0)
			return false;
		else if(p.getY()>14 || p.getY()<0)
			return false;
		else return true;
	}
	
	
	
	
	
	
	

	public  void useSpecial() throws  NoAvailableResourcesException , InvalidTargetException{
		
		if (this.getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException("No Supply available");
		else {
			this.getSupplyInventory().get(0).use(this);
			setSpecialAction(true);
		}
		
	}
  
  
	public void cure() throws NotEnoughActionsException , InvalidTargetException, NoAvailableResourcesException{
		
		if(this.getActionsAvailable() <= 0)
			throw new NotEnoughActionsException("Sorry you don't have enough actions available");

		if( this.getVaccineInventory().isEmpty() )
			throw new NoAvailableResourcesException("No vaccines in inventory");
		

		int xHero =(int) this.getLocation().getX();
		int yHero =(int) this.getLocation().getY();
		Character z = this.getTarget();
		if(z==null)
        	throw new InvalidTargetException("Select a zombie to cure.");
		Point p = z.getLocation();
		int xTarget = (int) p.getX();
		int yTarget = (int) p.getY();
		
		
		
		
		

		if( z instanceof Hero )
			throw new InvalidTargetException("Fellow heroes are uncurable. Only zombies are curable");
        
		if((Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1) && !(Math.abs(yTarget-yHero) == 0 && Math.abs(xTarget-xHero) == 0)) {
			this.getVaccineInventory().get(0).use(this);
//		
		}
		else {
			throw new InvalidTargetException("Cannot heal , out of range");
		}
		
			
  }
	
	public void reset() {
		this.setSpecialAction(false);
		this.setTarget(null);
		this.setActionsAvailable(maxActions);
		
	}
	
	
	
public void move(Direction d) throws  MovementException,NotEnoughActionsException{
	int hp= this.getCurrentHp();
	if (hp<=0) {
		this.onCharacterDeath();
		return;
	}
	
		int actions = this.getActionsAvailable();
		Point p = this.getLocation();
		Game.setVisibility(p);
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		int xnew = x;
		int ynew = y;
		
		if (actions>0) {
			
			if (d.equals(Direction.UP))
				xnew++;
			
			else if (d.equals(Direction.DOWN))
				xnew--;
				
			else if (d.equals(Direction.LEFT))
				ynew--;
			
			else if (d.equals(Direction.RIGHT))
				ynew++;
		}
		else 
			throw new NotEnoughActionsException("This hero doesn't have enough Action points");
		
		Point pnew = new Point(xnew,ynew);
		if (!isvalid(pnew))
			throw new MovementException("Cannot move in this direction");
			 
		Cell c[][] = Game.map;
		 
		 if(c[xnew][ynew] instanceof CharacterCell) {
			 if(((CharacterCell) c[xnew][ynew]).getCharacter()!=null) {
				 throw new MovementException("Cannot move in this direction");
					 
			 }
			 else {
				 c[x][y]= new CharacterCell(null);
				 c[xnew][ynew] = new CharacterCell(this);
				 this.setLocation(pnew);
				 
				 this.setActionsAvailable(--actions);
				 Game.setVisibility(pnew);
			 }
		}
		 else if(c[xnew][ynew] instanceof TrapCell) {
			 c[x][y]= new CharacterCell(null);
				hp-=((TrapCell)c[xnew][ynew]).getTrapDamage();
				this.setCurrentHp(hp);
				this.setActionsAvailable(--actions);
				c[xnew][ynew] = new CharacterCell(this);
				
				this.setLocation(pnew);
				if(hp<=0) {
					this.onCharacterDeath();
					c[xnew][ynew]= new CharacterCell(null);				
					}
				else {
					c[xnew][ynew] = new CharacterCell(this);
					this.setLocation(pnew);
					 
				}
				
				Game.setVisibility(pnew);
		 }
		 else if (c[xnew][ynew] instanceof CollectibleCell) {
		  this.setActionsAvailable(--actions);
	     ( (CollectibleCell) c[xnew][ynew]).getCollectible().pickUp(this);
		 c[x][y]= new CharacterCell(null);
		 c[xnew][ynew] = new CharacterCell(this);
		 this.setLocation(pnew);
		 Game.setVisibility(pnew);
		 }
		 Game.setMap(c);
}
	 
	
	

}