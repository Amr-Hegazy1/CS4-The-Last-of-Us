package model.characters;

import java.awt.*;


import engine.Game;
import exceptions.*;

import model.world.*;

public class Zombie extends Character{
	
	static int ZOMBIES_COUNT=0;
	
	public Zombie() {
		
		super("Zombie " + ++ZOMBIES_COUNT,40,10);
		

	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (this.getTarget() instanceof Zombie) {
			throw new InvalidTargetException("Zombies can't attack other zombies. Can only attack heroes");
		}
		
		
		
		Point loc = this.getLocation();
		int locX = (int) loc.getX();
		int locY = (int) loc.getY();
		int[] transform_cord = Game.transform(locX, locY);
		int x = transform_cord[0];
		int y = transform_cord[1];
		
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
		
		if(this.getTarget() != null)
		
			super.attack();
	}
	
	public void reset() {
		this.setTarget(null);
	}
	
}
