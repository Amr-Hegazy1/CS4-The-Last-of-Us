package views;

import javafx.scene.control.*;

public class CharacterView {
	
	ProgressBar healthBar;

	public CharacterView() {
		
		this.healthBar = new ProgressBar();
	
		this.healthBar.setProgress(1);
		this.healthBar.getStyleClass().add("health-bar");
	}

	public ProgressBar getHealthBar() {
		return healthBar;
	}

	public void setHealth(double newHealth) {
		this.healthBar.setProgress(newHealth); 
		
	}
	
	

}
