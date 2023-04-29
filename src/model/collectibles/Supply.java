package model.collectibles;
import java.util.*;

import exceptions.NoAvailableResourcesException;
import model.characters.Hero;

public class Supply implements Collectible {

	public Supply() {
		
		
		
	}
public void pickUp(Hero h) {
	ArrayList<Supply> supplyInventory= h.getSupplyInventory();
	supplyInventory.add(new Supply());
	
	
}

public void use(Hero h) throws NoAvailableResourcesException {
	ArrayList<Supply> supplyInventory= h.getSupplyInventory();
    if (supplyInventory.size()==0) {
    	throw new NoAvailableResourcesException("Sorry, No Supplies Are Available");
    }
	
}

}
