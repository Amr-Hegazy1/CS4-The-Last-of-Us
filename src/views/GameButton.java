package views;

import javafx.animation.TranslateTransition;
import javafx.scene.control.*;
import javafx.util.Duration;

public class GameButton extends Button {
	
	private boolean isActive;
	
	public GameButton() {
		super();
	}
	
	public GameButton(String text) {
		super(text);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
		
		if(isActive) {
			this.setOpacity(1);
		}else {
			this.setOpacity(0.5);
		}
	}
	
	public void vibrateSideways() {
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(50),this);
		tt.setByX(20f);
		tt.setByX(-5f);
		tt.setCycleCount(5);
		tt.setAutoReverse(true);
		tt.playFromStart();
		
	}
	
	
	

}
