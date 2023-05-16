package views;

import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.*;

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


	public Node getLayout() {
		
		return layout;
	}

}
