package views;

import model.characters.*;
import javafx.scene.layout.*;

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

		this.zombieView = new ZombieView();

		this.setGraphic(this.zombieView.getLayout());



	}

	public ZombieCellView(Zombie zombie,boolean isVisible) {
		super();
		this.zombieView = new ZombieView();
		if (isVisible)
			this.setGraphic(this.zombieView.getLayout());
		else
			this.setStyle("-fx-background-color:#000000");
		this.zombie = zombie;

	}


}