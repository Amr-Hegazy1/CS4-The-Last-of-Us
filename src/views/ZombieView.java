package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ZombieView extends CharacterView {
ImageView imageView;
VBox vBox = new VBox();

public ZombieView() {
	super();
	
	Image image = new Image(getClass().getResourceAsStream("ZHurt.png"));
	
	imageView = new ImageView(image);
	
	vBox.getChildren().addAll(imageView,super.healthBar);
	

	
	
	
}

}
