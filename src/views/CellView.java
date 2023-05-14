package views;

import javafx.scene.control.*;

public class CellView extends Button {
	
	public CellView() {
		super();
		this.setWidth(500);
		this.setHeight(500);
		
	}
	
	
	public CellView(String text) {
		super(text);
		this.setWidth(500);
		this.setHeight(500);
	}
	

}
