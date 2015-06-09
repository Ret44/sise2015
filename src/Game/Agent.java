package Game;

public interface Agent {
	public int decide(Choice[] choices, AgentStruct state, int historyLenght, int itemLevel, int turn);
	public String getName();
}
