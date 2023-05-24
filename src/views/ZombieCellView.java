package views;

import model.characters.*;
import javafx.scene.image.ImageView;
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

	public ZombieCellView(Zombie zombie,boolean isVisible,ImageView tile) {
		super();
		this.zombieView = new ZombieView();
		
		StackPane sp = new StackPane();
		sp.getChildren().addAll(tile,this.zombieView.getLayout());
		if (isVisible)
			this.setGraphic(sp);
		else
			super.setGraphic(tile);
			
		this.zombie = zombie;

	}


}