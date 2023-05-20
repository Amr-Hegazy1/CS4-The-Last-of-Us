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
	
	public Controls() {
//		Image leftImage= new Image("arrowleft.png");
//		Image RightImage= new Image("arrowright.png");
//		Image UpImage= new Image("arrowup.png");
//		Image DownImage= new Image("arrowdown.png");
//		ImageView leftImageView = new ImageView(leftImage);
//		ImageView RightImageView = new ImageView(RightImage);
//		ImageView UpImageView = new ImageView(UpImage);
//		ImageView DownImageView = new ImageView(DownImage);
		
		
		
		Button cureBtn = new Button("cure");

		Button useSpecialBtn = new Button("use special");

		cureBtn.setOnAction(event -> {

			Main.currentHero.setTarget(Main.currentZombie);

			try {
				Main.currentHero.cure();
				Main.refresh();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
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

		this.getChildren().addAll(cureBtn,useSpecialBtn);

		
//		Button moveRight = new Button();
//		moveRight.setGraphic(RightImageView);
//		Button moveLeft = new Button();
//		moveLeft.setGraphic(leftImageView);
//		Button moveUp = new Button();
//		moveUp.setGraphic(UpImageView);
//		Button moveDown = new Button();
//		moveDown.setGraphic(DownImageView);
		Button attackRight = new Button ("Attack Right");
		Button attackUpRight = new Button ("Attack UpRight");
		Button attackLeft = new Button ("Attack Left");
		Button attackUp = new Button ("Attack Up");
		Button attackDown = new Button ("Attack Down");
		Button attackDownRight = new Button ("Attack DownRight");
		Button attackUpLeft = new Button ("Attack UpLeft");
		Button attackDownLeft = new Button ("Attack DownLeft");
		Button endTurn = new Button ("End Turn");
		
		
//		moveRight.setOnAction(event ->{
//			
//			try {
//				Main.currentHero.move(Direction.RIGHT);
//				Main.refresh();
//				
//			} catch (MovementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NotEnoughActionsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		});	
//		
//		moveLeft.setOnAction(event ->{
//			
//			try {
//				Main.currentHero.move(Direction.LEFT);
//				Main.refresh();
//			} catch (MovementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NotEnoughActionsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		});	
//		
//		moveUp.setOnAction(event ->{
//			
//			try {
//				Main.currentHero.move(Direction.UP);
//				Main.refresh();
//			} catch (MovementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NotEnoughActionsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		});	
//		
//		moveDown.setOnAction(event ->{
//			
//			try {
//				Main.currentHero.move(Direction.DOWN);
//				Main.refresh();
//			} catch (MovementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NotEnoughActionsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		});	
		attackRight.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x,p.y+1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackLeft.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x,p.y-1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackUpRight.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x+1,p.y+1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackUp.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x+1,p.y);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackDown.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x-1,p.y);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackUpLeft.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x+1,p.y-1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		attackDownLeft.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x-1,p.y-1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.refresh();
				}
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
		
		getChildren().addAll(attackRight,attackUpRight,attackDownRight,attackUpLeft,attackLeft,attackDownLeft,attackUp,attackDown,endTurn);
		
		
	}

}
