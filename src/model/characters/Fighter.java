package model.characters;





import exceptions.*;


import model.collectibles.Supply;



public class Fighter extends Hero {

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}


	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		
		
		super.attack();
		if(!this.isSpecialAction()) {
			int actionsAvailable = this.getActionsAvailable();
			actionsAvailable--;
			this.setActionsAvailable(actionsAvailable);
		}
		
	}
	


	
		//this.attack();


}

