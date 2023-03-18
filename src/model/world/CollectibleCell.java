package model.world;

import model.collectibles.*;

public class CollectibleCell extends Cell {
	private Collectible collectible;
	public CollectibleCell() {
		super();
	}
	public Collectible getCollectible() {
		return collectible;
	}
	 
}
