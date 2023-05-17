package views;

import javafx.scene.control.*;

public class CharacterView {
	
	ProgressBar healthBar;

	public CharacterView() {
		
		this.healthBar = new ProgressBar();
	
		this.healthBar.setProgress(100);
		this.healthBar.setStyle("-fx-accent : chartreuse;-fx-border-radius : 100px");
		
	}

	public ProgressBar getHealthBar() {
		return healthBar;
	}

	public void setHealth(int newHealth) {
		this.healthBar.setProgress(newHealth); 
//		System.out.println(newHealth);
	}
	
	

}
