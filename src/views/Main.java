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
	
	
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
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
		
//		gridPane.setPrefWidth(primaryStage.getWidth() * 0.9);
//		gridPane.setPrefHeight(primaryStage.getHeight() * 0.9);
		
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
				
				
//				if(Game.map[j][i] instanceof CollectibleCell)
//					if(((CollectibleCell)Game.map[j][i]).getCollectible() instanceof Vaccine)
//						gridPane.add(new VaccineCellView(isVisible), x, y);
//					else
//						gridPane.add(new SupplyCellView(isVisible), x, y);
//				
//				
//				else if(Game.map[j][i] instanceof TrapCell)
//					gridPane.add(new TrapCellView(isVisible), x, y);
//				
//				else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Hero) {
//					
//					Hero hero = (Hero) ((CharacterCell) Game.map[j][i] ).getCharacter();
//					HeroCellView heroCellView = new HeroCellView(hero,isVisible); 
//					
//					HeroView heroView = heroCellView.getHeroView();
//					heroView.setHealth(hero.getCurrentHp() / (double)hero.getMaxHp());
//					
//					
//					
//					gridPane.add(heroCellView, x,y);
//				}else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Zombie) {
//					Zombie zombie = (Zombie)((CharacterCell) Game.map[j][i] ).getCharacter();
//
//					gridPane.add(new ZombieCellView(zombie,isVisible), x, y);
//					
//				}else
//					gridPane.add(new CellView(isVisible), x, y);
//			
				Button button = new Button();
				
                button.setGraphic(cellImageView);
                button.setStyle("-fx-background-color: red;-fx-padding: 0; -fx-margin: 0;");
				gridPane.add(button,j,i);
			}
		}
		
		
	}
	
	public static void switchToGameplayScene() {
		
		
		
		scene.setOnKeyReleased(null);
		
		BorderPane borderPane = new BorderPane();
		
		refresh();
		
		
		
		TilePane mountainPane = new TilePane();
       
        // Create the mountain tiles
        for (int i = 0; i < 5 * 1; i++) {
            Rectangle mountainTile = new Rectangle();
            mountainTile.setWidth(100);
            mountainTile.setHeight(100);
            mountainTile.setFill(new ImagePattern(fillerImage));
            mountainPane.getChildren().add(mountainTile);
        }
        mountainPane.setPadding(new Insets(0));
        TilePane.setMargin(mountainPane, new Insets(0));
        
        TilePane mountainPane1 = new TilePane();
        

        // Create the mountain tiles
        for (int i = 0; i < 5 * 1; i++) {
            Rectangle mountainTile = new Rectangle();
            mountainTile.setWidth(100);
            mountainTile.setHeight(100);
            mountainTile.setFill(new ImagePattern(fillerImage));
            mountainPane1.getChildren().add(mountainTile);
        }
        mountainPane1.setPadding(new Insets(0));
        TilePane.setMargin(mountainPane1, new Insets(0));
        
        TilePane mountainPane2 = new TilePane();
        
        // Create the mountain tiles
        for (int i = 0; i < 5 * 1; i++) {
            Rectangle mountainTile = new Rectangle();
            mountainTile.setWidth(100);
            mountainTile.setHeight(100);
            mountainTile.setFill(new ImagePattern(fillerImage));
            mountainPane2.getChildren().add(mountainTile);
        }
        mountainPane2.setPadding(new Insets(0));
        TilePane.setMargin(mountainPane2, new Insets(0));
        
        TilePane mountainPane3 = new TilePane();
        

        // Create the mountain tiles
        for (int i = 0; i < 5 * 1; i++) {
            Rectangle mountainTile = new Rectangle();
            mountainTile.setWidth(100);
            mountainTile.setHeight(100);
            mountainTile.setFill(new ImagePattern(fillerImage));
            mountainPane3.getChildren().add(mountainTile);
        }
        
        mountainPane3.setPadding(new Insets(0));
        TilePane.setMargin(mountainPane3, new Insets(0));

		

//		controls.updateControls();

		
//		MoveControls moveControls = new MoveControls();
		
		
	    VBox right = new VBox();
//	    right.getChildren().addAll(gameplayStatistics,moveControls);
	   // right.setSpacing(500);
		borderPane.setCenter(gridPane);
//		moveControls.setAlignment(Pos.BOTTOM_RIGHT);
//		gameplayStatistics.setAlignment(Pos.TOP_RIGHT);
//		borderPane.setRight(right);
		//borderPane.setBottom(moveControls);
	
//		borderPane.setLeft(controls);
		//borderPane.setTop(gameplayStatistics);
		
		
		borderPane.setLeft(mountainPane);
		borderPane.setRight(mountainPane1);
		borderPane.setTop(mountainPane2);
		borderPane.setBottom(mountainPane3);
		
		gridPane.setStyle("-fx-background-color: blue");
		
		
		scene.setRoot(borderPane);
		
		arrowMovemoments();
		
	}
	
	public static void switchToSelectHeroScene() {
		BorderPane borderPane = new BorderPane();
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		Statistics loadingScreenStatistics = new Statistics();
		
		
		Game.availableHeroes.forEach(hero ->{
			
			Button heroBtn = new Button(hero.getName());
			heroBtn.setId(hero.getName());
			heroBtn.setOnAction( event -> {
				
				Hero heroToBeAdded = getHero(((Button)event.getSource()).getId());
				Game.startGame(heroToBeAdded);
				Game.availableHeroes.remove(heroToBeAdded);
				
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
	
	public static void arrowMovemoments() {
		scene.setOnKeyReleased(keyEvent -> {
			
			KeyCode keyCode = keyEvent.getCode();
			System.out.println(keyCode);
			
			switch(keyCode) {
			case RIGHT:
			try {
				Main.currentHero.move(Direction.RIGHT);
				Main.refresh();
				
			} catch (MovementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
			case LEFT:
			try {
				Main.currentHero.move(Direction.LEFT);
				Main.refresh();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
	
			case UP:
			try {
				Main.currentHero.move(Direction.UP);
				Main.refresh();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
			case DOWN:
			try {
				Main.currentHero.move(Direction.DOWN);
				Main.refresh();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

}