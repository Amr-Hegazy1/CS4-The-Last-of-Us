package model.collectibles;
import java.util.*;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	public void pickUp(Hero h) {
		ArrayList<Vaccine> vaccineInventory=h.getVaccineInventory();
		vaccineInventory.add(new Vaccine());
	}
	public void use(Hero h) throws NoAvailableResourcesException {
		ArrayList<Vaccine> vaccineInventory=h.getVaccineInventory();
		if (vaccineInventory.size()==0) {
			throw   new NoAvailableResourcesException("Sorry, No Vaccines Are Available");
		}
	}
}
