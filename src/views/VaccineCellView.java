package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VaccineCellView extends CellView {
	ImageView sprite;
	
//	public VaccineCellView () {
//		super();
//		SpriteAnimation spriteAnimation = new SpriteAnimation("./static/vaccine.png",6,1,1.0);
//		sprite = spriteAnimation.getSprite();
//		
//		super.setGraphic(sprite);
//			
//		}
	
	public VaccineCellView (boolean isVisible,ImageView tile) {
		super();
		SpriteAnimation spriteAnimation = new SpriteAnimation("./static/vaccine.png",6,1,1.0);
		sprite = spriteAnimation.getSprite();
		
		StackPane sp = new StackPane();
		
		sp.getChildren().addAll(tile,sprite);
		
		if (isVisible)
			super.setGraphic(sp);
		else {
			super.setGraphic(tile);
		}
			

		}
	
	public VaccineCellView (boolean isVisible) {
		super();
		SpriteAnimation spriteAnimation = new SpriteAnimation("./static/vaccine.png",6,1,1.0);
		sprite = spriteAnimation.getSprite();
		
		if (isVisible)
			super.setGraphic(sprite);
		else {
			this.getStyleClass().add("cell-invisible");
		}
			

		}
		
	
		
	}







