package views;

import java.io.File;

import engine.Game;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.stage.*;
import model.characters.*;
import model.collectibles.Vaccine;
import model.world.*;

public class Main extends Application {
	
	private static Stage primaryStage;
	
	private static Scene scene;
	
	private static Statistics gameplayStatistics = new Statistics();
	
	static Hero currentHero;
	static Zombie currentZombie;
	private static GridPane gridPane = new GridPane();
	
	
	private MediaPlayer mediaPlayer;
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		String path = "./static/openingVideo.mp4";  
		Media media = new Media(new File(getClass().getResource(path).getPath()).toURI().toString());  
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		
		
		
		
     
		
		Game.loadHeroes("test_heros.csv");
		
		Game.startGame(Game.availableHeroes.remove(0));
		
		primaryStage.setTitle("The Last Of Us - Legacy");
		
		
		
		
		
		
		
		gridPane.setAlignment(Pos.CENTER);
		
		
		
		
		
		
		

	
	
		
		
		
		scene = new Scene(root);
		
		scene.getStylesheets().add(getClass().getResource("./static/view.css").toExternalForm());
		
		primaryStage.setFullScreen(true);
		
		
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(() -> {
            switchToLoadingScreen();
        });  
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
		
	}
	
	public static int[] transform (int x , int y) {

		return new int[] {y,14-x};


	}
	
	public static void refresh() {
	
		gridPane.getChildren().clear();
		gameplayStatistics.updateStatistics();
		
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				
				boolean isVisible = Game.map[j][i].isVisible();
				int[] transform_cords = transform(j,i);
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
					
					Hero hero = (Hero) ((CharacterCell) Game.map[j][i] ).getCharacter();
					HeroCellView heroCellView = new HeroCellView(hero,isVisible); 
					
					HeroView heroView = heroCellView.getHeroView();
					heroView.setHealth(hero.getCurrentHp() / (double)hero.getMaxHp());
					
					
					
					gridPane.add(heroCellView, x,y);
				}else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Zombie) {
					Zombie zombie = (Zombie)((CharacterCell) Game.map[j][i] ).getCharacter();

					gridPane.add(new ZombieCellView(zombie,isVisible), x, y);
					
				}else
					gridPane.add(new CellView(isVisible), x, y);
				
			}
		}
		
		
	}
	
	public static void switchToGameplayScene() {
		BorderPane borderPane = new BorderPane();
		refresh();
		
		Controls controls = new Controls();
		
		
		borderPane.setCenter(gridPane);
		
		borderPane.setLeft(controls);
		borderPane.setRight(gameplayStatistics);
		
		scene.setRoot(borderPane);
		
		
		
	}
	
	public static void switchToSelectHeroScene() {
		BorderPane borderPane = new BorderPane();
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		Statistics loadingScreenStatistics = new Statistics();
		
		
		Game.availableHeroes.forEach(hero ->{
			
			Button heroBtn = new Button(hero.getName());
			heroBtn.setOnAction( event -> {
				System.out.println(event.getSource());
				switchToGameplayScene();
			} );
			
			heroBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
			        new EventHandler<MouseEvent>() {
		          @Override
		          public void handle(MouseEvent e) {
		        	  loadingScreenStatistics.setStatistics(hero.getName());
		        	  
		        	  
		          }
		        });

			
			hBox.getChildren().add(heroBtn);
		});
		
		
		borderPane.setCenter(hBox);
		borderPane.setLeft(loadingScreenStatistics);
		
		scene.setRoot(borderPane);
	}
	
	
	public static void switchToLoadingScreen() {
		
		String path = "C:/Users/hhegazy/Desktop/Game/src/views/static/loadingScreen.mp4";  
		Media media = new Media(new File(path).toURI().toString());  
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(() -> {
        	switchToSelectHeroScene();
        });  
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		scene.setRoot(root);
		
	}
	
	public void playStartGameVideo() {
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
