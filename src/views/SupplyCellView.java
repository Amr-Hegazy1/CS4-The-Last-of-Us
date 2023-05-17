package views;

import javafx.scene.image.*;

import javafx.scene.layout.VBox;

public class SupplyCellView extends CellView {
	ImageView sprite;
	

	
	public SupplyCellView (boolean isVisible) {
		super();
		SpriteAnimation bx = new SpriteAnimation("./static/supply.png",9,1,1.0);
		sprite = bx.getSprite();
		
		if (isVisible)
			super.setGraphic(sprite);
		else
			this.setStyle("-fx-background-color:#000000");

	}

}



