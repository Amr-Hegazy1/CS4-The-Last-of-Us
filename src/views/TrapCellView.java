package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

	public class TrapCellView extends CellView {
		ImageView sprite;
		
//		public TrapCellView() {
//			super();
//			SpriteAnimation spriteAnimation = new SpriteAnimation("./static/trap.png",14,1,1.0);
//			sprite = spriteAnimation.getSprite();
//			
//			super.setGraphic(sprite);
//		}
		
		public TrapCellView(boolean isVisible,ImageView tile) {
			super();
			SpriteAnimation spriteAnimation = new SpriteAnimation("./static/trap.png",14,1,1.0);
			sprite = spriteAnimation.getSprite();
			StackPane sp = new StackPane();
			
			sp.getChildren().addAll(tile,sprite);
			
			if (isVisible)
				super.setGraphic(sp);
			else {
				super.setGraphic(tile);
			}
		}
		
	}



