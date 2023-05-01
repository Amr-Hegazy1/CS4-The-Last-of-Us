package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Zombie extends Character{
	
	static int ZOMBIES_COUNT=0;
	
	public Zombie() {
		
		super("Zombie " + ++ZOMBIES_COUNT,40,10);
		

	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		if (this.getTarget() instanceof Zombie) {
			throw new InvalidTargetException("Zombies can't attack other zombies. Can only attack heroes");
		}
		super.attack();
	}
	
	
}
