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
	
	public HeroCellView(Hero hero,boolean isVisible) {
		super(isVisible);
		
		String heroType = hero.getClass().getSimpleName().toLowerCase();
		
		
		
		this.heroView = new HeroView(heroType);
		
		

		this.hero = hero;
		if (isVisible)
			this.setGraphic(this.heroView.getLayout());
		
		this.setOnMouseEntered(event ->{
			heroStatistics.setStatistics(hero);
			this.setTooltip(new Tooltip(heroStatistics.toString()));
		});
		

	}


	
	public void rotate(newDirection d) {
		
		ImageView sprite = heroView.getSprite();
		
		switch(d) {
		
		case LEFT: sprite.setScaleX(-1);sprite.setScaleY(1);break;
		case DOWN: 
		case RIGHT: sprite.setScaleX(1);sprite.setScaleY(1);break;
		case UP: sprite.setScaleX(1);sprite.setScaleY(-1);break;
		case UPRIGHT: sprite.setRotate(-45);break;
		case DOWNRIGHT: sprite.setRotate(45);break;
		case UPLEFT: sprite.setScaleX(-1);sprite.setScaleY(1);sprite.setRotate(45);break;
		case DOWNLEFT: sprite.setScaleX(-1);sprite.setScaleY(1);sprite.setRotate(-45);break;
		
		}
		
		
		
		
		heroView.setSprite(sprite);
	}
	

}
