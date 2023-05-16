package views;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Controls extends VBox {
	
	public Controls() {
		Button cureBtn = new Button("cure");
		
		Button useSpecialBtn = new Button("use special");
		
		cureBtn.setOnAction(event -> {
			
			Main.currentHero.setTarget(Main.currentZombie);
			
			try {
				Main.currentHero.cure();
				Main.updateMap();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoAvailableResourcesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		useSpecialBtn.setOnAction(event -> {
			
			
			
			
				try {
					Main.currentHero.useSpecial();
				} catch (NoAvailableResourcesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.updateMap();
			
			
		});
		
		this.getChildren().addAll(cureBtn,useSpecialBtn);
		
	}
	
	

}
