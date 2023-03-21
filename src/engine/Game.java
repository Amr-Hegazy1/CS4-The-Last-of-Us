package engine;


import java.util.*;

import model.characters.*;
import model.world.*;

import java.io.*;


public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	
	
	/**
	 * @return the availableHeroes
	 */
	public static ArrayList<Hero> getAvailableHeroes() {
		return availableHeroes;
	}


	/**
	 * @param availableHeroes the availableHeroes to set
	 */
	public static void setAvailableHeroes(ArrayList<Hero> availableHeroes) {
		Game.availableHeroes = availableHeroes;
	}


	/**
	 * @return the Heroes
	 */
	public static ArrayList<Hero> getHeroes() {
		return heroes;
	}


	/**
	 * @param Heroes the Heroes to set
	 */
	public static void setHeroes(ArrayList<Hero> Heroes) {
		Game.heroes = heroes;
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
		
		availableHeroes = new ArrayList<Hero>();
		heroes = new ArrayList<Hero>();
		zombies = new ArrayList<Zombie>();
		map = new Cell[15][15];
		
	}
	
	public static void loadHeroes(String filePath) throws Exception {
		
		// Reading Files: https://www.w3schools.com/java/java_files_read.asp
		
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
			
			
			availableHeroes.add(hero);
			
			
		}
		sc.close();
		
	}
	
	
	
	
}
