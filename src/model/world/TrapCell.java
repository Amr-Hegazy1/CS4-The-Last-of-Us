package model.world;
import java.util.*;

public class TrapCell extends Cell{
	private int trapDamage;
	
	public TrapCell() {
		super();
		Random rand = new Random();
		trapDamage = ( rand.nextInt(3) + 1 ) * 10;
	}
	public int getTrapDamage() {
		return trapDamage;
	}

}
