package Game;

import java.util.Random;

public class Wall {
	public boolean connection = false;
	public Wall(){
		Random rand = new Random();
		float rnd = rand.nextFloat();
		if(rnd>0.7f){
			connection = true;
		}
	}
}
