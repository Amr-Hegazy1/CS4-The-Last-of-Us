package views;

import javafx.scene.control.*;
import model.characters.Hero;
import javafx.scene.image.*;

public class CellView extends Button {
	
	private SpriteAnimation spriteAnimation;
	
	public CellView() {
		super();
		initialize();
		this.setPrefWidth(50);
		this.setPrefHeight(50);
		
	}
	
	public CellView(boolean isVisible) {
		this();
		if(!isVisible)
			this.setStyle("-fx-background-color:#000000;-fx-background-radius:0px");


	}

	
	
	public CellView(String text) {
		super(text);
		initialize();
		this.setPrefWidth(50);
		
		this.setPrefHeight(50);
	}
	
	private void initialize() {
        setOnAction(event -> {
            	
        	
        	if(this instanceof HeroCellView) {
        		Main.currentHero = ((HeroCellView) this).hero;
        	}

        	if(this instanceof ZombieCellView) {
        		Main.currentZombie = ((ZombieCellView) this).zombie;
        	}
        });
	}
	
	public CellView(String imageLoc, int width,int height) {
		super();
		spriteAnimation = new SpriteAnimation(imageLoc,width,height);
		initialize();
		this.setGraphic(spriteAnimation.getSprite());
	}
	

}
