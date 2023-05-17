package views;



import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.characters.Direction;
import model.characters.Hero;
import model.world.CharacterCell;

public class Controls extends VBox {
	
	public Controls() {
		
		Button moveRight = new Button("move right");
		Button moveLeft = new Button("move left");
		Button moveUp = new Button("move up");
		Button moveDown = new Button("move down");
		Button attackRight = new Button ("Attack Right");
		Button attackUpRight = new Button ("Attack UpRight");
		Button attackLeft = new Button ("Attack Left");
		Button attackUp = new Button ("Attack Up");
		Button attackDown = new Button ("Attack Down");
		Button attackDownRight = new Button ("Attack DownRight");
		Button attackUpLeft = new Button ("Attack UpLeft");
		Button attackDownLeft = new Button ("Attack DownLeft");
		
		
		moveRight.setOnAction(event ->{
			
			try {
				Main.currentHero.move(Direction.RIGHT);
				Main.updateMap();
				
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
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
			} catch (MovementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});	
		attackRight.setOnAction(event->{
			try {
				Point p=Main.currentHero.getLocation();
				Point pnew= new Point(p.x,p.y+1);
				if(Hero.isvalid(pnew)) {
				Main.currentHero.setTarget(((CharacterCell)Game.map[pnew.x][pnew.y]).getCharacter());
				Main.currentHero.attack();
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
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
				Main.updateMap();
				}
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotEnoughActionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
		getChildren().addAll(moveRight,moveLeft,moveUp,moveDown,attackRight,attackUpRight,attackDownRight,attackUpLeft,attackLeft,attackDownLeft,attackUp,attackDown);
		
		
	}

}
