package views;

import java.util.*;

import javafx.animation.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SpriteAnimation {
	private int index = 0;
	private ImageView sprite;
    

    

    public ImageView getSprite() {
		return sprite;
	}




	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}




	public SpriteAnimation(String imageLoc,int width, int height,int duration,double resizeFactor) {
        Image image = new Image(getClass().getResourceAsStream(imageLoc));
        
        double spriteHeight = image.getHeight() / height;
        double spriteWidth = image.getWidth() / width;

        // create viewports to cycle through
        List<Rectangle2D> areas = new ArrayList<>(height * width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                areas.add(new Rectangle2D(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight));
            }
        }

        sprite = new ImageView(image);
        sprite.setFitWidth(spriteWidth * resizeFactor);
        sprite.setFitHeight(spriteHeight * resizeFactor);
        sprite.setViewport(areas.get(0));
        
        // create timeline animation cycling through viewports
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(duration), event ->  {

            

        
            sprite.setViewport(areas.get(index));
            index++;
            if (index >= areas.size()) {
                index = 0;
            }
            

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        
    }
	public SpriteAnimation(String imageLoc,int width, int height) {
        this(imageLoc,width,height,250,0.5);

        
    }
	
	public SpriteAnimation(String imageLoc,int width, int height,int duration) {
        this(imageLoc,width,height,duration,0.5);

        
    }
	
	public SpriteAnimation(String imageLoc,int width, int height,double resizeFactor) {
        this(imageLoc,width,height,250,resizeFactor);

        
    }
	
}
