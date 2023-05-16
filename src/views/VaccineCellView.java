package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VaccineCellView extends CellView {
	ImageView sprite;
	
	public VaccineCellView () {
		super();
		SpriteAnimation spriteAnimation = new SpriteAnimation("./static/vaccine.png",6,1,1.0);
		sprite = spriteAnimation.getSprite();
		
		super.setGraphic(sprite);
			
		}
		
	}



