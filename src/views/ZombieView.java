package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class ZombieView extends CharacterView {
	
	private ImageView sprite;
	private BorderPane layout = new BorderPane();
	
	public ZombieView() {
		super();
		
		
		SpriteAnimation bx = new SpriteAnimation("./static/zombieIdle.png",4,1,0.75);
		sprite = bx.getSprite();
		
		layout.setBottom(super.healthBar);
		layout.setCenter(sprite);
		
		
	
		
		
		
	}
	
	public BorderPane getLayout() {
		return layout;
	}
	
	
}
