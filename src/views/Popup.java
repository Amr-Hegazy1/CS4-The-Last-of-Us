package views;

import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Popup extends StackPane {
	
	Label popupText;
	
	public Popup() {
		Font arcadeFont = Font.loadFont(getClass().getResourceAsStream("./static/ARCADECLASSIC.TTF"), 50);
		
		popupText = new Label("An Unknown Error Has Occured");
		popupText.setFont(arcadeFont);
		
		Image popupImage = new Image(getClass().getResourceAsStream("./static/popupwindow.png"));
		ImageView popupImageView = new ImageView(popupImage);
		popupImageView.setFitWidth(Main.getPrimaryStage().getWidth() * 0.80);
		popupImageView.setFitHeight(Main.getPrimaryStage().getHeight() * 0.80);
		
		this.getChildren().addAll(popupImageView,popupText);
		
		this.setOnMouseClicked(event ->{
			((StackPane)Main.getScene().getRoot()).getChildren().remove(1);
		});
		
	}

	

	public void setPopupText(String text) {
		
		this.getChildren().remove(1);
		this.popupText.setText(text);
		this.getChildren().add(popupText);
	}
	
	

}
