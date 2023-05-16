package model.characters;

import java.awt.*;
import java.util.Arrays;

import engine.Game;
import exceptions.*;

import model.world.*;

public class Zombie extends Character{
	
	static int ZOMBIES_COUNT=0;
	
	public Zombie() {
		
		super("Zombie " + ++ZOMBIES_COUNT,40,10);
		

	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
//		if (this.getTarget() instanceof Zombie) {
//			throw new InvalidTargetException("Zombies can't attack other zombies. Can only attack heroes");
//		}
		
		
		
		Point loc = this.getLocation();
		int x = (int) loc.getY();
		int y = (int) loc.getX();
		
		Cell[][] map = Game.map;
		
		// look for optimizations
		
		
		
		
		if (x > 0 && map[x-1][y] instanceof CharacterCell && ((CharacterCell)(map[x-1][y])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x-1][y])).getCharacter());
		else if (x < 14 && map[x+1][y] instanceof CharacterCell && ((CharacterCell)(map[x+1][y])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x+1][y])).getCharacter());
		else if (y < 14 && map[x][y+1] instanceof CharacterCell && ((CharacterCell)(map[x][y+1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x][y+1])).getCharacter());
		else if (y > 0 && map[x][y-1] instanceof CharacterCell && ((CharacterCell)(map[x][y-1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x][y-1])).getCharacter());
		else if (x < 14 && y < 14 && map[x+1][y+1] instanceof CharacterCell && ((CharacterCell)(map[x+1][y+1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x+1][y+1])).getCharacter());
		else if (x < 14 && y > 0 && map[x+1][y-1] instanceof CharacterCell && ((CharacterCell)(map[x+1][y-1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x+1][y-1])).getCharacter());
		else if (x > 0 && y < 14 && map[x-1][y+1] instanceof CharacterCell && ((CharacterCell)(map[x-1][y+1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x-1][y+1])).getCharacter());
		else if (x > 0 && y > 0 && map[x-1][y-1] instanceof CharacterCell && ((CharacterCell)(map[x-1][y-1])).getCharacter() instanceof Hero)
			this.setTarget(((CharacterCell)(map[x-1][y-1])).getCharacter());
		else
			this.setTarget(null);
		
//		System.out.println(this.getLocation());
//		System.out.println((this.getTarget() != null) ? this.getTarget().getLocation() : null );
	
		
		if(this.getTarget() != null) {
			
			super.attack();
		}
	}
	
	
}
