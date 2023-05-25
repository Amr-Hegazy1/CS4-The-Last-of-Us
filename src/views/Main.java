package views;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import engine.Game;
import exceptions.*;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import model.characters.*;
import model.collectibles.Vaccine;
import model.world.*;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Main extends Application {
	
	private static Stage primaryStage;
	
	private static Scene scene;
	
	private static Statistics gameplayStatistics = new Statistics();
	
	static Hero currentHero;
	static Zombie currentZombie;
	private static GridPane gridPane = new GridPane();
	private static Media mainMenuMedia;
	
	
	private static MediaPlayer mediaPlayer;
	
	private static Controls controls = new Controls();
	
	private static Image mapImage , fillerImage;
	
	private static Popup popup;
	
	private static StackPane gameLayout = new StackPane();
	
	
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		popup = new Popup();
		
		mapImage = new Image(getClass().getResourceAsStream("./static/map.png"));
		fillerImage = new Image(getClass().getResourceAsStream("./static/filler.png"));
		
		
		String path = "./static/openingVideo.mp4";
		Media media = new Media(new File(getClass().getResource(path).getPath()).toURI().toString());  
		mainMenuMedia = new Media(new File(getClass().getResource("./static/loadingScreen.mp4").getPath()).toURI().toString());
		mediaPlayer = new MediaPlayer(media);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		
		
		
		
		
		
     
		
		Game.loadHeroes("test_heros.csv");
		
		
		
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
        
        scene.setOnKeyReleased(event -> {
			mediaPlayer.stop();
			switchToLoadingScreen();
		});

		
		primaryStage.setScene(scene);
		
		
		
		primaryStage.show();
		
		
	}
	
	public static Scene getScene() {
		return scene;
	}

	public static int[] transform (int x , int y) {

		return new int[] {y,14-x};


	}
	
    public static void refresh() {
	
		gridPane.getChildren().clear();
		gameplayStatistics.updateStatistics();

		controls.updateControls();

		if(Game.checkGameOver()) {
			switchToGameOverScene();
		}
		if(Game.checkWin()) {
			switchToYouWonScene();
		}
		

		
		double cellWidth = mapImage.getWidth() / 15;
        double cellHeight = mapImage.getHeight() / 15;
        
        
        		
		for (int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				
				double xRect = j * cellWidth;
                double yRect = i * cellHeight;

                // Create an ImageView with the cell image
                ImageView cellImageView = new ImageView(mapImage);
                cellImageView.setViewport(new javafx.geometry.Rectangle2D(xRect, yRect, cellWidth, cellHeight));
				
				boolean isVisible = Game.map[j][i].isVisible();
				int[] transform_cords = transform(j,i);
				int x = transform_cords[0];
				int y = transform_cords[1];
				
				
				if(Game.map[j][i] instanceof CollectibleCell)
					if(((CollectibleCell)Game.map[j][i]).getCollectible() instanceof Vaccine)
						gridPane.add(new VaccineCellView(isVisible,cellImageView), x, y);
					else
						gridPane.add(new SupplyCellView(isVisible,cellImageView), x, y);
				
				
				else if(Game.map[j][i] instanceof TrapCell)
					gridPane.add(new TrapCellView(false,cellImageView), x, y);
				
				else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Hero) {
					
					Hero hero = (Hero) ((CharacterCell) Game.map[j][i] ).getCharacter();
					HeroCellView heroCellView = new HeroCellView(hero,isVisible,cellImageView); 
					
					HeroView heroView = heroCellView.getHeroView();
					heroView.setHealth(hero.getCurrentHp() / (double)hero.getMaxHp());
					
					
					
					gridPane.add(heroCellView, x,y);
				}else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Zombie) {
					Zombie zombie = (Zombie)((CharacterCell) Game.map[j][i] ).getCharacter();

					gridPane.add(new ZombieCellView(zombie,isVisible,cellImageView), x, y);
					
				}else
					gridPane.add(new CellView(isVisible,cellImageView), x, y);
			
				
                
				
				cellImageView.setFitWidth(scene.getWidth() *  0.04);
				cellImageView.setFitHeight(scene.getHeight() *  0.06);
			
				scene.widthProperty().addListener((observable, oldValue, newValue) -> {
		            double newWidth = newValue.doubleValue() *  0.04;
		            cellImageView.setFitWidth(newWidth);
		        });

		        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
		            double newHeight = newValue.doubleValue() *  0.06;
		            cellImageView.setFitHeight(newHeight);
		        });
				
