package Game;

public abstract class Agent {
	public String Name;
	public abstract int decide(Choice[] choices);
	public abstract void turn(char input); // turn logic for this agent.
}
