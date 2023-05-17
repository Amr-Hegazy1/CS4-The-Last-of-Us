package views;

import javafx.scene.control.*;

public class CharacterView {
	
	ProgressBar healthBar;

	public CharacterView() {
		
		this.healthBar = new ProgressBar();
	
		this.healthBar.setProgress(1);
		this.healthBar.setStyle("-fx-accent : chartreuse;-fx-background-insets: 0;;-fx-background-radius : 100px;-fx-padding: 1;");
		
	}

	public ProgressBar getHealthBar() {
		return healthBar;
	}

	public void setHealth(double newHealth) {
		this.healthBar.setProgress(newHealth); 
		
	}
	
	

}
