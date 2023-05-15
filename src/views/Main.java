package views;

import engine.Game;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.characters.*;
import model.collectibles.Vaccine;
import model.world.*;

public class Main extends Application {

	
	public void start(Stage primaryStage) throws Exception {
		
		Game.loadHeroes("test_heros.csv");
		
		Game.startGame(Game.availableHeroes.remove(0));
		
		primaryStage.setTitle("The Last Of Us - Legacy");
		
		BorderPane borderPane = new BorderPane();
		
		GridPane gridPane = new GridPane();
		
		gridPane.setAlignment(Pos.CENTER);
		
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(Game.map[i][j] instanceof CollectibleCell)
					if(((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Vaccine)
						gridPane.add(new VaccineCellView(), i, j);
					else
						gridPane.add(new SupplyCellView(), i, j);
				
				
				else if(Game.map[i][j] instanceof TrapCell)
					gridPane.add(new TrapCellView(), i, j);
				
				else if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j] ).getCharacter() instanceof Hero)
					gridPane.add(new HeroCellView(), i, j);
				else if(Game.map[i][j] instanceof CharacterCell && ((CharacterCell) Game.map[i][j] ).getCharacter() instanceof Zombie)
					gridPane.add(new ZombieCellView(), i, j);
				else
					gridPane.add(new CellView(), i, j);
				
			}
		}
		
		borderPane.setCenter(gridPane);
		
	
	
		
		Scene scene = new Scene(borderPane);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
