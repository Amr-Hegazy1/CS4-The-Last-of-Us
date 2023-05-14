package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class VaccineCellView extends CellView {
	ImageView imageView;
	VBox vBox = new VBox();
	public VaccineCellView () {
		super();
		Image image = new Image (getClass().getResourceAsStream("vaccine.png"));
		imageView = new ImageView(image);
		vBox.getChildren().add(imageView);
		super.setGraphic(imageView);
			
		}
		
	}



