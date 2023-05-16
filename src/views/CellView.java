package views;

import javafx.scene.control.*;

public class CellView extends Button {
	
	private SpriteAnimation spriteAnimation;
	
	
	public CellView() {
		super();
		initialize();
		this.setPrefWidth(50);
		this.setPrefHeight(50);
	}
	
	public CellView(boolean isVisible) {
		super();
		initialize();
		this.setPrefWidth(50);
		this.setPrefHeight(50);
		if(!isVisible)
			this.setStyle("-fx-background-color:#000000");
		
		
	}
	
	
	public CellView(String text) {
		super(text);
		initialize();
		this.setPrefWidth(50);
		
		this.setPrefHeight(50);
	}
	
	protected void initialize() {
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
