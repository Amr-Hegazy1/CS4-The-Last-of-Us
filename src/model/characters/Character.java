package model.characters;
import java.awt.*;


public abstract class Character {
	
	
	private String name;
	private Point location;
	private int maxHp;
	private int attackDmg;
	private int currentHp;
	private Character target;


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
			currentHp = 0;
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

}
