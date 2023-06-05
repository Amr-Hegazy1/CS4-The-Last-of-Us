package views;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import engine.Game;
import exceptions.*;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
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
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.util.Duration;
import model.characters.*;
import model.collectibles.Vaccine;
import model.world.*;



public class Main extends Application {
	
	private static Stage primaryStage;
	
	private static Scene scene;
	
	private static Statistics gameplayStatistics = new Statistics();
	protected static Statistics currentHeroStats = new Statistics();
	
	
	
	
	static Hero currentHero;
	static HeroCellView currentHeroCellView;
	static Hero medicTarget = null;
	static Zombie currentZombie;
	static ZombieCellView currentZombieCellView;
	private static GridPane gridPane = new GridPane();
	private static Media mainMenuMedia,gameOverMedia,gameWinMedia;
	
	
	private static MediaPlayer mediaPlayer,soundPlayer,trapSoundPlayer,cureSoundPlayer,walkSoundPlayer,useSpecialSoundPlayer,zombieDeathSoundPlayer,zombieHurtSoundPlayer;
	
	private static Controls controls = new Controls();
	
	private static Image mapImage , fillerImage;
	
	private static Popup popup;
	
	private static StackPane gameLayout = new StackPane();
	
	private static Font arcadeClassicFont;
	
	
	
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		popup = new Popup();
		
		mapImage = new Image(getClass().getResourceAsStream("./static/map.png"));
		fillerImage = new Image(getClass().getResourceAsStream("./static/filler.png"));
		
		
		String path = "./static/openingVideo.mp4";
		Media media = new Media(new File(getClass().getResource(path).getPath()).toURI().toString());  
		mainMenuMedia = new Media(new File(getClass().getResource("./static/loadingScreen.mp4").getPath()).toURI().toString());
		gameOverMedia = new Media(new File(getClass().getResource("./static/gameOver.mp4").getPath()).toURI().toString());
		gameWinMedia = new Media(new File(getClass().getResource("./static/gameWin.mp4").getPath()).toURI().toString());
		mediaPlayer = new MediaPlayer(media);  
        
	
		mediaPlayer.setAutoPlay(true); 
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		Media gameplayAudio = new Media(new File(getClass().getResource("./static/gameplayAudio.mp3").getPath()).toURI().toString());
		
		soundPlayer = new MediaPlayer(gameplayAudio);
		
		soundPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		    	   soundPlayer.seek(Duration.ZERO);
		       }
		   });
		
		Media trapSound = new Media(new File(getClass().getResource("./static/trapSound.mp3").getPath()).toURI().toString());
		trapSoundPlayer = new MediaPlayer(trapSound);
		
		
		Media cureSound = new Media(new File(getClass().getResource("./static/cureSound.mp3").getPath()).toURI().toString());
		cureSoundPlayer = new MediaPlayer(cureSound);
		
		Media walkSound = new Media(new File(getClass().getResource("./static/walkSound.mp3").getPath()).toURI().toString());
		walkSoundPlayer = new MediaPlayer(walkSound);
		walkSoundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		
		Media useSpecialSound = new Media(new File(getClass().getResource("./static/useSpecialSound.mp3").getPath()).toURI().toString());
		useSpecialSoundPlayer = new MediaPlayer(useSpecialSound);
		
		Media zombieDeathSound = new Media(new File(getClass().getResource("./static/zombieDeathSound.mp3").getPath()).toURI().toString());
		zombieDeathSoundPlayer = new MediaPlayer(zombieDeathSound);
		
		Media zombieHurtSound = new Media(new File(getClass().getResource("./static/zombieHurtSound.mp3").getPath()).toURI().toString());
		zombieHurtSoundPlayer = new MediaPlayer(zombieHurtSound);
		
		arcadeClassicFont = Font.loadFont(getClass().getResourceAsStream("./static/ARCADECLASSIC.ttf"), 25);
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		
		
		
		
		
		
     
		
		Game.loadHeroes("test_heros.csv");
		
		
		
		primaryStage.setTitle("The Last Of Us - Legacy");
		
		
		
		
		
		
		
		gridPane.setAlignment(Pos.CENTER);
		
		
		
		gameLayout.setStyle("-fx-background-color: transparent;");
		
		
		

	
	
		
		
		
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
		
		currentHeroStats.setStatistics(currentHero);
		
		controls.updateControls();
		
		if(scene.getRoot() instanceof StackPane) {
			((StackPane) scene.getRoot() ).getChildren().remove(popup);
		}

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
				
				boolean isVisible = Game.map[j][i].isVisible();
				int[] transform_cords = transform(j,i);
				int x = transform_cords[0];
				int y = transform_cords[1];
				
				
				if(Game.map[j][i] instanceof CollectibleCell)
					if(((CollectibleCell)Game.map[j][i]).getCollectible() instanceof Vaccine)
						gridPane.add(new VaccineCellView(isVisible), x, y);
					else
						gridPane.add(new SupplyCellView(isVisible), x, y);
				
				
				else if(Game.map[j][i] instanceof TrapCell) {
					gridPane.add(new TrapCellView(isVisible), x, y);
//					System.out.println(x + " " + y);
				
				}else if(Game.map[j][i] instanceof CharacterCell && ((CharacterCell) Game.map[j][i] ).getCharacter() instanceof Hero) {
					
					Hero hero = (Hero) ((CharacterCell) Game.map[j][i] ).getCharacter();
					HeroCellView heroCellView = new HeroCellView(hero,isVisible); 
					
					HeroView heroView = heroCellView.getHeroView();
					heroView.setHealth(hero.getCurrentHp() / (double)hero.getMaxHp());
					
					
					
					if(hero == currentHero)
						currentHeroCellView = heroCellView;
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
		
		
		
		scene.setOnKeyReleased(null);
		
		
		
		refresh();
		
		
		
		
		

		
		
		
		

		gridPane.getStyleClass().add("map");
//		Accordion statsAccord = new Accordion();
//		statsAccord.setMaxWidth((scene.getWidth() - gridPane.getWidth()) / 6);
//		statsAccord.setMaxHeight(gameplayStatistics.getHeight());
//		TitledPane statsPane = new TitledPane("Stats", gameplayStatistics);
//		statsAccord.getPanes().addAll(statsPane);
		
		StackPane.setAlignment(gameplayStatistics, Pos.TOP_LEFT);
		StackPane.setAlignment(currentHeroStats, Pos.TOP_RIGHT);
		currentHeroStats.setStatistics(currentHero);
		
		
		gameLayout.getChildren().addAll(gridPane,gameplayStatistics,currentHeroStats);
		
		
		
		scene.setRoot(gameLayout);
		
		keyboardEvents();
		
		
	}
	
	public static void switchToSelectHeroScene() {
		
		soundPlayer.play();
		
		StackPane sp = new StackPane();
		Carousel heroCarousel = new Carousel();
		sp.getChildren().add(heroCarousel);
		StackPane.setAlignment(heroCarousel,Pos.CENTER);
		scene.setRoot(sp);
	}
	
	
	private static Hero getHero(String id) {
		ArrayList<Hero> availableHeroes = Game.availableHeroes;
		
		for(int i = 0; i < availableHeroes.size(); i++) 
			if(availableHeroes.get(i).getName().equals(id))
				return availableHeroes.get(i);
		
		return null;
				
		
		
	}

	public static void switchToLoadingScreen() {
		
		Label label = new Label("PRESS ANY BUTTON TO START");
		label.getStyleClass().add("loading-screen-label");
		label.setFont(arcadeClassicFont);
		
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
	
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
		
		root.getChildren().addAll(mediaView,label);
		
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
						
						walkSoundPlayer.play();
						if(checkTrap(currentHero,Direction.RIGHT)) {
							FunctionRunner.runInFXThread(() -> {
					   
								try {
						
									trapSoundPlayer.stop();
									Main.currentHero.move(Direction.RIGHT);
									currentHeroCellView = null;
								} catch (MovementException | NotEnoughActionsException e) {
									displayPopup(e.getMessage());
									walkSoundPlayer.stop();
								}catch(NullPointerException e) {
									displayPopup("Please select a character");
									walkSoundPlayer.stop();
								}
								Main.refresh();
					        }, Duration.seconds(2));
							walkSoundPlayer.stop();
							trapSoundPlayer.play();
							TrapCellView trapCellView = (TrapCellView) getNodeFromGridPane(gridPane,GridPane.getRowIndex(currentHeroCellView), GridPane.getColumnIndex(currentHeroCellView) + 1);
							trapCellView.setVisible(true);
							StackPane sp = new StackPane();
							sp.getChildren().addAll(trapCellView.sprite,currentHeroCellView.heroView.getLayout());
							trapCellView.setCellGraphic(sp);
							
						}else {
							
							FunctionRunner.runInFXThread(() ->{
								walkSoundPlayer.stop();
							},Duration.seconds(1.5));
							
							Main.currentHero.move(Direction.RIGHT);
							Main.refresh();
						}
						
						
						
						
					}catch(NullPointerException e) {
						walkSoundPlayer.stop();
						displayPopup("Please select a character");
					}catch (Exception e) {
						walkSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
		
				case LEFT:
					try {
						walkSoundPlayer.play();
						if(checkTrap(currentHero,Direction.LEFT)) {
							FunctionRunner.runInFXThread(() -> {
					   
								try {
									trapSoundPlayer.stop();
									Main.currentHero.move(Direction.LEFT);
									currentHeroCellView = null;
									
								} catch(NullPointerException e) {
									walkSoundPlayer.stop();
									displayPopup("Please select a character");
								} catch (MovementException | NotEnoughActionsException e) {
									walkSoundPlayer.stop();
									displayPopup(e.getMessage());
								}
								Main.refresh();
					        }, Duration.seconds(2));
							
							walkSoundPlayer.stop();
							trapSoundPlayer.play();
							TrapCellView trapCellView = (TrapCellView) getNodeFromGridPane(gridPane,GridPane.getRowIndex(currentHeroCellView), GridPane.getColumnIndex(currentHeroCellView) - 1);
							trapCellView.setVisible(true);
							StackPane sp = new StackPane();
							sp.getChildren().addAll(trapCellView.sprite,currentHeroCellView.heroView.getLayout());
							trapCellView.setCellGraphic(sp);
							
						}else {
							FunctionRunner.runInFXThread(() ->{
								walkSoundPlayer.stop();
							},Duration.seconds(1.5));
							
							Main.currentHero.move(Direction.LEFT);
							Main.refresh();
						}
						
						
						
						
						
						
					} catch(NullPointerException e) {
						walkSoundPlayer.stop();
						displayPopup("Please select a character");
					} catch (Exception e) {
						walkSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
			
	
				case UP:
					try {
						walkSoundPlayer.play();
						if(checkTrap(currentHero,Direction.UP)) {
							FunctionRunner.runInFXThread(() -> {
					   
								try {
									trapSoundPlayer.stop();
									Main.currentHero.move(Direction.UP);
									currentHeroCellView = null;
								} catch (MovementException | NotEnoughActionsException e) {
									walkSoundPlayer.stop();
									displayPopup(e.getMessage());
								}catch(NullPointerException e) {
									walkSoundPlayer.stop();
									displayPopup("Please select a character");
								}
								Main.refresh();
					        }, Duration.seconds(2));
							
							
							walkSoundPlayer.stop();
							trapSoundPlayer.play();
							
							TrapCellView trapCellView = (TrapCellView) getNodeFromGridPane(gridPane,GridPane.getRowIndex(currentHeroCellView)-1, GridPane.getColumnIndex(currentHeroCellView));
							trapCellView.setVisible(true);
							StackPane sp = new StackPane();
							sp.getChildren().addAll(trapCellView.sprite,currentHeroCellView.heroView.getLayout());
							trapCellView.setCellGraphic(sp);
							
						}else {
							FunctionRunner.runInFXThread(() ->{
								walkSoundPlayer.stop();
							},Duration.seconds(1.5));
							Main.currentHero.move(Direction.UP);
							Main.refresh();
						}
					} catch(NullPointerException e) {
						walkSoundPlayer.stop();
						displayPopup("Please select a character");
					}catch (Exception e) {
						walkSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
		
				case DOWN:
					try {
						walkSoundPlayer.play();
						if(checkTrap(currentHero,Direction.DOWN)) {
							FunctionRunner.runInFXThread(() -> {
					   
								try {
									trapSoundPlayer.stop();
									
									Main.currentHero.move(Direction.DOWN);
									currentHeroCellView = null;
								} catch (MovementException | NotEnoughActionsException e) {
									walkSoundPlayer.stop();
									displayPopup(e.getMessage());
								}catch(NullPointerException e) {
									walkSoundPlayer.stop();
									displayPopup("Please select a character");
								}
								Main.refresh();
					        }, Duration.seconds(2));
							
							
							walkSoundPlayer.stop();
							trapSoundPlayer.play();
							TrapCellView trapCellView = (TrapCellView) getNodeFromGridPane(gridPane,GridPane.getRowIndex(currentHeroCellView)+1, GridPane.getColumnIndex(currentHeroCellView));
							trapCellView.setVisible(true);
							StackPane sp = new StackPane();
							sp.getChildren().addAll(trapCellView.sprite,currentHeroCellView.heroView.getLayout());
							trapCellView.setCellGraphic(sp);
							
						}else {
							FunctionRunner.runInFXThread(() ->{
								walkSoundPlayer.stop();
							},Duration.seconds(1.5));
							Main.currentHero.move(Direction.DOWN);
							Main.refresh();
						}
					} catch(NullPointerException e) {
						walkSoundPlayer.stop();
						displayPopup("Please select a character");
					} catch (Exception e) {
						walkSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
			
				case A:
				
					try {
						FunctionRunner.runInFXThread(() -> {
							  
							if(currentZombie.getCurrentHp() <= 0) {
								// zombie death animation
								zombieHurtSoundPlayer.stop();
								zombieDeathSoundPlayer.play();
								String path = "./static/zombieDeath.png";
								SpriteAnimation bx = new SpriteAnimation(path,6,1,0.75);
								ImageView sprite = bx.getSprite();
								ZombieCellView zombieCellView = Main.currentZombieCellView;
								ZombieView zombieView = zombieCellView.getZombieView();
								
								zombieView.setSprite(sprite);
								
								BorderPane borderPane = new BorderPane();
								borderPane.setBottom(zombieView.getHealthBar());
								borderPane.setCenter(sprite);
								zombieView.setLayout(borderPane);
								zombieCellView.setGraphic(borderPane);
								
								FunctionRunner.runInFXThread(() -> {
									  
									zombieDeathSoundPlayer.stop();
									refresh();
						        }, Duration.seconds(1));
								
							}else {
								refresh();
							}
							
							
							
				        }, Duration.seconds(1));
						
						currentHero.setTarget(currentZombie);
						currentHero.attack();
						
						zombieHurtSoundPlayer.play();
						
						
						
						// hero attack animations
						
						String path = "./static/" + currentHero.getClass().getSimpleName().toLowerCase() + "Attack.png";
						SpriteAnimation bx = new SpriteAnimation(path,6,1,0.75);
						ImageView sprite = bx.getSprite();
						
						HeroCellView heroCellView = Main.currentHeroCellView;

						HeroView heroView = heroCellView.getHeroView();
						
						heroView.setSprite(sprite);
						
						BorderPane borderPane = new BorderPane();
						borderPane.setBottom(heroView.getHealthBar());
						borderPane.setCenter(sprite);
						heroView.setLayout(borderPane);
						heroCellView.setGraphic(borderPane);
						
						currentHeroCellView.rotate(lookTo(currentHero));
						
						// zombie hurt animation
						
						path = "./static/zombieHurt.png";
						bx = new SpriteAnimation(path,2,1,0.75);
						sprite = bx.getSprite();
						ZombieCellView zombieCellView = Main.currentZombieCellView;
						ZombieView zombieView = zombieCellView.getZombieView();
						
						zombieView.setSprite(sprite);
						
						
						borderPane = new BorderPane();
						borderPane.setBottom(zombieView.getHealthBar());
						borderPane.setCenter(sprite);
						zombieView.setLayout(borderPane);
						zombieCellView.setGraphic(borderPane);
						
						
					} catch(NullPointerException e) {
						zombieHurtSoundPlayer.stop();
						zombieDeathSoundPlayer.stop();
						displayPopup("Please select a character");
					} catch (Exception e) {
						zombieHurtSoundPlayer.stop();
						zombieDeathSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
				
				case E:
				
					try {
						Game.endTurn();
						refresh();
					} catch(NullPointerException e) {
						displayPopup("Please select a character");
					}catch (Exception e) {
						displayPopup(e.getMessage());
					} 
					break;
				
				case S:
					
					try {
						FunctionRunner.runInFXThread(() -> {
							useSpecialSoundPlayer.stop();   
							
							refresh();
				        }, Duration.seconds(1.5));
						
						if(currentHero instanceof Medic)
							currentHero.setTarget(medicTarget);
						
						useSpecialSoundPlayer.play();
						currentHero.useSpecial();
						String path = "./static/" + currentHero.getClass().getSimpleName().toLowerCase() + "Special.png";
						SpriteAnimation bx = new SpriteAnimation(path,6,1,0.75);
						ImageView sprite = bx.getSprite();
						
						HeroCellView heroCellView = Main.currentHeroCellView;

						HeroView heroView = heroCellView.getHeroView();
						
						heroView.setSprite(sprite);
						
						BorderPane borderPane = new BorderPane();
						borderPane.setBottom(heroView.getHealthBar());
						borderPane.setCenter(sprite);
						heroView.setLayout(borderPane);
						heroCellView.setGraphic(borderPane);
						
					} catch(NullPointerException e) {
						useSpecialSoundPlayer.stop();
						displayPopup("Please select a character");
					} catch (Exception e) {
						useSpecialSoundPlayer.stop();
						displayPopup(e.getMessage());
					} 
					break;
				
				case C:
					
					FunctionRunner.runInFXThread(() -> {
						   
						
						cureSoundPlayer.stop();
			        }, Duration.seconds(1));
				
				
					try {
						cureSoundPlayer.play();
						currentHero.setTarget(currentZombie);
						currentHero.cure();
						refresh();
					} catch(NullPointerException e) {
						displayPopup("Please select a character");
						cureSoundPlayer.stop();
					} catch (Exception e) {
						displayPopup(e.getMessage());
						cureSoundPlayer.stop();
					} 
					break;
				
			
			}
		
		
	});
		 
	}
	public static void switchToGameOverScene(){
		mediaPlayer = new MediaPlayer(gameOverMedia);  
        
		mediaPlayer.setAutoPlay(true); 
		
		soundPlayer.stop();
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(null);  
		
		Group root = new Group();  
		
		root.getChildren().add(mediaView);
		
		scene.setRoot(root);
		
	}
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void switchToYouWonScene() {
		mediaPlayer = new MediaPlayer(gameWinMedia);  
        
		mediaPlayer.setAutoPlay(true); 
		
		soundPlayer.stop();
		
		MediaView mediaView = new MediaView (mediaPlayer);
		
		mediaView.setPreserveRatio(false);
		
		mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
        mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        mediaPlayer.setOnEndOfMedia(null);  
		
		
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
	
	private static boolean checkTrap(Hero hero,Direction direction) {
		
		int x = (int) hero.getLocation().getX();
		int y = (int) hero.getLocation().getY();
		
		
		
		if(direction.equals(Direction.UP) && x < 14 && Game.map[x+1][y] instanceof TrapCell)
			return true;
		if(direction.equals(Direction.DOWN) && x > 0 && Game.map[x-1][y] instanceof TrapCell)
			return true;
		
		if(direction.equals(Direction.RIGHT) && y < 14 && Game.map[x][y+1] instanceof TrapCell)
			return true;
		
		if(direction.equals(Direction.LEFT) && y > 0 && Game.map[x][y-1] instanceof TrapCell)
			return true;
			

		
		return false;
		
	}
	
	private static Node getNodeFromGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridPane.getChildren()) {
        	System.out.println(GridPane.getRowIndex(node) + " " + GridPane.getColumnIndex(node));
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return node;
            }
        }
        return null;
    }
	
	
	public static newDirection lookTo (Hero h) {
		int x = h.getLocation().x;
		int y = h.getLocation().y;
		Point p = h.getTarget().getLocation();
		int x2 = p.x;
		int y2 = p.y;
		int x_diff = x2-x;
		int y_diff = y2-y;

		if(x_diff>0 && y_diff==0) {
			return newDirection.UP;
		}
		else if(x_diff>0 && y_diff>0) {
			return newDirection.UPRIGHT;
		}
		else if(x_diff>0 && y_diff<0) {
			return newDirection.UPLEFT;
		}
		else if(x_diff==0 && y_diff>0) {
			return newDirection.RIGHT;
		}
		else if(x_diff==0 && y_diff<0) {
			return newDirection.LEFT;
		}
		else if(x_diff<0 && y_diff>0) {
			return newDirection.DOWNRIGHT;
		}
		else if(x_diff<0 && y_diff==0) {
			return newDirection.DOWN;
		}
		else  {
			return newDirection.DOWNLEFT;
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	

}