//                button.setGraphic(cellImageView);
//                button.setStyle("-fx-background-color: #806454;-fx-padding: 0; -fx-margin: 0;");
//				gridPane.add(button,j,i);
			}
		}
		
		
	}
	
	public static void switchToGameplayScene() {
		
		
		
		scene.setOnKeyReleased(null);
		
		
		
		refresh();
		
		
		
		
		

		
		
		
		
//		gridPane.setStyle("-fx-background-color: #806454");
		gridPane.getStyleClass().add("map");
		Accordion statsAccord = new Accordion();
		statsAccord.setMaxWidth(scene.getWidth() * 0.2);
		statsAccord.setMaxHeight(gameplayStatistics.getHeight());
		TitledPane t1 = new TitledPane("Stats", gameplayStatistics);
		statsAccord.getPanes().addAll(t1);
		
		gameLayout.getChildren().addAll(gridPane,statsAccord);
		StackPane.setAlignment(statsAccord,Pos.TOP_RIGHT);
		scene.setRoot(gameLayout);
		
		keyboardEvents();
		
		
	}
	
	public static void switchToSelectHeroScene() {
//		BorderPane borderPane = new BorderPane();
//		HBox hBox = new HBox();
//		hBox.setAlignment(Pos.CENTER);
//		Statistics loadingScreenStatistics = new Statistics();
//		loadingScreenStatistics.setStatistics(Game.availableHeroes.get(0).getName());
//		
//		Game.availableHeroes.forEach(hero ->{
//			
//			Button heroBtn = new Button(hero.getName());
//			heroBtn.setId(hero.getName());
//			heroBtn.setOnAction( event -> {
//				
//				Hero heroToBeAdded = getHero(((Button)event.getSource()).getId());
//				Game.startGame(heroToBeAdded);
//				Game.availableHeroes.remove(heroToBeAdded);
//				
//				switchToGameplayScene();
//			} );
//			
//			heroBtn.addEventHandler(MouseEvent.MOUSE_ENTERED,
//			        new EventHandler<MouseEvent>() {
//		          @Override
//		          public void handle(MouseEvent e) {
//		        	  loadingScreenStatistics.setStatistics(hero.getName());
//		        	  
//		        	  
//		          }
//		        });
//
//			
//			hBox.getChildren().add(heroBtn);
//		});
//		
//		
//		borderPane.setCenter(hBox);
//		borderPane.setLeft(loadingScreenStatistics);
		
		scene.setRoot(new Carousel());
	}
	
	
	private static Hero getHero(String id) {
		ArrayList<Hero> availableHeroes = Game.availableHeroes;
		
		for(int i = 0; i < availableHeroes.size(); i++) 
			if(availableHeroes.get(i).getName().equals(id))
				return availableHeroes.get(i);
		
		return null;
				
		
		
	}

	public static void switchToLoadingScreen() {
		
		
	
		mediaPlayer = new MediaPlayer(mainMenuMedia);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(() -> {
        	switchToSelectHeroScene();
        });  
        
        
		
		Group root = new Group();  
		
		root.getChildren().addAll(mediaView);
		
		scene.setOnKeyReleased(event -> {
			mediaPlayer.stop();
			switchToSelectHeroScene();
		});
		
		
		
		scene.setRoot(root);
		
	}
	
	public void playStartGameVideo() {
		
	}
	
	public static void keyboardEvents() {
		scene.setOnKeyReleased(keyEvent -> {
			
			KeyCode keyCode = keyEvent.getCode();
			
			
			switch(keyCode) {
				case RIGHT:
					try {
						Main.currentHero.move(Direction.RIGHT);
						Main.refresh();
						
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
		
				case LEFT:
					try {
						Main.currentHero.move(Direction.LEFT);
						Main.refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
			
	
				case UP:
					try {
						Main.currentHero.move(Direction.UP);
						Main.refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
		
				case DOWN:
					try {
						Main.currentHero.move(Direction.DOWN);
						Main.refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
			
				case A:
				
					try {
						currentHero.setTarget(currentZombie);
						currentHero.attack();
						refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
				
				case E:
				
					try {
						Game.endTurn();
						refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
				
				case S:
					
					try {
						currentHero.useSpecial();
						refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
				
				case C:
				
				
					try {
						currentHero.setTarget(currentZombie);
						currentHero.cure();
						refresh();
					} catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
				
			
			}
		
		
	});
		 
	}
	public static void switchToGameOverScene(){
		mediaPlayer = new MediaPlayer(mainMenuMedia);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(() -> {
        	switchToLoadingScreen();
        });  
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		scene.setRoot(root);
		
	}
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void switchToYouWonScene() {
		mediaPlayer = new MediaPlayer(mainMenuMedia);  
        
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(() -> {
        	switchToLoadingScreen();
        });  
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		scene.setRoot(root);
		
		
	}
	
	public static void displayPopup(String text) {
		StackPane sp = new StackPane();
		popup.setPopupText(text);
		sp.getChildren().addAll(gameLayout,popup);
		scene.setRoot(sp);
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}