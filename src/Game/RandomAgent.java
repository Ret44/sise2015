package Game;

import java.util.Random;

public class RandomAgent implements Agent {
	@Override
	public int decide(Choice[] choices, AgentStruct state) {
		Random r = new Random();
		return r.nextInt(choices.length);
	}

}
