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
		if( target == null )
			throw new InvalidTargetException("Please select a target!");
		
		int xHero = (int) location.getX();
		int yHero = (int) location.getY();
		int xTarget = (int) target.location.getX();
		int yTarget = (int) target.location.getY();
		
		if(Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1 && !(Math.abs(yTarget-yHero) == 0 && Math.abs(xTarget-xHero) == 0)) {
			int targetHp = target.getCurrentHp();
			targetHp -= this.attackDmg;
			target.setCurrentHp(targetHp);
			

			
			target.defend(this);
			if(target.currentHp <= 0)
				target.onCharacterDeath();

			
		}else {
			throw new InvalidTargetException("Target is too far away! Pick a closer target.");
		}
		
			
			
		
			
		
		
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
		
		int locX = (int) this.location.getX();
		int locY = (int) this.location.getY();
		int[] transformCords = Game.transform(locX, locY);
		int x = transformCords[0];
		int y = transformCords[1];
		Cell[][] map = Game.getMap();
		
		map[x][y] = new CharacterCell(null); 
		Game.setMap(map);
		
		if (this instanceof Zombie) {
			Game.zombies.remove(this);
			Zombie newZombie = new Zombie();  // when a zombie dies then another one spawns
			Point newZombieLocation = Game.generateRandomLoaction();
			newZombie.setLocation(newZombieLocation); // add to a valid location
			Game.zombies.add(newZombie);
			
			int newZombieLocationX = (int) newZombieLocation.getX();
			int newZombieLocationY = (int) newZombieLocation.getX();
			
			int[] transform_cords = Game.transform(newZombieLocationX, newZombieLocationY);
			
			int newZombieX = transform_cords[0];
			int newZombieY = transform_cords[1];
			
			Game.map[newZombieX][newZombieY] = new CharacterCell(newZombie);
			
			
		}else if(this instanceof Hero ) {
			
			Game.heroes.remove(this);
			Game.setVisibility(this.location);
			Game.checknull(Game.map[x][y]).setVisible(true);
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
		
	

}
