package views;


import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class HeroView extends CharacterView {
	
	private ImageView sprite;
	private BorderPane layout = new BorderPane();
	
	
	public HeroView() {
		super();
		
		
		
		SpriteAnimation bx = new SpriteAnimation("./static/heroIdle.png",4,1);
		sprite = bx.getSprite();
		
		layout.setBottom(super.healthBar);
		layout.setCenter(sprite);
		
	
		
		
		
	}


	public BorderPane getLayout() {
		
		return layout;
	}


	public ImageView getSprite() {
		return sprite;
	}


	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
		
	}


	public void setLayout(BorderPane layout) {
		this.layout = layout;
	}

}
