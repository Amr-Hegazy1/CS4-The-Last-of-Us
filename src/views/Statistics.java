package views;

import engine.Game;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.characters.*;

public class Statistics extends ScrollPane {
	
	private VBox vBox = new VBox(); 
	private Font goodTimingFont = Font.loadFont(getClass().getResourceAsStream("./static/goodtimingbd.otf"), 12);
	
	public Statistics () {
		super();
		
		vBox.getStyleClass().add("stats");
		
//		this.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMaxWidth(200);
		this.setMaxHeight(200);
		this.updateStatistics();
	}
	public  void updateStatistics(){
		vBox.getChildren().clear();
		
		for(int i=0 ; i< Game.heroes.size() ; i++ ) {
			Hero hero = Game.heroes.get(i);
			
			Label labelno = new Label("Hero: " + hero.getName());
			Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
			Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
			Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
//			Label labelsupplies = new Label( "Number of Supplies: "+ hero.getSupplyInventory().size());
//			Label labelVaccines = new Label( "Number of Vaccines: "+hero.getVaccineInventory().size());
			Label labelType = new Label( "Type: " + hero.getClass().getSimpleName());
			
			
			
			
			
			
			labelno.setFont(goodTimingFont);
			
			labelHp.setFont(goodTimingFont);
	
			labelAttackDmg.setFont(goodTimingFont);
			labelActionsAvailable.setFont(goodTimingFont);
//			labelsupplies.setFont(goodTimingFont);
//			labelVaccines.setFont(goodTimingFont);
			labelType.setFont(goodTimingFont);
			
			
			
			vBox.getChildren().addAll(labelno ,labelHp ,labelAttackDmg
					, labelActionsAvailable , labelType,new Label( "\n" ));
			
			
			this.setContent(vBox);
		}
	}
	
	public void setStatistics(String heroName) {
		vBox.getChildren().clear();
		  for(int i=0 ; i< Game.availableHeroes.size() ; i++ ) {
			  
				if(Game.availableHeroes.get(i).getName().equals(heroName)) {
					
					
					Hero hero = Game.availableHeroes.get(i);
					Label labelno = new Label("Hero: " + hero.getName());
					Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
					Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
					Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
					
					Label labelType = new Label( "Type: " + hero.getClass().getSimpleName());
					
					
					
					
					
					
					labelno.setFont(goodTimingFont);
					
					labelHp.setFont(goodTimingFont);
			
					labelAttackDmg.setFont(goodTimingFont);
					labelActionsAvailable.setFont(goodTimingFont);
				
					labelType.setFont(goodTimingFont);
			
			
					vBox.getChildren().addAll(labelno ,labelHp ,labelAttackDmg
							, labelActionsAvailable , labelType);
					
					this.setContent(vBox);
	
				}
		  }
	}
	
	public void setStatistics(Hero hero) {
		vBox.getChildren().clear();
		
		if (hero == null ) return;
		  
					
					
		Label labelno = new Label("Hero: " + hero.getName());
		Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
		Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
		Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
		Label labelsupplies = new Label( "Number of Supplies: "+ hero.getSupplyInventory().size());
		Label labelVaccines = new Label( "Number of Vaccines: "+hero.getVaccineInventory().size());
		
		Label labelType = new Label( "Type: " + hero.getClass().getSimpleName());

		
		
		labelno.setFont(goodTimingFont);
		
		labelHp.setFont(goodTimingFont);

		labelAttackDmg.setFont(goodTimingFont);
		labelActionsAvailable.setFont(goodTimingFont);
		labelsupplies.setFont(goodTimingFont);
		labelVaccines.setFont(goodTimingFont);
		labelType.setFont(goodTimingFont);

		vBox.getChildren().addAll(labelno ,labelHp ,labelAttackDmg
				, labelActionsAvailable,labelsupplies,labelVaccines , labelType);
		

		this.setContent(vBox);
		  
	}
	
	
	public String toString() {
		String out = "";
		
		for(int i = 0; i < vBox.getChildren().size(); i++) {
			Label l = (Label) vBox.getChildren().get(i);
			
			out += l.getText() + "\n";
		}
		
		return out;
	}
	
	
	
}
	
