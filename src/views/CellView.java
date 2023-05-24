package views;

import javafx.scene.control.*;
import model.characters.Hero;
import javafx.scene.image.*;

public class CellView extends Button {
	
	private SpriteAnimation spriteAnimation;
	
	public CellView() {
		super();
		initialize();
		this.setPrefWidth(40);
		this.setPrefHeight(60);
		getStyleClass().add("cell");
		
		
	}
	
	public CellView(boolean isVisible) {
		this();
		if(!isVisible) {
			getStyleClass().add("cell");
			
		}else {
			
		}

	}
	
	public CellView(boolean isVisible,ImageView tile) {
		this();
		if(!isVisible) {
			getStyleClass().add("cell");
			
		}else {
			
		}
		this.setGraphic(tile);

	}

	
	
	public CellView(String text) {
		super(text);
		initialize();
		this.setPrefWidth(40);
		
		this.setPrefHeight(60);
	}
	
	public CellView(ImageView tile) {
		super();
		initialize();
		this.setPrefWidth(50);
		
		this.setPrefHeight(50);
		
		this.setGraphic(tile);
	}
	
	private void initialize() {
        setOnAction(event -> {
            	
        	
        	if(this instanceof HeroCellView) {
        		Main.currentHero = ((HeroCellView) this).hero;
        		Main.refresh();
        	}

        	if(this instanceof ZombieCellView) {
        		Main.currentZombie = ((ZombieCellView) this).zombie;
        		Main.refresh();
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
