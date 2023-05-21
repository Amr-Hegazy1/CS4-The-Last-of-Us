package views;



import java.awt.Point;

import engine.Game;
import exceptions.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import model.characters.Direction;
import model.characters.Hero;
import model.world.CharacterCell;

public class Controls extends VBox {
	
	GameButton cureBtn = new GameButton("cure");

	GameButton useSpecialBtn = new GameButton("use special");
	GameButton moveRight = new GameButton("move right");
	GameButton moveLeft = new GameButton("move left");
	GameButton moveUp = new GameButton("move up");
	GameButton moveDown = new GameButton("move down");
	GameButton attack = new GameButton ("Attack");
	GameButton endTurn = new GameButton ("End Turn");
	
	public Controls() {
//		Image leftImage= new Image("arrowleft.png");
//		Image RightImage= new Image("arrowright.png");
//		Image UpImage= new Image("arrowup.png");
//		Image DownImage= new Image("arrowdown.png");
//		ImageView leftImageView = new ImageView(leftImage);
//		ImageView RightImageView = new ImageView(RightImage);
//		ImageView UpImageView = new ImageView(UpImage);
//		ImageView DownImageView = new ImageView(DownImage);
		
		
		
		getChildren().clear();
		
		

		
		
		getChildren().addAll(moveRight,moveLeft,moveUp,moveDown,attack,cureBtn,useSpecialBtn,endTurn);
		
		
	}
	
	public void updateControls() {
		
		moveRight.setActive(Main.currentHero != null);
		moveLeft.setActive(Main.currentHero != null);
		moveUp.setActive(Main.currentHero != null);
		moveDown.setActive(Main.currentHero != null);
		
		
		cureBtn.setOnAction(event -> {

			Main.currentHero.setTarget(Main.currentZombie);

			try {
				Main.currentHero.cure();
				Main.refresh();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				// hezzzzzzzzzzzz
				e.printStackTrace();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoAvailableResourcesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		useSpecialBtn.setOnAction(event -> {




				try {
					if(!useSpecialBtn.isActive()) {
						useSpecialBtn.vibrateSideways();
						return;
					}
					Main.currentHero.useSpecial();
				} catch (NoAvailableResourcesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.refresh();


		});

		

		

		
		
		
		
		
		
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
				
				// 7ezzz

				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		});	
		
		moveUp.setOnAction(event -> {
			
				
				
				try {
					if(!moveUp.isActive()) {
						moveUp.vibrateSideways();
						return;
					}
					Main.currentHero.move(Direction.UP);
					
				} catch (MovementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnoughActionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.refresh();
				
		});

		
		
		
		moveDown.setOnAction(event ->{
			
			
				
				
				try {
					if(!moveDown.isActive()) {
						moveDown.vibrateSideways();
						return;
					}
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
		attack.setOnAction(event->{

			try {
				
				if(!attack.isActive()) {
					attack.vibrateSideways();
					return;
				}
				
				Main.currentHero.setTarget(Main.currentZombie);
				Main.currentHero.attack();
				Main.refresh();
				
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		
		endTurn.setOnAction(event ->{
			try {
				if(!endTurn.isActive()) {
					endTurn.vibrateSideways();
					return;
				}
				Game.endTurn();
				Main.refresh();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		

		
	}

}
