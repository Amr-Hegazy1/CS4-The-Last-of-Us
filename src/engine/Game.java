package engine;


import java.util.*;
import java.awt.*;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.*;
import model.characters.Character;
import model.collectibles.*;


import model.world.*;

import java.awt.Point;
import java.io.*;


public class Game {
	
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();;
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();;
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();;
	public static Cell[][] map = new Cell[15][15];
	
	
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


//	public Game() {
//		
//		availableHeroes = new ArrayList<Hero>();
//		heroes = new ArrayList<Hero>();
//		zombies = new ArrayList<Zombie>();
////		map = new Cell[15][15];
//		
//		
//	}
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
	public static void startGame(Hero h) {
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				map[i][j] = new CharacterCell(null);
				
			}
		}
		
		
		
//		loadHeroes("Heroes.csv");
	    availableHeroes.remove(h);
	    h.setLocation(new Point(0,0));
		heroes.add(h);
		map[14][0]= new CharacterCell(h);
		for (int k=0;k<10;k++) {
			Point p = generateRandomLoaction();
			int locX = (int) p.getX();
			int locY = (int) p.getY();
			int[] transform_cords = transform(locX,locY);
			int x = transform_cords[0];
			int y = transform_cords[1];
			Zombie newZombie = new Zombie();
			newZombie.setLocation(p);
			map[x][y]=new CharacterCell(newZombie);
			zombies.add(newZombie);
		}
		for(int i=0;i<5;i++) {
			Point p = generateRandomLoaction();
			int locX = (int) p.getX();
			int locY = (int) p.getY();
			int[] transform_cords = transform(locX,locY);
			int x = transform_cords[0];
			int y = transform_cords[1];
			
			map[x][y]=new CollectibleCell(new Vaccine());
			 p = generateRandomLoaction();
			 locX = (int) p.getX();
			 locY = (int) p.getY();
			 transform_cords = transform(locX,locY);
			 x = transform_cords[0];
			 y = transform_cords[1];
			 
			map[x][y]=new CollectibleCell(new Supply());
			 p= generateRandomLoaction();
			 locX = (int) p.getX();
			 locY = (int) p.getY();
			 transform_cords = transform(locX,locY);
			 x = transform_cords[0];
			 y = transform_cords[1];
			map[x][y]=new TrapCell();
			
		}
		
	}
	
	public static boolean checkWin() {
		int heroalive = heroes.size();
		if(heroalive < 5)
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
	public static int[] transform (int x , int y) {
		
		return new int[] {14-y,x};
		
		
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
		
		int[] transform_cords = transform(newZombieLocX,newZombieLocY);
		int newZombieX = transform_cords[0];
		int newZombieY = transform_cords[1];
		Zombie newZombie = new Zombie();
		newZombie.setLocation(newZombieLoc);
		map[newZombieX][newZombieY] = new CharacterCell(newZombie);
		zombies.add(newZombie);
		
		
	}
	
	public static boolean checkGameOver() {
		
		if (heroes.isEmpty())
			return true;
		
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
			
//			map[x][y+r].setVisible(true);
//			map[x+u][y+r].setVisible(true);
//			map[x-d][y+r].setVisible(true);
//			map[x+u][y].setVisible(true);
//			map[x-d][y].setVisible(true);
//			map[x][y-l].setVisible(true);
//			map[x+u][y-l].setVisible(true);
//			map[x-d][y-l].setVisible(true);
	
		}

	// -----------------------------METHODS FOR TESTING PURPOSES ONLY-----------------------------------------------
	
	
		public static void main(String[] args) {
			System.out.println("
					▀█▀ █░█ █▀▀   █░░ ▄▀█ █▀ ▀█▀   █▀█ █▀▀   █░█ █▀   ▄▄   █░░ █▀▀ █▀▀ ▄▀█ █▀▀ █▄█
					░█░ █▀█ ██▄   █▄▄ █▀█ ▄█ ░█░   █▄█ █▀░   █▄█ ▄█   ░░   █▄▄ ██▄ █▄█ █▀█ █▄▄ ░█░");
			
			System.out.println("This is only for testing purposes.");
			System.out.println("Here are a few regulations:");
			System.out.println("* You are able to see everything ont the map inorder to test");
//			System.out.println("* Zombie Names will appear as Z & a number. eg Z1,Z2,Z3, etc.");
			System.out.println("* Empty cells are marked as E, Supplies as S , Vaccines as V & Traps as T");
//			System.out.println(" However when asked which zombie you want to attack/heal you have to write Zombie 1. TAKE CARE OF SPELLING & CASING");
			System.out.println("* SPELLING & CASING ARE VERY IMPORTANT");
			System.out.println("* Exceptions aren't handled so you can see which type of exception was thrown");
			
			System.out.println("ENJOY THE GAME!");
			try {
				Scanner sc = new Scanner(System.in);
				loadHeroes("test_heros.csv");
				
				startGame(availableHeroes.remove(0));
				
				while(!checkWin() && !checkGameOver()) {
					System.out.println("-----------------------------------------------------------------------------------------------------");
					displayHeroAndZombieStats();
					displayMap();
					System.out.println();
					
				
					System.out.print("Select a hero: ");
					String heroStr = sc.nextLine();
					System.out.println();
					
					Hero hero = findHero(heroStr);
					
					
					System.out.print("What do you want to do (move,cure,attack,use special): ");
					String action = sc.nextLine();
					System.out.println();
					
					if(action.equals("move")) {
						System.out.print("Enter Direction(up,down,left,right): ");
						String direction = sc.nextLine();
						System.out.println();
						
						switch (direction) {
							case "right" :  hero.move(Direction.RIGHT);break;
							case "left" :  hero.move(Direction.LEFT);break;
							case "up" :  hero.move(Direction.UP);break;
							case "down" :  hero.move(Direction.DOWN);break;
						}
						
					}else if(action.equals("cure")) {
						System.out.print("Select Zombie: ");
						String zombieName = sc.nextLine();
						Zombie zombie = findZombie(zombieName);
						hero.setTarget(zombie);
						hero.cure();
					}else if(action.equals("attack")) {
						System.out.print("Select Zombie: ");
						String zombieName = sc.nextLine();
						Zombie zombie = findZombie(zombieName);
						hero.setTarget(zombie);
						hero.attack();
					}else if(action.equals("use special")) {
						if(hero instanceof Medic) {
							System.out.print("Select Hero: ");
							String heroName = sc.nextLine();
							Hero heroTarget = findHero(heroName);
							hero.setTarget(heroTarget);
						}
						hero.useSpecial();
					}
					System.out.print("End Turn?(y/n): ");
					String endTurn = sc.nextLine();
					System.out.println();
					
					if(endTurn.equals("y")) endTurn();
					
					
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		public static void displayMap() {
			for (int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					if ( map[i][j] instanceof CharacterCell ) {
						Character character = ((CharacterCell) map[i][j]).getCharacter();
						System.out.print((character == null) ? "E" : character.getName());
				
					}else if (map[i][j] instanceof CollectibleCell) {
						Collectible collectible = ((CollectibleCell) map[i][j]).getCollectible();
						System.out.print((collectible instanceof Supply) ? "S" : "V");
					}else if (map[i][j] instanceof TrapCell) {
						System.out.print("T");
					}
					System.out.print(", ");
					
				}
				System.out.println();
			}
		}
		
		public static Hero findHero(String heroStr) {
			for(int i = 0;i<heroes.size();i++)
				if(heroes.get(i).getName().equals(heroStr)) {
					return heroes.get(i);
				}
			
			return null;
			
		}
		
		public static Zombie findZombie(String zombieName) {
			for(int i = 0;i<zombies.size();i++)
				if(zombies.get(i).getName().equals(zombieName)) {
					return zombies.get(i);
				}
			
			return null;
			
		}
		
	public static void displayHeroAndZombieStats() {
		System.out.println("Heros:");
		heroes.forEach((hero) -> {
			System.out.println("  " + hero.getName() + ":");
			System.out.println("    Current Hp: " + hero.getCurrentHp());
			System.out.println("    Actions Available: " + hero.getActionsAvailable());
			System.out.println("    Attack Damage: " + hero.getAttackDmg());
			System.out.println("    Special Action: " + hero.isSpecialAction());
			System.out.println("    Vaccine Count: " + hero.getVaccineInventory().size());
			System.out.println("    Supply Count: " + hero.getSupplyInventory().size());
			
		});
		
	}
		
}
	
	
	
	
	

