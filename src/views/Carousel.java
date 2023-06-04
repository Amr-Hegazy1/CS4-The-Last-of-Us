package views;

import java.util.*;

import engine.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Carousel extends BorderPane  {
	
	private List<String> list = new ArrayList<String>();
    private int j = 0;
    private double orgCliskSceneX, orgReleaseSceneX;
    private Button lbutton, rButton;
    private ImageView imageView;
    private Statistics stats = new Statistics();
    private Label heroName = new Label();
    private Font goodTimingFont = Font.loadFont(getClass().getResourceAsStream("./static/goodtimingbd.otf"), 12);
 

	public Carousel() {
		super();
		populateList();
		
		
		this.getStyleClass().add("select-hero");	
//		setAlignment(Pos.CENTER);
		
		Image lButtonImage = new Image(getClass().getResourceAsStream("./static/carouselArrowLeft.png"));
		ImageView lButtonImageView = new ImageView(lButtonImage);
		
		
		Image rButtonImage = new Image(getClass().getResourceAsStream("./static/carouselArrowRight.png"));
		ImageView rButtonImageView = new ImageView(rButtonImage);
		
		lbutton = new Button();
        rButton = new Button();
        
        lbutton.getStyleClass().add("carousel-button");
        rButton.getStyleClass().add("carousel-button");
        
        lbutton.setGraphic(lButtonImageView);
        rButton.setGraphic(rButtonImageView);
        
        
        
        
        stats.setStatistics(Game.availableHeroes.get(0).getName());
       
        heroName.setText(stats.getHeroName());
        heroName.setFont(goodTimingFont);
        
        
        BorderPane.setAlignment(lbutton, Pos.CENTER_LEFT);
        BorderPane.setAlignment(rButton, Pos.CENTER_RIGHT);
        BorderPane.setAlignment(stats, Pos.TOP_CENTER);
        BorderPane.setAlignment(heroName, Pos.BOTTOM_CENTER);
        
        heroName.getStyleClass().add("stats");

        SpriteAnimation spriteAnimations[] = new SpriteAnimation[list.size()];
        for (int i = 0; i < list.size(); i++) {
        	
        	SpriteAnimation spriteAnimation = new SpriteAnimation("./static/" + list.get(i),4,1,5.0);
        	
        	spriteAnimations[i] = spriteAnimation;
        }

        imageView = spriteAnimations[j].getSprite();
        imageView.setOnMousePressed(circleOnMousePressedEventHandler);

        rButton.setOnAction(e -> {
            j = j + 1;
            if (j == list.size()) {
                j = 0;
            }
            stats.setStatistics(Game.availableHeroes.get(j).getName());
            heroName.setText(stats.getHeroName());
            imageView.setImage(spriteAnimations[j].getSprite().getImage());

        });
        lbutton.setOnAction(e -> {
            j = j - 1;
            if (j == 0 || j > list.size() + 1 || j == -1) {
                j = list.size() - 1;
            }
            stats.setStatistics(Game.availableHeroes.get(j).getName());
            heroName.setText(stats.getHeroName());
            imageView.setImage(spriteAnimations[j].getSprite().getImage());

        });

//        imageView.setFitHeight(100);
//        imageView.setFitWidth(300);

//        HBox hBox = new HBox();
//        setSpacing(15);
//        setAlignment(Pos.CENTER);
        // hBox.getChildren().addAll(lbutton, imageView, rButton);
        
        this.setCenter(imageView);
        this.setLeft(lbutton);
        this.setRight(rButton);
        this.setTop(stats);
        this.setBottom(heroName);
        
//        getChildren().addAll(lbutton,imageView,rButton,stats);

//        add(hBox, 2, 1);
        
	}
	
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent t) {
	    	Game.startGame(Game.availableHeroes.get(j));
	    	Game.availableHeroes.remove(j);
	    	Main.switchToGameplayScene();
	        
	    }
	};
	
	
	private void populateList() {
		Game.availableHeroes.forEach(hero -> {
			String heroType = hero.getClass().getSimpleName();
			switch(heroType) {
			
				case "Fighter": list.add("fighterIdle.png");break;
				case "Medic": list.add("medicIdle.png");break;
				case "Explorer": list.add("explorerIdle.png");break;
			}
			
		});
	}

}
