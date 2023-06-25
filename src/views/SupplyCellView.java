package views;

import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SupplyCellView extends CellView {
	ImageView sprite;
	

	
	public SupplyCellView (boolean isVisible,ImageView tile) {
		super();
		SpriteAnimation bx = new SpriteAnimation("./static/supply.png",9,1,1.0);
		sprite = bx.getSprite();
		StackPane sp = new StackPane();
		
		sp.getChildren().addAll(tile,sprite);
		
		if (isVisible)
			super.setGraphic(sp);
		else {
			super.setGraphic(tile);
		}

	}
	
	public SupplyCellView (boolean isVisible) {
		super();
		SpriteAnimation bx = new SpriteAnimation("./static/supply.png",9,1,1.0);
		sprite = bx.getSprite();
		
		
		
		
		if (isVisible)
			super.setGraphic(sprite);
		else {
			this.getStyleClass().add("cell-invisible");
		}

	}

}



