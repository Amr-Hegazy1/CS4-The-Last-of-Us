package views;

import model.characters.*;

public class HeroCellView extends CellView {
	
	Hero hero;
	
	HeroView heroView;
	
	public HeroCellView() {
		super();
		this.heroView = new HeroView();
		
		this.setGraphic(this.heroView.getLayout());
		
	}
	
	
	public HeroCellView(String text) {
		super(text);
		
	}
	
	public HeroCellView(Hero hero) {
		super();
		this.heroView = new HeroView();
		this.hero = hero;
		this.setGraphic(this.heroView.getLayout());
		
	}
	
	public HeroCellView(String text,Hero hero) {
		super(text);
		this.hero = hero;
		this.heroView = new HeroView();
		
		this.setGraphic(this.heroView.getLayout());
	}
	
	public HeroCellView(Hero hero,boolean isVisible) {
		super();


		this.heroView = new HeroView();


		this.hero = hero;
		if (isVisible)
			this.setGraphic(this.heroView.getLayout());
		else
			this.setStyle("-fx-background-color:#000000");

	}


	public HeroView getHeroView() {
		return heroView;
	}
	
	
	

}
