package Game;

import java.util.Random;

public class Sciana {
	public boolean drzwi = false;
	public Sciana(){
		Random rand = new Random();
		float rnd = rand.nextFloat();
		if(rnd>0.7f){
			drzwi = true;
		}
	}
}
