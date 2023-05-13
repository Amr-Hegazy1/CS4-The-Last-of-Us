package views;

import model.characters.*;

public class HeroCellView extends CellView {
	
	Hero hero;
	
	HeroView heroView;
	
	public HeroCellView() {
		super();
		this.heroView = new HeroView();
		
		this.setGraphic(this.heroView.vBox);
		
	}
	
	
	public HeroCellView(String text) {
		super(text);
		
	}
	
	public HeroCellView(Hero hero) {
		super();
		this.hero = hero;
		
	}
	
	public HeroCellView(String text,Hero hero) {
		super(text);
		this.hero = hero;
		this.heroView = new HeroView();
		
		this.setGraphic(this.heroView.imageView);
	}
	
	

}
