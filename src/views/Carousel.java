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

public class Carousel extends GridPane  {
	
	private List<String> list = new ArrayList<String>();
    private int j = 0;
    private double orgCliskSceneX, orgReleaseSceneX;
    private Button lbutton, rButton;
    private ImageView imageView;
    private Statistics stats = new Statistics();
 

	public Carousel() {
		super();
		populateList();
		
		this.getStyleClass().add("select-hero");	
		setAlignment(Pos.CENTER);
		lbutton = new Button("<");
        rButton = new Button(">");
        stats.setStatistics(Game.availableHeroes.get(0).getName());
        

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
            imageView.setImage(spriteAnimations[j].getSprite().getImage());

        });
        lbutton.setOnAction(e -> {
            j = j - 1;
            if (j == 0 || j > list.size() + 1 || j == -1) {
                j = list.size() - 1;
            }
            stats.setStatistics(Game.availableHeroes.get(j).getName());
            imageView.setImage(spriteAnimations[j].getSprite().getImage());

        });

//        imageView.setFitHeight(100);
//        imageView.setFitWidth(300);

        HBox hBox = new HBox();
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);
        // hBox.getChildren().addAll(lbutton, imageView, rButton);
        hBox.getChildren().addAll(lbutton,imageView,rButton,stats);

        add(hBox, 1, 1);
        
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
