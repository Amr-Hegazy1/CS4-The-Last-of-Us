package model.characters;
import java.awt.*;
import java.util.*;


import engine.Game;
import exceptions.*;
import model.world.*;


public abstract class Character {
	
	
	private String name;
	private Point location;
	private int maxHp;
	private int attackDmg;
	private int currentHp;
	private Character target;
	
	public Character() {
	}


	public Character(String name,int maxHp,int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
		this.currentHp = maxHp;
	}
	
	
	public Point getLocation() {
		return location;
	}
	
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	
	public int getCurrentHp() {
		return currentHp;
	}
	
	
	public void setCurrentHp(int currentHp) {
		if (currentHp < 0)
			this.currentHp = 0;
		else if(currentHp <= maxHp)
			this.currentHp = currentHp;
		else
			this.currentHp = maxHp;
	}
	
	
	public Character getTarget() {
		return target;
	}
	
	
	public void setTarget(Character target) {
		this.target = target;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getMaxHp() {
		return maxHp;
	}
	
	
	public int getAttackDmg() {
		return attackDmg;
	}
	public void attack() throws InvalidTargetException,NotEnoughActionsException  {
		
		
		
		Character target = this.getTarget();
		
	
		int targetHp = target.getCurrentHp();
		targetHp -= this.attackDmg;
		target.setCurrentHp(targetHp);
		

		
		target.defend(this);
		if(target.currentHp <= 0)
			target.onCharacterDeath();

			
		
		
			
			
		
			
		
		
	}
	public void defend (Character c) {
		
		if(c.currentHp <= 0) return;
		
		int cHp= c.getCurrentHp();
		cHp -= this.attackDmg/2;
		c.setCurrentHp(cHp);
//		if( this instanceof Hero ) {
//			Hero hero = (Hero) this;
//			int actionsAvailable = hero.getActionsAvailable();
//			actionsAvailable--;
//			hero.setActionsAvailable(actionsAvailable);
//		}

		if(c.currentHp <= 0)
			c.onCharacterDeath();

		
	}
	
	//should this method throw an exception?
	public void onCharacterDeath() {
		
		int x = (int) this.location.getX();
		int y = (int) this.location.getY();
		//int[] transformCords = Game.transform(locX, locY);
		//int x = transformCords[0];
		//int y = transformCords[1];
		Cell[][] map = Game.getMap();
		
		map[x][y] = new CharacterCell(null); 
		
		
		if (this instanceof Zombie) {
			Game.zombies.remove(this);
			Zombie newZombie = new Zombie();  // when a zombie dies then another one spawns
			Point newZombieLoc = Game.generateRandomLoaction();
			newZombie.setLocation(newZombieLoc); // add to a valid location
			int x1 = (int) newZombieLoc.getX();
			int y1 = (int) newZombieLoc.getY();
			map[x1][y1] = new CharacterCell(newZombie);
			Game.zombies.add(newZombie);
		}else if(this instanceof Hero ) {
			Hero hero = (Hero)this;
			Game.setInvisibility(hero.getLocation());
			Game.heroes.remove(hero);
			
		}
		Game.setMap(map);
		
		
	}
	
	public static boolean isAdjacent(Point p1 , Point p2) {
		int x1 = (int) p1.getX();
		int x2 = (int) p1.getX();
		int y1 = (int) p1.getY();
		int y2 = (int) p1.getY();
		
		return Math.abs(x1-x2) <= 1 && Math.abs(y1-y2) <= 1 ;
	}
	
	
	
	
	
		
	

}
