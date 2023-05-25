package views;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.characters.*;

public class HeroCellView extends CellView {
	
	Hero hero;
	
	HeroView heroView;
	
	private static Statistics heroStatistics = new Statistics();
	
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


	public HeroView getHeroView() {
		return heroView;
	}
	
	
	public HeroCellView(Hero hero,boolean isVisible,ImageView tile) {
		super(isVisible);
		StackPane sp = new StackPane();
		
		String heroType = hero.getClass().getSimpleName().toLowerCase();
		
		
		
		this.heroView = new HeroView(heroType);
		
		sp.getChildren().addAll(tile,this.heroView.getLayout());

		this.hero = hero;
		if (isVisible)
			this.setGraphic(sp);
		
		this.setOnMouseEntered(event ->{
			heroStatistics.setStatistics(hero);
			this.setTooltip(new Tooltip(heroStatistics.toString()));
		});
		

	}


	
	
	

}
