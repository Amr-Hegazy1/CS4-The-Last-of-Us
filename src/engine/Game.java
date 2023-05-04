package engine;


import java.util.*;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.collectibles.Collectible;
import model.collectibles.Vaccine;
import model.world.*;

import java.awt.Point;
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
	public static void setHeroes(ArrayList<Hero> heroes) {
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
//		map = new Cell[15][15];
		
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
	public static int[] transform (int x , int y) {
		
		return new int[] {14-y,x};
		
		
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
	
	public static void endTurn() throws InvalidTargetException,NotEnoughActionsException {
		Zombie zombie;
		Hero hero;
		
		// allow zombies to attack adjacent heroes
		
		for( int i = 0; i < zombies.size(); i++ ) {
			zombie = zombies.get(i);
			zombie.attack();
		}
		
		
		// reset map visibility
		for( int i = 0; i < 15 ; i++ ) 
			for ( int j = 0; j < 15; j++ )
				map[i][j].setVisible(false);
		
		// set visibility around heroes only
		
		for ( int i = 0; i < heroes.size(); i++ ) {
			hero = heroes.get(i);
			setVisibility(hero.getLocation());
			hero.reset();
		}
		
		// spawn new zombie
		
		Point newZombieLoc = generateRandomLoaction();
		int newZombieLocX = (int) newZombieLoc.getX();
		int newZombieLocY = (int) newZombieLoc.getY();
		Zombie newZombie = new Zombie();
		map[newZombieLocX][newZombieLocY] = new CharacterCell(newZombie);
		zombies.add(newZombie);
		
	}
	
	public static boolean checkGameOver() {
		
		int totalVaccines = 0;
		Hero hero;
		
		// count vaccines with heroes
		for( int i = 0; i < heroes.size();i++ ){
			hero = heroes.get(i);
			totalVaccines += hero.getVaccineInventory().size();
		}
		
		
		// count number of vaccines left on map
		
		for ( int i = 0; i < 15; i++ )
			for ( int j = 0; j < 15; j++ )
				if ( map[i][j] instanceof CollectibleCell && ( (CollectibleCell) map[i][j] ).getCollectible() instanceof Vaccine )
					totalVaccines++;
		
		// lose condition
		
		return heroes.size() + totalVaccines < 5;
	}
	


	public static void setVisibility(Point loc) {
		
		int locX = (int) loc.getX();
		int locY = (int) loc.getY();
		int[] transform_cords = Game.transform(locX, locY);
		int x = transform_cords[0];
		int y = transform_cords[1];
		
		
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

	
//	public static void main(String[] args) {
//		int[] a = transform(0,2);
//		System.out.println(Arrays.toString(a));
//	}
	
		
}
	
	
	
	
	

