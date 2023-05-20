package views;

import model.characters.*;

public class ZombieCellView extends CharacterCellView{
Zombie zombie;
ZombieView zombieView;
public ZombieCellView() {
	super();
	this.zombieView = new ZombieView();
	
	this.setGraphic(this.zombieView.getLayout());
	
}
public ZombieCellView(Zombie zombie, ZombieView zombieView) {
	super();
	this.zombie = zombie;
	this.zombieView = zombieView;
}
public ZombieCellView(String text) {
	super(text);
	
}

public ZombieCellView(Zombie zombie) {
	super();
	this.zombie = zombie;
	
}

	
}
