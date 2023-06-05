package views;

import java.io.File;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.animation.PauseTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import model.characters.Hero;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class CellView extends ButtonWithblClick {
	
	
	
	private SpriteAnimation spriteAnimation;
	
	private MediaPlayer characterSelectSoundPlayer;
	private Media characterSelectSound;
	
	public CellView() {
		super();
		initialize();
		this.setPrefWidth(50);
		this.setPrefHeight(60);
		getStyleClass().add("cell-visible");
		
		
	}
	
	public CellView(boolean isVisible) {
		this();
		if(!isVisible) {
			getStyleClass().add("cell-invisible");
			
		}else {
			getStyleClass().add("cell-visible");
		}

	}
	
	public CellView(boolean isVisible,ImageView tile) {
		this();
		if(!isVisible) {
			getStyleClass().add("cell-invisible");
			
		}else {
			getStyleClass().add("cell-visible");
		}
		this.setGraphic(tile);

	}

	
	
	public CellView(String text) {
		super(text);
		initialize();
		this.setPrefWidth(40);
		
		this.setPrefHeight(60);
	}
	
	public CellView(ImageView tile) {
		super();
		initialize();
		this.setPrefWidth(50);
		
		this.setPrefHeight(50);
		
		this.setGraphic(tile);
	}
	
	private void initialize() {
		this.setOnMouseDoubleClicked(event -> doubleClick() );
		this.setOnMouseSingleClicked(event -> singleClick() );
	}
	
	
	private void singleClick() {
		
		if(this instanceof HeroCellView) {
    		Main.currentHero = ((HeroCellView) this).hero;
    		Main.currentHeroCellView = (HeroCellView) this;
    		Main.currentHeroStats.setStatistics(Main.currentHero);
    		Main.refresh();
    		
    		characterSelectSound = new Media(new File(getClass().getResource("./static/" + Main.currentHero.getClass().getSimpleName().toLowerCase() + "SelectSound.mp3").getPath()).toURI().toString());  
    		characterSelectSoundPlayer = new MediaPlayer(characterSelectSound);
    		characterSelectSoundPlayer.play();
    	}
		
	}
	
	private void doubleClick() {
		if(this instanceof ZombieCellView) {
    		Main.currentZombie = ((ZombieCellView) this).zombie;
    		Main.currentZombieCellView = (ZombieCellView) this;
    		
    	}
        
        if(this instanceof HeroCellView) {
    		Main.medicTarget = ((HeroCellView) this).hero;
    		Main.refresh();
    	}
	}
	
	public CellView(String imageLoc, int width,int height) {
		super();
		spriteAnimation = new SpriteAnimation(imageLoc,width,height);
		initialize();
		this.setGraphic(spriteAnimation.getSprite());
	}
	

}
