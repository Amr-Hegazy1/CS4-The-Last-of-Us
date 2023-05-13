package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SupplyCellView extends CellView {
	ImageView imageView;
	VBox vBox = new VBox();
	public SupplyCellView () {
		super();
		Image image = new Image (getClass().getResourceAsStream("supply.png"));
		imageView = new ImageView(image);
		vBox.getChildren().add(imageView);
		super.setGraphic(imageView);
				
			}
			
		}



