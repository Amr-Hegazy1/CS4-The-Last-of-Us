package views;

import javafx.scene.image.*;
import javafx.scene.layout.*;

public class HeroView extends CharacterView {
	
	ImageView imageView;
	
	VBox vBox = new VBox();
	
	
	public HeroView() {
		super();
		
		Image image = new Image(getClass().getResourceAsStream("Hurt.png"));
		
		imageView = new ImageView(image);
		
		vBox.getChildren().addAll(imageView,super.healthBar);
		
	
		
		
		
	}

}
