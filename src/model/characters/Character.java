package model.characters;
import java.awt.*;
import java.util.Scanner;


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
	public void attack() {
		Character target =this.getTarget();
		int xHero= (int)location.getX();
		int yHero =(int)location.getY();
		int xTarget=(int)target.location.getX();
		int yTarget=(int)target.location.getY();
		if((Math.abs(xHero-xTarget)==0|| Math.abs(xHero-xTarget)==1) && (Math.abs(yTarget-yHero)==0||Math.abs(yTarget-yHero)==1) && (Math.abs(yTarget-yHero)!=0 && Math.abs(xTarget-xHero)!=0)) {
			
			
		}
		
			
			
		int targetHp= target.getCurrentHp();
		targetHp-=this.attackDmg;
		target.setCurrentHp(targetHp);
		target.defend(this);
		
	}
	public void defend (Character c) {
		int cHp= c.getCurrentHp();
		cHp-=this.attackDmg/2;
		c.setCurrentHp(cHp);
		

		
	}
		
	

}
