package views;

import engine.Game;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import model.characters.*;

public class Statistics extends VBox {
	public Statistics () {
		super();
		this.updateStatistics();
	}
	public  void updateStatistics(){
		getChildren().clear();
		
		for(int i=0 ; i< Game.heroes.size() ; i++ ) {
			Hero hero = Game.heroes.get(i);
			
			Label labelno = new Label("Hero: " + hero.getName());
			Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
			Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
			Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
			Label labelsupplies = new Label( "Number of Supplies: "+ hero.getSupplyInventory().size());
			Label labelVaccines = new Label( "Number of Vaccines: "+hero.getVaccineInventory().size());
			Label labelType = new Label( "Type: "+ hero.getClass().getSimpleName());
			
			Font goodTimingFont = Font.loadFont(getClass().getResourceAsStream("./static/goodtimingbd.otf"), 12);
			
			
			
			
			labelno.setFont(goodTimingFont);
			
			labelHp.setFont(goodTimingFont);
	
			labelAttackDmg.setFont(goodTimingFont);
			labelActionsAvailable.setFont(goodTimingFont);
			labelsupplies.setFont(goodTimingFont);
			labelVaccines.setFont(goodTimingFont);
			labelType.setFont(goodTimingFont);
			
			
			
			getChildren().addAll(labelno ,labelHp ,labelAttackDmg
					, labelActionsAvailable ,labelsupplies
					, labelVaccines , labelType,new Label( "\n" ));
	
		}
	}
	
	public void setStatistics(String heroName) {
		getChildren().clear();
		  for(int i=0 ; i< Game.availableHeroes.size() ; i++ ) {
			  
				if(Game.availableHeroes.get(i).getName().equals(heroName)) {
					
					
					Hero hero = Game.availableHeroes.get(i);
					Label labelno = new Label("Hero: " + hero.getName());
					Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
					Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
					Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
					
					Label labelType = new Label( "Type: " + hero.getClass().getSimpleName());
					
					Font goodTimingFont = Font.loadFont(getClass().getResourceAsStream("./static/goodtimingbd.otf"), 12);
					
					
					
					
					labelno.setFont(goodTimingFont);
					
					labelHp.setFont(goodTimingFont);
			
					labelAttackDmg.setFont(goodTimingFont);
					labelActionsAvailable.setFont(goodTimingFont);
				
					labelType.setFont(goodTimingFont);
			
			
					getChildren().addAll(labelno ,labelHp ,labelAttackDmg
							, labelActionsAvailable , labelType);
	
				}
		  }
	}
	
	public void setStatistics(Hero hero) {
		getChildren().clear();
		  
					
					
		Label labelno = new Label("Hero: " + hero.getName());
		Label labelHp = new Label( "Current Hp: " + hero.getCurrentHp());
		Label labelAttackDmg = new Label( "Attacking Damage: " + hero.getAttackDmg());
		Label labelActionsAvailable = new Label( "ActionPoints: " + hero.getActionsAvailable());
		
		Label labelType = new Label( "Type: " + hero.getClass().getSimpleName());



		getChildren().addAll(labelno ,labelHp ,labelAttackDmg
				, labelActionsAvailable , labelType);
	
				
		  
	}
	
	
	public String toString() {
		String out = "";
		
		for(int i = 0; i < getChildren().size(); i++) {
			Label l = (Label) getChildren().get(i);
			
			out += l.getText() + "\n";
		}
		
		return out;
	}
	
}
	
