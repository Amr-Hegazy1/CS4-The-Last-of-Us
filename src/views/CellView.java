package views;

import javafx.scene.control.*;
import model.characters.Hero;

public class CellView extends Button {
	
	private SpriteAnimation spriteAnimation;
	
	public CellView() {
		super();
		initialize();
		this.setPrefWidth(50);
		this.setPrefHeight(50);
		
	}
	
	
	public CellView(String text) {
		super(text);
		initialize();
		this.setPrefWidth(50);
		
		this.setPrefHeight(50);
	}
	
	private void initialize() {
        setOnAction(event -> {
            	
        	
        	if(this instanceof HeroCellView)
                
                Main.currentHero = ((HeroCellView) this).hero;
                
            
        });
    }
	
	public CellView(String imageLoc, int width,int height) {
		super();
		spriteAnimation = new SpriteAnimation(imageLoc,width,height);
		initialize();
		this.setGraphic(spriteAnimation.getSprite());
	}
	

}
