package engine;


import java.util.*;
import java.awt.*;

import model.characters.*;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
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
	public static void startGame(Hero h) throws Exception {
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				map[i][j] =new CharacterCell(null);
				
			}
		}
		loadHeroes("Heroes.csv");
	    availableHeroes.remove(h);
		heroes.add(h);
		map[14][0]= new CharacterCell(h);
		for (int k=0;k<10;k++) {
			Point p= generateRandomLoaction();
			int x=(int) p.getX();
			int  y=(int) p.getY();
			map[x][y]=new CharacterCell(new Zombie());
		}
		for(int i=0;i<5;i++) {
			Point p= generateRandomLoaction();
			int x=(int) p.getX();
			int  y=(int) p.getY();
			map[x][y]=new CollectibleCell(new Vaccine());
			 p= generateRandomLoaction();
			 x=(int) p.getX();
			  y=(int) p.getY();
			map[x][y]=new CollectibleCell(new Supply());
			 p= generateRandomLoaction();
			 x=(int) p.getX();
			  y=(int) p.getY();
			map[x][y]=new TrapCell();
			
		}
	}
	
	public static boolean checkWin() {
		int heroalive = heroes.size();
		if(heroalive <5)
			return false;
		else {
			for(int i=0 ; i<heroalive ;i++) {
				Hero h = heroes.get(i);
				if(h.getVaccineInventory()!=null)
					return false;
				for(int k = 0 ; k < 15 ; k++) 
					for(int j = 0 ; j < 15 ; j++) {
						if(map[k][j] instanceof CollectibleCell) {
							CollectibleCell c = (CollectibleCell) map[k][j];
							if(c.getCollectible() instanceof Vaccine)
								return false;
						}
					}
			
		} 
			return true;
		}
		
		 
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
