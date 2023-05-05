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
		if(this.getActionsAvailable() <= 0) {
			throw new NotEnoughActionsException("Not Enough Actions Available.");
		}
		super.attack();
		
	}

	public static boolean isvalid(Point p) {
		if (p.getY()>14 || p.getY()<0)
			return false;
		else if(p.getY()>14 || p.getY()<0)
			return false;
		else return true;
	}
	
	/*public void move(Direction d) throws  MovementException,NotEnoughActionsException{
		
		Point p = this.getLocation();
		Point original = new Point ((int)p.getX(),(int)p.getY());
		if (this.getActionsAvailable()>0) {
			
			if (d.equals(Direction.UP))
				p.translate(0,1);
			
			else if (d.equals(Direction.DOWN))
				p.translate(0,-1);
				
			else if (d.equals(Direction.LEFT))
				p.translate(-1,0);
			
			else if (d.equals(Direction.RIGHT))
				p.translate(1,0);

		}
		
		else 
			throw new NotEnoughActionsException("This hero doesn't have enough Action points");
		 
		if (!isvalid(p)){
			p=original;
			this.setLocation(original);
			throw new MovementException("Cannot move in this direction");
			 
		}
		int locX = (int) p.getX();
		int	locY = (int) p.getY();
		int[] transform_cords = Game.transform(locX, locY);
		
		int	x = transform_cords[0];
	    int y= transform_cords[1];
		 Cell c[][] = Game.map;
		 
		 if(c[x][y] instanceof CharacterCell) {
			 if(((CharacterCell) c[x][y]).getCharacter()!=null) {
				 p=original;
				 throw new MovementException("Cannot move in this direction");
					 
			 }
			 else
				 c[x][y] = new CharacterCell(this);
			
				 
		 }
		if (isvalid(p)) {
			actionsAvailable--;
			this.setLocation(p);
			Game.setVisibility(p);
			
		}
		//direction visibility part 
		
		Game.setVisibility(original);
		

		
		
		 locX = (int) original.getX();
		 locY= (int) original.getY();
		 transform_cords = Game.transform(locX, locY);
		
		int x_old = transform_cords[0];
		int y_old = transform_cords[1];
		
		
		
		 Game.setMap(c);
		 Cell c_old[][] = Game.map;
		 c_old[x_old][y_old] = new CharacterCell(null); 
		 
		 Game.setMap(c_old);
		 
		 
		 if (c[x][y] instanceof TrapCell) {
				int hp= this.getCurrentHp();
				hp-=((TrapCell)c[x][y]).getTrapDamage();
				this.setCurrentHp(hp);
				if(hp<=0) {
					this.onCharacterDeath();
					c[x][y] = new CharacterCell(null); 				
					}
				else
					c[x][y] = new CharacterCell(this);
					
					
		              		
			}
		 if (c[x][y] instanceof CollectibleCell) {
			 
			 ( (CollectibleCell) c[x][y]).getCollectible().pickUp(this);
		 
		 c[x][y] = new CharacterCell(this);}
		 Game.setMap(c);
		

		
	}*/
	
	
	
	
	

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
		

		Character z = this.getTarget();

		if( z instanceof Hero )
			throw new InvalidTargetException("Fellow heroes are uncurable. Only zombies are curable");

		
		this.getVaccineInventory().get(0).use(this);
		actionsAvailable--;
		//	this.setActionsAvailable(actionsAvailable);
		Point p =  z.getLocation();
		int locX = (int) p.getX();
		int locY = (int) p.getY();
		//int[] transform_cords = Game.transform(locX, locY);
		
		//int	x = transform_cords[0];
	    //int y= transform_cords[1];
		Cell c[][]=Game.getMap();
		Hero heroToBeAdded = Game.getAvailableHeroes().remove(0);
		heroToBeAdded.setLocation(z.getLocation());
	    c[locX][locY] =new CharacterCell(heroToBeAdded); 
		Game.getZombies().remove(z);
		Game.getHeroes().add(heroToBeAdded);
		Game.setMap(c);
		
			
  }
	
	public void reset() {
		this.setSpecialAction(false);
		this.setTarget(null);
		this.setActionsAvailable(maxActions);
		
	}
	
	
	
public void move(Direction d) throws  MovementException,NotEnoughActionsException{
		
		Point p = this.getLocation();
		 Game.setVisibility(p);
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		int xnew = x;
		int ynew = y;
		
		if (this.getActionsAvailable()>0) {
			
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
				 Game.setVisibility(pnew);
			 }
		}
		 else if(c[xnew][ynew] instanceof TrapCell) {
			 c[x][y]= new CharacterCell(null);
		     	int hp= this.getCurrentHp();
				hp-=((TrapCell)c[xnew][ynew]).getTrapDamage();
				this.setCurrentHp(hp);

				if(hp<=0) {
					this.onCharacterDeath();
					c[xnew][ynew]= new CharacterCell(null);				
					}
				else {
					c[xnew][ynew] = new CharacterCell(this);
					this.setLocation(pnew);
					 Game.setVisibility(pnew);
				}
		 }
		 else if (c[xnew][ynew] instanceof CollectibleCell) {
			 
			 ( (CollectibleCell) c[xnew][ynew]).getCollectible().pickUp(this);
		 c[x][y]= new CharacterCell(null);
		 c[xnew][ynew] = new CharacterCell(this);
		 this.setLocation(pnew);
		 Game.setVisibility(pnew);
		 }
		 
}
	 public static void main(String[] args) {
		 
	 }
	
	

}