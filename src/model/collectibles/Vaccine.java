package model.collectibles;
import java.util.*;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Hero;

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
			vaccineInventory.remove(this);
			
		}
	}
}
