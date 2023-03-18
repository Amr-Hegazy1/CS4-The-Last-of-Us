package engine;


import java.util.*;

import model.characters.*;
import model.world.*;

import java.io.*;


public class Game {
	
	public static ArrayList<Hero> availableHeros;
	public static ArrayList<Hero> heros;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	
	
	/**
	 * @return the availableHeros
	 */
	public static ArrayList<Hero> getAvailableHeros() {
		return availableHeros;
	}


	/**
	 * @param availableHeros the availableHeros to set
	 */
	public static void setAvailableHeros(ArrayList<Hero> availableHeros) {
		Game.availableHeros = availableHeros;
	}


	/**
	 * @return the heros
	 */
	public static ArrayList<Hero> getHeros() {
		return heros;
	}


	/**
	 * @param heros the heros to set
	 */
	public static void setHeros(ArrayList<Hero> heros) {
		Game.heros = heros;
	}


	/**
	 * @return the zombies
	 */
	public static ArrayList<Zombie> getZombies() {
		return zombies;
	}


	/**
	 * @param zombies the zombies to set
	 */
	public static void setZombies(ArrayList<Zombie> zombies) {
		Game.zombies = zombies;
	}


	/**
	 * @return the map
	 */
	public static Cell[][] getMap() {
		return map;
	}


	/**
	 * @param map the map to set
	 */
	public static void setMap(Cell[][] map) {
		Game.map = map;
	}


	public Game() {
		
		availableHeros = new ArrayList<Hero>();
		heros = new ArrayList<Hero>();
		zombies = new ArrayList<Zombie>();
		map = new Cell[15][15];
		
	}
	
	public static void loadHeros(String filePath) throws Exception {
		
		File myFile = new File(filePath);
		
		Scanner sc = new Scanner(myFile);
		
		while(sc.hasNextLine()) {
			String[] heroArr = sc.nextLine().split(",");
			
			Hero hero = null;
			
			String name = heroArr[0];
			int maxHp = Integer.parseInt(heroArr[2]);
			int attackDmg = Integer.parseInt(heroArr[4]);
			int maxActions = Integer.parseInt(heroArr[3]);
			
			switch(heroArr[1]) {
			
			case "FIGH": hero = new Fighter(name,maxHp,attackDmg,maxActions);break;
			case "EXP": hero = new Explorer(name,maxHp,attackDmg,maxActions);break;
			case "MED": hero = new Medic(name,maxHp,attackDmg,maxActions);break;
			
			
			}
			
			
			availableHeros.add(hero);
			
			
		}
		
	}
	
	
	
	
}
