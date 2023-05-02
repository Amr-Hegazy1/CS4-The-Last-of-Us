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
		this.specialAction=false;
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
		if (p.getX()>14 || p.getX()<0)
			return false;
		else if(p.getY()>14 || p.getY()<0)
			return false;
		else return true;
	}
	
	public void move(Direction d) throws  MovementException,NotEnoughActionsException{
		Point p =this.getLocation();
		Point original =(Point) p.clone();
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
			throw new MovementException("Cannot move in this direction");
			 
		}
		else if (isvalid(p)) {
			actionsAvailable--;
			setVisibility(p);
		}
		//direction visibility part 
		
		setVisibility(original);

		int	x_old=(int) original.getX();
		 int y_old= (int) original.getY();
		 Cell c_old[][]=Game.getMap();
		 c_old[x_old][y_old] = new CharacterCell(null);
		 
		 int	x =(int) p.getX();
		 int	y= (int) p.getY();
		 Cell c[][]=Game.getMap();
		 if(c[x][y] instanceof CharacterCell) {
			 if(((CharacterCell) c[x][y]).getCharacter()!=null) {
				 p=original;
				 throw new MovementException("Cannot move in this direction");
					 
			 }
				 
		 }
		 if (c[x][y] instanceof TrapCell) {
				int hp= this.getCurrentHp();
				hp-=((TrapCell)c[x][y]).getTrapDamage();
				this.setCurrentHp(hp);
			}
		 if (c[x][y] instanceof CollectibleCell) {
			 
			 ( (CollectibleCell) c[x][y]).getCollectible().pickUp(this);
		 }
		 c[x][y] = new CharacterCell(this);
		

		
	}
	
	
	
	public static void setVisibility(Point loc) {
		
			int x = (int) loc.getX();
			int y = (int) loc.getY();
			Cell[][] map = Game.getMap();
			int l =0 ; int r =0 ; int u=0; int d=0;
			if(x!=0)
				l=1;
	        if(x!=14)
			    r=1;
	        if(y!=0)
				d=1;
	        if(y!=14)
			    u=1;
				map[x+r][y].setVisible(true);
				map[x+r][y+u].setVisible(true);
				map[x+r][y-d].setVisible(true);
				map[x][y+u].setVisible(true);
				map[x][y-d].setVisible(true);
				map[x-l][y].setVisible(true);
				map[x-l][y+u].setVisible(true);
				map[x-l][y-d].setVisible(true);
		
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
		

		Character z = this.getTarget();

		if( z instanceof Hero )
			throw new InvalidTargetException("Fellow heroes are uncurable. Only zombies are curable");

		
		this.getVaccineInventory().get(0).use(this);
		actionsAvailable--;
		//	this.setActionsAvailable(actionsAvailable);
		Point p =  z.getLocation();
		int	x =(int) p.getX();
		int	y= (int) p.getY();
		Cell c[][]=Game.getMap();
		Hero heroToBeAdded = Game.getAvailableHeroes().remove(0);
		c[x][y] = new CharacterCell(heroToBeAdded);
		Game.getZombies().remove(z);
		Game.getHeroes().add(heroToBeAdded);
		Game.setMap(c);
			
		
		
	}
	

}
