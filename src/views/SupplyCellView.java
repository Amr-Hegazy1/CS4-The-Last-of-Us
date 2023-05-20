package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SupplyCellView extends CellView {
	ImageView sprite;
	
	public SupplyCellView () {
		super();
		SpriteAnimation bx = new SpriteAnimation("./static/supply.png",9,1,1.0);
		sprite = bx.getSprite();
		
		super.setGraphic(sprite);
				
			}
			
		}



