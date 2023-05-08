package model.collectibles;
import java.awt.Point;
import java.util.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Hero;
import model.world.Cell;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	public void pickUp(Hero h) {
		
		ArrayList<Vaccine> vaccineInventory=h.getVaccineInventory();
		vaccineInventory.add(this);
		
	}
	public void use(Hero h) throws NoAvailableResourcesException {
		ArrayList<Vaccine> vaccineInventory=h.getVaccineInventory();
		if (vaccineInventory.size() == 0) {
			throw   new NoAvailableResourcesException("Sorry, No Vaccines Are Available");
		}else {
//			h.cure();
			
			Character z = h.getTarget();
			
			h.setActionsAvailable(h.getActionsAvailable()-1);
			Point p = z.getLocation();
			
			int xTarget = (int) p.getX();
			int yTarget = (int) p.getY();
			//	this.setActionsAvailable(actionsAvailable);
			//Point p =  z.getLocation();
			//int locX = (int) p.getX();
			//int locY = (int) p.getY();
			//int[] transform_cords = Game.transform(locX, locY);
			
			//int	x = transform_cords[0];
		    //int y= transform_cords[1];
			Cell c[][] = Game.getMap();
			Hero heroToBeAdded = Game.getAvailableHeroes().remove(0);
			//z.setLocation(null);
			heroToBeAdded.setLocation(p);
		    c[xTarget][yTarget] = new CharacterCell(heroToBeAdded); 
			Game.zombies.remove(z);
			Game.heroes.add(heroToBeAdded);
			Game.setMap(c);
			
			vaccineInventory.remove(this);
			
		}
	}
}
