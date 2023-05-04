package model.characters;

import engine.Game;
import exceptions.*;
import model.collectibles.Supply;
import model.world.*;



public class Explorer extends Hero {

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
		
	}


	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		
		
		
		
		int actionsAvailable = this.getActionsAvailable();
		actionsAvailable--;
		this.setActionsAvailable(actionsAvailable);
		
	}
	
	


	public  void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException{
		super.useSpecial();
			
			// set entire map to be visible
			
			Cell[][] map = Game.getMap();
			for(int i = 0 ; i < 15 ; i++)
				for(int j = 0 ; j < 15 ; j++)
					map[i][j].setVisible(true);
			
			Game.setMap(map);
			
	}
	}


