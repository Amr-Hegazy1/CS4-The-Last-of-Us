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
		int xHero= (int)location.getX();
		int yHero =(int)location.getY();
		int xTarget=(int)target.location.getX();
		int yTarget=(int)target.location.getY();
		if(Math.abs(xHero-xTarget) <= 1 && Math.abs(yTarget-yHero) <= 1 && !(Math.abs(yTarget-yHero) == 0 && Math.abs(xTarget-xHero) == 0)) {
			int targetHp = target.getCurrentHp();
			targetHp -= this.attackDmg;
			target.setCurrentHp(targetHp);
			target.defend(this);
			
		}else {
			throw new InvalidTargetException("Target is too far away! Pick a closer target.");
		}
		
			
		
		
	}
	public void defend (Character c) {
		int cHp= c.getCurrentHp();
		cHp -= this.attackDmg/2;
		c.setCurrentHp(cHp);
		if( this instanceof Hero ) {
			Hero hero = (Hero) this;
			int actionsAvailable = hero.getActionsAvailable();
			actionsAvailable--;
			hero.setActionsAvailable(actionsAvailable);
		}

		
	}
	
	//should this method throw an exception?
	public void onCharacterDeath() {
		int x = (int) this.location.getX();
		int y = (int) this.location.getY();
		Cell[][] map = Game.getMap();
		
		map[x][y] = new CharacterCell(); // what type of cell to be placed here?
		Game.setMap(map);
		
		if (this instanceof Zombie) {
			Game.zombies.remove(this);
			Zombie newZombie = new Zombie();  // when a zombie dies then another one spawns
			newZombie.setLocation(generateRandomLoaction()); // add to a valid location
			Game.zombies.add(newZombie);
		}else if(this instanceof Hero ) {
			Game.heroes.remove(this);
		}
		
	}
	
	public static Point generateRandomLoaction() {
		Random rand = new Random();
		
		int randomX = rand.nextInt(15);
		int randomY = rand.nextInt(15);
		
		Cell[][] map = Game.getMap();
		
		while(map[randomX][randomY] instanceof TrapCell || map[randomX][randomY] instanceof CollectibleCell || (map[randomX][randomY] instanceof CharacterCell && ( (CharacterCell) map[randomX][randomY] ).getCharacter() != null) ) {
			
			randomX = rand.nextInt(15);
			randomY = rand.nextInt(15);
			
		}
		
		return new Point(randomX,randomY);
		
	}
	
	
		
	

}
