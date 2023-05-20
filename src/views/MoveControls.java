package views;

import java.awt.Point;
import java.beans.EventHandler;

import engine.Game;
import exceptions.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import model.characters.Direction;
import model.characters.Hero;
import model.world.CharacterCell;
public class MoveControls extends HBox {
	VBox vBox= new VBox();
	 public MoveControls(){
		 
	Image leftImage= new Image(getClass().getResourceAsStream("static/arrowleft.png"));
	Image RightImage= new Image(getClass().getResourceAsStream("static/arrowright.png"));
	Image UpImage= new Image(getClass().getResourceAsStream("static/arrowup.png"));
	Image DownImage= new Image(getClass().getResourceAsStream("static/arrowdown.png"));
	ImageView leftImageView = new ImageView(leftImage);
	leftImageView.setFitWidth(100);
	leftImageView.setFitHeight(50);
	ImageView RightImageView = new ImageView(RightImage);
	RightImageView.setFitWidth(100);
	RightImageView.setFitHeight(50);
	ImageView UpImageView = new ImageView(UpImage);
	UpImageView.setFitWidth(50);
	UpImageView.setFitHeight(100);
	ImageView DownImageView = new ImageView(DownImage);
	DownImageView.setFitWidth(50);
	DownImageView.setFitHeight(100);
	
	//vBox.setAlignment(Pos.CENTER);
	
	Button moveRight = new Button();
	moveRight.setPrefWidth(100);
	moveRight.setPrefHeight(100);
	moveRight.setGraphic(RightImageView);
	moveRight.setStyle("-fx-background-color: transparent;");
	
	Button moveLeft = new Button();
	moveLeft.setPrefWidth(100);
	moveLeft.setPrefHeight(100);
	moveLeft.setGraphic(leftImageView);
	moveLeft.setStyle("-fx-background-color: transparent;");
	Button moveUp = new Button();
	moveUp.setPrefWidth(100);
	moveUp.setPrefHeight(50);
	moveUp.setGraphic(UpImageView);
	moveUp.setStyle("-fx-background-color: transparent;");
	Button moveDown = new Button();
	moveDown.setPrefWidth(100);
	moveDown.setPrefHeight(50);
	moveDown.setGraphic(DownImageView);
	moveDown.setStyle("-fx-background-color: transparent;");
	vBox.setAlignment(Pos.BOTTOM_CENTER);
	vBox.getChildren().addAll(moveUp,moveDown);
	this.getChildren().addAll(moveLeft,vBox,moveRight);
	
	
	
	moveRight.setOnAction(event ->{
	
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

		
	});	
	
	moveLeft.setOnAction(event ->{
		
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
		
	});	
	
	moveUp.setOnAction(event ->{
		
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

	});
	
	moveDown.setOnAction(event ->{
		
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
	
	});	
		
	
	

	 
}
}
