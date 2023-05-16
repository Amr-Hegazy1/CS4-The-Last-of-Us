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
	
	
	static Hero currentHero;
	
	static Zombie currentZombie;
	
	static GridPane gridPane = new GridPane();

	
	public void start(Stage primaryStage) throws Exception {
		
		Game.loadHeroes("test_MEDS.csv");
		
		Game.startGame(Game.availableHeroes.remove(0));
		
		primaryStage.setTitle("The Last Of Us - Legacy");
		
		BorderPane borderPane = new BorderPane();
		
		
		
		gridPane.setAlignment(Pos.CENTER);
		
		updateMap();
		
		Controls controls = new Controls();
		
		borderPane.setCenter(gridPane);
		
		borderPane.setLeft(controls);
	
	
		
		Scene scene = new Scene(borderPane);
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static int[] transform (int x , int y) {
		
		return new int[] {y,14-x};
		
		
	}
	
	public static void updateMap() {
		
		gridPane.getChildren().clear();
		
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				boolean isVisible = Game.map[j][i].isVisible();
				int[] transform_cords = transform(i,j);
				int x = transform_cords[0];
				int y = transform_cords[1];
				
				if(Game.map[j][i] instanceof CollectibleCell)
					if(((CollectibleCell)Game.map[j][i]).getCollectible() instanceof Vaccine)
						gridPane.add(new VaccineCellView(isVisible), x, y);
					else
						gridPane.add(new SupplyCellView(isVisible), x, y);
				
				
				else if(Game.map[j][i] instanceof TrapCell)
					gridPane.add(new TrapCellView(isVisible), x, y);
				
				else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Hero) {
					
					Hero hero = (Hero)((CharacterCell) Game.map[j][i] ).getCharacter();
					
					gridPane.add(new HeroCellView(hero,isVisible), x, y);
				}else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Zombie) {
					Zombie zombie = (Zombie)((CharacterCell) Game.map[j][i] ).getCharacter();
					
					gridPane.add(new ZombieCellView(zombie,isVisible), x, y);
				}else
					gridPane.add(new CellView(isVisible), x, y);
				
			}
		}
	}

}
