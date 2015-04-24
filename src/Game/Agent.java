package Game;

public interface Agent {
	public int decide(Choice[] choices, AgentStruct state);
	public String getName();
}
